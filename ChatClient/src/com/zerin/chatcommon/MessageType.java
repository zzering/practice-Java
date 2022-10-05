package com.zerin.chatcommon;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED="1";
    String MESSAGE_LOGIN_FAILED="2";
    String MESSAGE_GET_ONLINE_USER = "3"; //要求返回在线用户列表
    String MESSAGE_RET_ONLINE_USER = "4"; //返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "5";//客户端请求退出
    String MESSAGE_COMM_MES = "6"; //普通信息包
    String MESSAGE_TO_ALL_MES = "7"; //群发消息报
    String MESSAGE_FILE_MES="8";

}
