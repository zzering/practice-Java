package com.zerin.chatserver.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 使用HashMap管理线程
 */
public class ManageClientThreads {
    private static HashMap<String, ServerThread> hm = new HashMap<>();

    public static HashMap<String,ServerThread> getHm(){
        return hm;
    }
    public static void addServerThread(String userId, ServerThread serviceThread) {
        hm.put(userId, serviceThread);
    }

    public static ServerThread getServerThread(String userId) {
        return hm.get(userId);
    }

    public static void removeServerThraed(String userId){
        hm.remove(userId);
    }

    public static String getOnlineUser(){
        Iterator<String> iterator = hm.keySet().iterator();
        StringBuilder onlineUserList = new StringBuilder();
        while(iterator.hasNext()){
            onlineUserList.append(iterator.next()).append(" ");
        }
        return onlineUserList.toString();
    }
}
