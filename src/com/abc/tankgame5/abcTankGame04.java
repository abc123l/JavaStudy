package com.abc.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author abc
 * @version 1.0
 */
public class abcTankGame04 extends JFrame {
    MyPanel myPanel=null;
    public abcTankGame04() {
        myPanel=new MyPanel();
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
        new abcTankGame04();
    }



}
