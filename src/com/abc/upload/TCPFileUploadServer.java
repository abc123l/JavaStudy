package com.abc.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author abc
 * @version 1.0
 * 文件上传的服务端
 */
public class TCPFileUploadServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端在等待连接");
        Socket socket = serverSocket.accept();

        //读取客户端发送的数据
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //将文件写到指定目录下
        String filePath="src//Acopy.png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        bos.write(bytes);
        bos.close();

        //用字符输出流输出到客户端提示信息
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("图片已收到");
        writer.flush();//把内容刷新到数据通道
        socket.shutdownOutput();//结束标记


        //关闭资源
        writer.close();
        bis.close();
        socket.close();
        serverSocket.close();

    }
}
