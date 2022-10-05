package com.zerin.chatclient.service;

import com.zerin.chatcommon.Message;
import com.zerin.chatcommon.MessageType;

import java.io.FileInputStream;

public class FileClientService {
    /**
     * @param src 源文件
     * @param dest 把该文件传输到对方的哪个目录
     * @param senderId 发送用户id
     * @param getterId 接收用户id
     */
    public void sendFileToOne(String src,String dest,String senderId,String getterId){
        Message msg = new Message();
        msg.setMesType(MessageType.MESSAGE_FILE_MES);
        msg.setSender(senderId);
        msg.setGetter(getterId);
        msg.setSrc(src);
        msg.setDest(dest);
        // 需要将文件读取
        FileInputStream fileInputStream=null;
//        byte[] fileBytes=new
    }
}
