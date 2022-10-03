package com.zerin.chatclient.service;

import java.util.HashMap;

public class ManageClientThread {
    private static HashMap<String, ClientThread> map = new HashMap<>();

    public static void addClientThread(String userId, ClientThread clientThread) {
        map.put(userId, clientThread);
    }

    public static void removeClientThraed(String userId){
        map.remove(userId);
    }

    public static ClientThread getClientThread(String userId) {
        return map.get(userId);
    }
}
