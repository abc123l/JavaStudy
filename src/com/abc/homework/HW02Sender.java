package com.abc.homework;

import java.io.IOException;
import java.net.*;

/**
 * @author abc
 * @version 1.0
 */
public class HW02Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);
        String question="你是谁？";
        DatagramPacket packet = new DatagramPacket
                (question.getBytes(), question.getBytes().length, InetAddress.getLocalHost(), 9999);
        socket.send(packet);

        byte[] buf=new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        int length = packet.getLength();
        byte[] data = packet.getData();
        System.out.println(new String(data, 0, length));

        socket.close();
    }
}
