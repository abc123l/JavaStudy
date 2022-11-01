package com.abc.tankgame5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author abc
 * @version 1.0
 * 该类用于记录相关信息，和文件相关
 */
public class Recorder {
    private static int allEnemyTankNum=0;//机会的敌人坦克数
    //定义IO对象
    private static FileWriter fw=null;
    private static BufferedWriter bw=null;
    private static String recordFile="e://temp/myRecord.txt";

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    /**
     * 当击毁敌人坦克时该值自增
     */
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile,true));
            bw.write(allEnemyTankNum+"\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
