package com.abc.tankgame5;

import java.util.Vector;

/**
 * @author abc
 * @version 1.0
 */
public class Hero extends Tank {
    //定义一个Shot对象，表示一个射击行为
    Shot shot=null;//shot和hero息息相关，将两者链接起来位置+方向
    Vector<Shot> shots=new Vector<>();//放入多个子弹
    public Hero(int x, int y) {
        super(x, y);
    }//表示其坐标
    //拿到子弹的坐标
    public void shotEnemyTank(){

        if (shots.size()==5){
            return;
        }
        //创建shot对象，但要根据Hero的位置和方向来创建shot
        switch (getDirect()){
            case 0://上
                shot=new Shot(getX()+20,getY(),0);
                break;
            case 1://右
                shot=new Shot(getX()+60,getY()+20,1);
                break;
            case 2://下
                shot=new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot=new Shot(getX(),getY()+20,3);
        }
        shots.add(shot);//把新建的shot放入集合中
        //启动线程
        new Thread(shot).start();
    }
}
