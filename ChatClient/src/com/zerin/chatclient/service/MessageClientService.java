package com.zerin.chatclient.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MessageClientService {

    public void sendMessageToOne(String content, String senderId, String getterId) {
        Message msg = new Message();
        msg.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        msg.setSender(senderId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());
        System.out.println(senderId + " said to " + getterId + ": " + content);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageClientThread.getClientThread(senderId).getSocket().getOutputStream()
            );
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAll(String content, String senderId) {
        Message msg = new Message();
        msg.setMesType(MessageType.MESSAGE_COMM_MES);
        msg.setSender(senderId);
        msg.setContent(content);
        msg.setSendTime(new Date().toString());
        System.out.println(senderId + " said to all: " + content);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageClientThread.getClientThread(senderId).getSocket().getOutputStream()
            );
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
