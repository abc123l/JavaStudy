package com.abc.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author abc
 * @version 1.0
 * 服务端
 */
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //在本机的9999端口监听，等待连接，要求本机没有其他服务监听9999
        //ServerSocket可以通过accept()返回多个Socket，用于多个客户端连接服务器的并发
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端在9999端口等待连接");
        //当没有客户端连接9999端口时，程序会阻塞，等待连接
        //如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务器端socket="+socket.getClass());
        //通过socket.getInputStream()获取输入流
        InputStream inputStream = socket.getInputStream();
        byte[] buffer=new byte[1024];
        int readLen=0;
        while ((readLen=inputStream.read(buffer))!=-1){
            System.out.println(new String(buffer,0,readLen));//根据读取的实际长度
        }
        //关流
        inputStream.close();
        socket.close();
        serverSocket.close();//不要漏了它
    }
}
