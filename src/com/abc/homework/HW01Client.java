package com.abc.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 */
public class HW01Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getByName("192.168.74.1"), 9999);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("请输入你的问题：");
        Scanner scanner = new Scanner(System.in);
        String question = scanner.next();
        writer.write(question);
        writer.newLine();
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = reader.readLine();
        System.out.println(s);

        //关流
        socket.close();
        writer.close();
        reader.close();

    }
}
