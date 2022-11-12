package com.abc.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author abc
 * @version 1.0
 */
public class HW01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String question = reader.readLine();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String answer;

        if ("name".equals(question)){
            answer="abc";
        } else if ("hobby".equals(question)) {
            answer="coding";
        }else {
            answer="dunno";
        }

        writer.write(answer);
        writer.newLine();
        writer.flush();

        //关流
        serverSocket.close();
        socket.close();
        reader.close();
        writer.close();
    }
}
