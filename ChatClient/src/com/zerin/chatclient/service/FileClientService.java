package com.zerin.chatclient.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;

import java.io.*;

public class FileClientService {
    /**
     * @param src      源文件
     * @param dest     把该文件传输到对方的哪个目录
     * @param senderId 发送用户id
     * @param getterId 接收用户id
     */
    public void sendFileToOne(String src, String dest, String senderId, String getterId) {
        Message msg = new Message();
        msg.setMesType(MessageType.MESSAGE_FILE_MES);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setSrc(src);
        msg.setDest(dest);
        // 需要将文件读取
        FileInputStream fileInputStream = null;
        //🐉
        byte[] fileBytes = new byte[(int) new File(src).length()];
        try {
            fileInputStream = new FileInputStream(src);
            msg.setFileBytes(fileBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("fuckkk");
        System.out.println("\n" + senderId + " send file: " + src + " to " + getterId);
        System.out.println("\nPath to save the file: " + msg.getDest());

        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getClientThread(senderId).getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
