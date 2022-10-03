package com.zerin.chatclient.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;
import com.zerin.chatcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {
    private User u=new User();
    private Socket socket;

    public boolean checkUser(String userId,String pwd){
        boolean state=false;
        u.setUserId(userId);
        u.setPasswd(pwd);
        try{
            // 连接到服务端
            socket= new Socket(InetAddress.getLocalHost(),8888);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);
            //read from server
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) ois.readObject();
            if(msg.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                ClientThread ccst=new ClientThread(socket);
                ccst.start();
                //这里为了后面客户端的扩展，我们将线程放入到集合管理
                ManageClientThread.addClientThread(userId,ccst);
                state=true;
            }else {
                //如果登录失败, 我们就不能启动和服务器通信的线程, 关闭socket
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    public void onlineFriendList(){
        Message msg=new Message();
        msg.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        msg.setSender(u.getUserId());
        try {
            //从管理线程的集合中，通过userId, 得到这个线程对象
            //通过这个线程得到关联的socket
            //得到当前线程的Socket 对应的 ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageClientThread.getClientThread(u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
