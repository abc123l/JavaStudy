package com.abc.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author abc
 * @version 1.0
 */
public class SocketTCP02Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
//        byte[] buffer =new byte[1024];
//        int readLen=0;
//        while ((readLen=inputStream.read(buffer))!=-1){
//            System.out.println(new String(buffer,0,readLen));
//        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

//        while (bufferedReader.readLine()!=null){
//            System.out.println(bufferedReader.readLine());
//        }不能这样写，因为如果返回null相当于没有写入结束标记
        String s = bufferedReader.readLine();//如果客户端的输出流没有结束标记就会卡在这里
        System.out.println(s);

        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("im fine, thanks for asking.".getBytes());
//        socket.shutdownOutput();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("im fine, thanks for asking.字符流");
        bufferedWriter.newLine();//结束标记，内容结束
        bufferedWriter.flush();//数据进入管道


        //关流要按顺序
        serverSocket.close();
        socket.close();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
