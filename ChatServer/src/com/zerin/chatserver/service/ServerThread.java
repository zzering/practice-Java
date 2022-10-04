package com.zerin.chatserver.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 该类的一个对象和某个客户端保持通信
 */
public class ServerThread extends Thread {
    private Socket socket;
    private String userId;

    public ServerThread(Socket socket, String userid) {
        this.socket = socket;
        this.userId = userid;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Server and Client(" + userId + ") is maintaining its communication...");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) ois.readObject();
                if (msg.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    System.out.println("User "+msg.getSender() + " Request to get user list");
                    String onlineUser = ManageServerThread.getOnlineUser();
                    Message msg2 = new Message();
                    msg2.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    msg2.setContent(onlineUser);
                    msg2.setGetter(msg.getSender());
                    // return to client via stream
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(msg2);
                }else if(msg.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)){
                    System.out.println("User "+msg.getSender()+" logout");
                    ManageServerThread.removeServerThraed(msg.getSender());
                    socket.close();
                    break;
                } else if(msg.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    String sender = msg.getSender();


                }else {
                    System.out.println("Undefined message detected");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
