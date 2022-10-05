package com.zerin.chatclient.view;

import com.zerin.chatclient.service.MessageClientService;
import com.zerin.chatclient.service.UserClientService;
import com.zerin.chatclient.utils.ScannerUtility;

//@SuppressWarnings("all")
public class ChatView {
    private boolean loop = true;// 控制是否显示菜单
    private String key = "";// 接收用户的键盘输入
    private UserClientService userClientService = new UserClientService();// 对象是用于登录服务/注册用户
    private MessageClientService messageClientService =new MessageClientService();// 用于消息服务

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
                            System.out.println("\n----Secondary Menu(user: " + userId + " )----");
                            System.out.println("\t\t 1.Show online users");
                            System.out.println("\t\t 2.Send message to everyone");
                            System.out.println("\t\t 3.Send message to someone");
                            System.out.println("\t\t 4.Send file");
                            System.out.println("\t\t 0.Exit");
                            System.out.print("Please enter your choice: ");
                            key = ScannerUtility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("Please enter the message to everyone: ");
                                    String s = ScannerUtility.readString(100);
                                    messageClientService.sendMessageToAll(s, userId);
                                    break;
                                case "3":
                                    System.out.print("Please enter the user that you want to chat with: ");
                                    String getterId = ScannerUtility.readString(50);
                                    System.out.print("Please enter the message: ");
                                    String content = ScannerUtility.readString(100);
                                    messageClientService.sendMessageToOne(content, userId, getterId);
                                    break;
                                case "4":
                                    System.out.print("Please enter the user that you want to send file to: ");
                                    getterId = ScannerUtility.readString(50);
                                    System.out.print("Please enter the path of the file (like d:\\xx.jpg)");
                                    String src = ScannerUtility.readString(100);
                                    System.out.print("Please enter the destination of the file(like d:\\yy.jpg)");
                                    String dest = ScannerUtility.readString(100);
                                    //fileClientService.sendFileToOne(src,dest,userId,getterId);
                                    break;
                                case "0":
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Failed to login in");
                    }
                    break;
                case "0":
                    loop = false;
                    break;
            }
        }
    }
}

