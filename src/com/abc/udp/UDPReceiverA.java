package com.abc.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author abc
 * @version 1.0
 * UDP接收端
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //创建一个DatagramSocket对象，准备在9999接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        //构建一个DatagramPacket对象，准备接收数据，最大为64k
        byte[] buf=new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //调用接收方法，将通过网络传输的DatagramPacket对象填充到packet对象
        //当有数据包发送到本机的9999端口时，就会接受到数据，如果没有的话就会阻塞
        System.out.println("接收端A等待数据");
        socket.receive(packet);

        //把得到的packet进行拆包，并取出数据
        int length = packet.getLength();
        byte[] data = packet.getData();
        String s = new String(data, 0, length);//注意把字节数组变为字符串的方法有三个参数！！！！！
        System.out.println(s);



        //发送
        byte[] sendData="I was wondering if after all these years you’d like to meet".getBytes();
        DatagramPacket sendPac = new DatagramPacket
                (sendData, sendData.length, InetAddress.getByName("192.168.74.1"), 9998);
        socket.send(sendPac);
        //关闭资源
        socket.close();
        System.out.println("A exists");
    }
}
