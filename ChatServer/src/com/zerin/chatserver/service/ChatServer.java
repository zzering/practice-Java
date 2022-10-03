package com.zerin.chatserver.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;
import com.zerin.chatcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是服务器, 在监听9999，等待客户端的连接，并保持通信
 */
public class ChatServer {
    private ServerSocket ss = null;
    // thread-safe HashMap
    private static ConcurrentHashMap<String,User> validUsers=new ConcurrentHashMap<>();

    static {
        validUsers.put("cs",new User("cs","cs"));
        validUsers.put("vs",new User("vs","vs"));
        validUsers.put("bs",new User("bs","bs"));
    }

    private boolean checkUser(String userId,String pwd){
        User user=validUsers.get(userId);
        if(user==null){
            return false;
        }
        if(!user.getPasswd().equals(pwd)){
            return false;
        }
        return true;
    }

    public ChatServer() {
        try {
            System.out.println("Server is listening in port 9999");
            ss = new ServerSocket(9999);
            while (true) {
                Socket socket = ss.accept();
                //得到socket关联的对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //得到socket关联的对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User u = (User) ois.readObject();//读取客户端发送的User对象
                Message msg = new Message();
                if (checkUser(u.getUserId(),u.getPasswd())) {
                    msg.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(msg);
                    ServerThread serviceThread = new ServerThread(socket, u.getUserId());
                    // nm 一定要启动
                    serviceThread.start();
                    ManageServerThread.addServerThread(u.getUserId(), serviceThread);
                    System.out.println("User id=" + u.getUserId() + " pwd=" + u.getPasswd() + " succeed to login in");
                    //todo why sout sequence is random
                } else {
                    msg.setMesType(MessageType.MESSAGE_LOGIN_FAILED);
                    oos.writeObject(msg);
                    socket.close();
                    System.out.println("User id=" + u.getUserId() + " pwd=" + u.getPasswd() + " failed to login in");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 如果服务器退出了while，说明服务器端不在监听，因此关闭ServerSocket
                if (ss != null) {
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
