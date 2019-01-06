package com.oracle.tcp;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpService {

    public static void main(String[] args) throws Exception {

        //定义一个服务端的套接字
        ServerSocket server = new ServerSocket(9999);

        //接收请求
        System.out.println("服务端已经启动");
        Socket s = server.accept();

        new ServerWriter(s).start();

        System.out.println("服务连接成功.");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = null;
        do {
            str = br.readLine();
            System.out.println("接收到一条消息：");
            System.out.println("客户说："+str);
        }while(!str.equals("bye"));

        br.close();
        s.close();
        server.close();
    }
}

/**
 * 向客户端写
 */
 class ServerWriter extends Thread{
     Socket s;

    public ServerWriter(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        Scanner scanner = null;
        try{
            new PrintWriter(s.getOutputStream());
            new Scanner(System.in);
            String str = null;
            while(true){
                System.out.println("服务器请回带：");
                str = scanner.nextLine();
                out.println(str);
                out.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            scanner.close();
            out.close();
        }


    }
}