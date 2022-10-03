package com.zerin.chatclient.view;

import com.zerin.chatclient.service.UserClientService;
import com.zerin.chatclient.utils.ScannerUtility;

//@SuppressWarnings("all")
public class ChatView {
    private boolean loop = true;// 控制是否显示菜单
    private String key = "";// 接收用户的键盘输入
    private UserClientService userClientService = new UserClientService();// 对象是用于登录服务/注册用户

    public static void main(String[] args) {
        new ChatView().mainMenu();
    }

    private void mainMenu() {
        while (loop) {
            System.out.println("---Welcome to Zerin's Chatroom---");
            System.out.println("\t\t1.login in");
            System.out.println("\t\t0.exit");
            System.out.println("Please enter a number:");
            key = ScannerUtility.readString(1);

            switch (key) {
                case "1":
                    System.out.println("Please enter your id:");
                    String userId = ScannerUtility.readString(50);
                    System.out.println("Please enter your password");
                    String pwd = ScannerUtility.readString(50);
                    if (userClientService.checkUser(userId, pwd)) {
                        System.out.println("Login succeeded");
                        System.out.println("Welcome user: " + userId);
                        while (loop) {
                            System.out.println("\n----Secondary menu(user: " + userId + " )----");
                            System.out.println("\t\t 1.显示在线用户列表");
                            System.out.println("\t\t 2.群发消息");
                            System.out.println("\t\t 3.私聊消息");
                            System.out.println("\t\t 4.发送文件");
                            System.out.println("\t\t 0.退出系统");
                            System.out.print("请输入你的选择: ");
                            key = ScannerUtility.readString(1);
                            switch (key) {
                                case "1":
                                    System.out.println("fuck");
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想对大家说的话: ");
                                    String s = ScannerUtility.readString(100);
                                    //messageClientService.sendMessageToAll(s, userId);
                                    break;
                                case "3":
                                    System.out.print("请输入想聊天的用户号(在线): ");
                                    String getterId = ScannerUtility.readString(50);
                                    System.out.print("请输入想说的话: ");
                                    String content = ScannerUtility.readString(100);
                                    //编写一个方法，将消息发送给服务器端
                                    //messageClientService.sendMessageToOne(content, userId, getterId);
                                    break;
                                case "4":
                                    System.out.print("请输入你想把文件发送给的用户(在线用户): ");
                                    getterId = ScannerUtility.readString(50);
                                    System.out.print("请输入发送文件的路径(形式 d:\\xx.jpg)");
                                    String src = ScannerUtility.readString(100);
                                    System.out.print("请输入把文件发送到对应的路径(形式 d:\\yy.jpg)");
                                    String dest = ScannerUtility.readString(100);
                                    //fileClientService.sendFileToOne(src,dest,userId,getterId);
                                    break;
                                case "0":
                                    //调用方法，给服务器发送一个退出系统的message
                                    //userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("failed to login in");
                    }
                    break;
                case "0":
                    loop = false;
                    break;
            }
        }
    }
}

