package com.oracle.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket(InetAddress.getLocalHost(),9999);

        //启动读线程
        new ClientReader(s).start();

        Scanner scanner = new Scanner(System.in);
        PrintWriter out = new PrintWriter(s.getOutputStream());

        System.out.println("客户端已经连接成功");
        String str = null;

        do {
            System.out.println("你想对我说些什么呢？");
            System.out.println("我说：");
            str = scanner.nextLine();
            out.println(str);
            out.flush();
        }while(!str.equals("bye"));

        out.close();
        s.close();
        System.out.println("客户端已经关闭");
    }
}

/**
 * 从服务端接收消息
 */
class ClientReader extends Thread{

    Socket s;

    public ClientReader(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = null;
            while(true){
                str = br.readLine();
                System.out.println("客服人员说："+str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}