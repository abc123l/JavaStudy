package com.abc.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author abc
 * @version 1.0
 */
public class HW03Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write("高山流水".getBytes());
        bos.flush();
        socket.shutdownOutput();


        InputStream inputStream = socket.getInputStream();
        byte[] bytes = StreamUtils.streamToByteArray(inputStream);

        String destFilePath="e://temp/downLoad.mp3";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bufferedOutputStream.write(bytes);

        bos.close();
        inputStream.close();
        bufferedOutputStream.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
