package com.abc.upload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author abc
 * @version 1.0
 * 文件上传的客户端
 */
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        String filePath="e://temp/A.png";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        byte[] bytes = StreamUtils.streamToByteArray(bis);//将输入流准换成字节数组，用于保存图片

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);//将文件对应的字节数组的内容写入到数据通道
        bis.close();
        //设置写入数据结束标志
        socket.shutdownOutput();

        //接收从服务端接收的信息
        InputStream inputStream = socket.getInputStream();//将input流读取到的内容准成字符串
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);


        //关流
        inputStream.close();
        bos.close();
        socket.close();

    }
}
