package com.zerin.chatcommon;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED="1";
    String MESSAGE_LOGIN_FAILED="2";
    String MESSAGE_COMM_MES = "3"; //普通信息包
    String MESSAGE_GET_ONLINE_USER = "4"; //请求返回在线用户列表
    String MESSAGE_RET_ONLINE_USER = "5"; //已返回在线用户列表
}
