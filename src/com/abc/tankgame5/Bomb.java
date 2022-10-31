package com.abc.tankgame5;

/**
 * @author abc
 * @version 1.0
 */
public class Bomb {
    int x,y;//坐标
    int life=9;//声明周期
    boolean isLive=true;//是否还存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少声明值
    public void lifeDown(){//配合出现图片的爆炸效果
        if (life>0){
            life--;
        }else {
            isLive=false;
        }

    }
}
