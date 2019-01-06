package com.oracle.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(8888);
        System.out.println("启动服务端。");
        byte [] b = new byte[2048];
        DatagramPacket dp = new DatagramPacket(b,b.length);

        ds.receive(dp);

        System.out.println("收到的字节数是："+dp.getLength());

        System.out.println(new String(b,0,dp.getLength()));
        ds.close();
    }
}
