package com.abc.homework;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author abc
 * @version 1.0
 */
public class HW02Receiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);
        byte[] buf=new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        socket.receive(packet);
        int length = packet.getLength();
        byte[] data = packet.getData();

        String question = new String(data, 0, length);
        String ans="";
        if ("你是谁？".equals(question)){
            ans="妙蛙种子";
        }else {
            ans="no one";
        }

        packet=new DatagramPacket(ans.getBytes(),ans.getBytes().length,InetAddress.getLocalHost(),9998);
        socket.send(packet);

        //关流
        socket.close();
    }
}
