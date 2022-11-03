package com.abc.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 */
public class abcTankGame05 extends JFrame {
    MyPanel myPanel=null;
    static Scanner scanner=new Scanner(System.in);
    public abcTankGame05() {
        System.out.println("是否恢复上局游戏? 1:新游戏 2：继续上局");
        String key=scanner.next();
        myPanel=new MyPanel(key);
        Thread thread = new Thread(myPanel);
        thread.start();
        this.add(myPanel);
        this.addKeyListener(myPanel);
        this.setVisible(true);
        this.setSize(1300,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {//在关闭窗口的时候调用记录方法
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {

        new abcTankGame05();
    }



}
