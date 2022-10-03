package com.zerin.chatserver.service;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 使用HashMap管理线程
 */
public class ManageServerThread {
    private static HashMap<String, ServerThread> map = new HashMap<>();

    public static void addServerThread(String userId, ServerThread serviceThread) {
        map.put(userId, serviceThread);
    }

    public static ServerThread getServerThread(String userId) {
        return map.get(userId);
    }

    public static void removeServerThraed(String userId){
        map.remove(userId);
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
