package com.abc.tankgame5;

import java.io.*;
import java.util.Map;
import java.util.Vector;

/**
 * @author abc
 * @version 1.0
 * 该类用于记录相关信息，和文件相关
 */
public class Recorder {
    private static int allEnemyTankNum = 0;//击毁的敌人坦克数
    //定义IO对象
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "e://temp/myRecord.txt";

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    /**
     * 当击毁敌人坦克时该值自增
     */
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }

    public static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //定义一个Vector保存敌人信息nodes
    private static Vector<Node> nodes = new Vector<>();

    /**
     * 读取文件内容，转化为敌人坦克的信息，以此创建一个Node对象保存敌人的信息，再将所有的敌人信息存放到一个集合里
     *
     * @return 文件中存储的敌人坦克的集合
     */
    public static Vector<Node> getNodesAndEnemyTankNum() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());//第一行是打掉的坦克数量
            //循环读取文件，生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    /**
     * 写入击毁的敌人坦克的数量
     * 写入还剩下的敌人坦克的位置和方向也就是存盘退出
     */
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //遍历敌人坦克的Vector
            //OOP，定义一个属性通过set方法得到MyPanel里的敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {//最好判断一下
                    //保存该信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(record + "\r\n");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
