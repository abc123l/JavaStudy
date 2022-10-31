package com.abc.tankgame5;

import javax.swing.*;

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
        this.setSize(1200,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new abcTankGame04();
    }
}
