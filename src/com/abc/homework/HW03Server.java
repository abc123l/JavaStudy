package com.abc.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author abc
 * @version 1.0
 * 文件下载的服务端
 */
public class HW03Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();

        //如果客户端发送的数据较大的情况下
        InputStream inputStream = socket.getInputStream();
        int len=0;
        byte[] buf=new byte[1024];
        String downLoadFileName="";
        while ((len=inputStream.read(buf))!=-1){
            downLoadFileName+=new String(buf,0,len);
        }
        System.out.println("客户端希望下载的文件是"+downLoadFileName);

        //定义下载地址
        String resFileName="";
        if ("高山流水".equals(downLoadFileName)){
            resFileName="src//高山流水.mp3";
        }else {
            resFileName="src//无名.mp3";
        }

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(resFileName));
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        socket.shutdownOutput();

        //关流
        inputStream.close();
        bos.close();
        serverSocket.close();
        socket.close();
        System.out.println("服务端退出");
    }
}
