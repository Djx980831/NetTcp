package com.oracle.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HttpDemo {

    public static void main(String[] args) throws Exception {
        //1.监听8080端口

        System.out.println("开始监听8080端口：");
        ServerSocket server = new ServerSocket(8080);
        Map<String,String> map = new HashMap<String,String>();

        Socket socket =  server.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String str = br.readLine();

        System.out.println(str);

        //2.获得消息头
        String string = null;
        while("".equals(string = br.readLine())){
            System.out.println(string);
            map.put(string.split(":")[0],string.split(":")[1].trim());
        }

        //3.并不是所有的都有内容体
        if(map.containsKey("Content-Length")){
            char[] c = new char[Integer.valueOf(map.get("Content-Length"))];
            br.read(c);
            System.out.println("内容是："+new String(c));
        }else{
            System.out.println("没有内容。");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String code = "qwertyuiopasdfghjklzxcvbnm1234567890";
        Random r = new Random();

        char [] names = code.toCharArray();

        StringBuffer sb = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(names[r.nextInt(36)]);
        stringBuffer.append(names[r.nextInt(36)]);
        stringBuffer.append(names[r.nextInt(36)]);
        stringBuffer.append(names[r.nextInt(36)]);

        sb.append("<html><head><title>我的网页</title></head><body>userName:<input type='text'></input></br>验证码是："+stringBuffer.toString()+"<body/></html>");
        //1.写状态行
        bw.write("HTTP/1.1 200 ok\r\n");

        //2.写消息头
        bw.write("Host : http:www.oracle.com\r\n");
        bw.write("Content-Type : text/html;charset=UTF-8\r\n");
        bw.write("\r\n");

        //3.写内容体
        bw.write(sb.toString());
        bw.write("\r\n");
        bw.flush();


        bw.close();
        br.close();
        socket.close();
        server.close();
    }
}
