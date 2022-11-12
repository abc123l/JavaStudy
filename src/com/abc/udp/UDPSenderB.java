package com.abc.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author abc
 * @version 1.0
 * UDP发送端B
 */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket对象准备发送/接收数据，不能和另外一个9999重复，因为这时在同一台机器上
        DatagramSocket socket = new DatagramSocket(9998);

        //将需要发送的数据封装到
        byte[] data="hello, it's me.".getBytes();

        DatagramPacket packet =
                new DatagramPacket(data, data.length, InetAddress.getByName("192.168.74.1"), 9999);
        socket.send(packet);




        //接收
        byte[] buf=new byte[1024];
        DatagramPacket receivePac = new DatagramPacket(buf, buf.length);
        socket.receive(receivePac);
        byte[] receivePacData = receivePac.getData();
        int receivePacLength = receivePac.getLength();
        System.out.println(new String(receivePacData,0,receivePacLength));

        //关流
        socket.close();
        System.out.println("B exists");
    }
}
