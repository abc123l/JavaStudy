package com.abc.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author abc
 * @version 1.0
 */
public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("whats up? server?".getBytes());
//        socket.shutdownOutput();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        bufferedWriter.write("whats up, server?字符流");
        bufferedWriter.newLine();//插入一个换行符，表示写入内容结束，相当于介乎是标记，但对方读取的时候应当使用readLine
        bufferedWriter.flush();//如果使用字符流，需要手动刷新，否则数据不会写入数据通道



        InputStream inputStream = socket.getInputStream();
//        byte[] buffer =new byte[1024];
//        int readLen=0;
//        while ((readLen=inputStream.read(buffer))!=-1){
//            System.out.println(new String(buffer,0,readLen));
//        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        while (bufferedReader.readLine()!=null){
//            System.out.println(bufferedReader.readLine());
//        }
        String s = bufferedReader.readLine();
        System.out.println(s);

        socket.close();
        bufferedWriter.close();//关闭外层流
        bufferedReader.close();
    }
}
