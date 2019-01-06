package com.oracle.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    public static void main(String[] args) throws Exception {
        //发送 hello world！

        //创建Socket
        DatagramSocket client = new DatagramSocket(9999);

        System.out.println("启动客户端。");

        byte [] str = "hello world!".getBytes();

        //创建packet 包
        DatagramPacket dp = new DatagramPacket(str,str.length,InetAddress.getLocalHost(),8888);

        //发包
        client.send(dp);

        System.out.println("发送成功。");
        client.close();
    }
}
