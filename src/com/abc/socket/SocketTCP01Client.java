package com.abc.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author abc
 * @version 1.0
 * 客户端
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        //连接服务端（ip，端口），即连接本机的9999端口，如果连接成功返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端socket返回="+socket.getClass());
        //通过socket.getOutputStream()得到和socket关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //写入数据
        outputStream.write("hello, server".getBytes());

        socket.shutdownOutput();//发送完数据之后应当有结束标记

        InputStream inputStream = socket.getInputStream();
        byte[] buffer =new byte[1024];
        int readLen=0;
        while ((readLen=inputStream.read(buffer))!=-1){
            System.out.println(new String(buffer,0,readLen));
        }
        //关流，释放资源
        inputStream.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");
    }
}
