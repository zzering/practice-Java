package com.zerin.chatclient.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("Client thread is waiting the messages form Server...");
                ObjectInputStream ios = new ObjectInputStream(socket.getInputStream());
                //如果服务器没有发送Message对象,线程会阻塞在这里
                Message msg = (Message) ios.readObject();
                if (msg.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_USER)) {
                    String[] onlineUsers = msg.getContent().split(" ");
                    System.out.println("\n---Current online user list---");
                    for (int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("User: " + onlineUsers[i]);
                    }
                } else if (msg.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n" + msg.getSender() + " said to " + msg.getGetter() + " : " + msg.getContent());
                } else if (msg.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)) {
                    //显示在客户端的控制台
                    System.out.println("\n" + msg.getSender() + " said to everyone: " + msg.getContent());
                } else if (msg.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    System.out.println("\n" + msg.getSender() + " send file: " + msg.getSrc() + " to: " + msg.getGetter());
                    System.out.println("\nPath to save the file: " + msg.getDest());
                    //取出message的文件字节数组，通过文件输出流写出到磁盘

                    // 创建文件输出流 绑定输出到des处
                    FileOutputStream fos = new FileOutputStream(msg.getDest());
                    // 将空壳+信息写入到des处
                    fos.write(msg.getFileBytes());
                    fos.close();

                    System.out.println("\nFile saved successfully");
                } else {
                    System.out.println("Undefined message detected");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
