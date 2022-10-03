package com.zerin.chatclient.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;

    public ClientThread(Socket socket){
        this.socket=socket;
    }

    public Socket getSocket(){
        return socket;
    }

    @Override
    public void run() {
        while (true){
            try {
                //System.out.println("客户端线程，等待从读取从服务器端发送的消息中");
                ObjectInputStream ios=new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送Message对象,线程会阻塞在这里
                Message msg = (Message) ios.readObject();
                if(msg.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    String[] onlineUsers=msg.getContend().split(" ");
                    System.out.println("\n---Current online user list---");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("User: " + onlineUsers[i]);
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
