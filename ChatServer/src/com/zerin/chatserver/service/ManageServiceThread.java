package com.zerin.chatserver.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 使用HashMap管理线程
 */
public class ManageServiceThread {
    private static HashMap<String, ServiceThread> map = new HashMap<>();

    public static void addServiceThread(String userId, ServiceThread serviceThread) {
        map.put(userId, serviceThread);
    }

    public static ServiceThread getServiceThread(String userId) {
        return map.get(userId);
    }

    public static String getOnlineUser(){
        Iterator<String> iterator = map.keySet().iterator();
        StringBuilder onlineUserList = new StringBuilder();
        while(iterator.hasNext()){
            onlineUserList.append(iterator.next()).append(" ");
        }
        return onlineUserList.toString();
    }
}
