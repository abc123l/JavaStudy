package com.abc.tankgame5;

/**
 * @author abc
 * @version 1.0
 */
public class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed=2;
    boolean isAlive=true;//子弹是否存活

    /**
     *
     * @param x 初始x方向
     * @param y 初始y方向
     * @param direct 向哪边发射
     */
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true){
            try {//需要休眠，否则移动过快
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据direct改变x或y坐标
            switch (direct){
                case 0://上
                    y-=speed;
                    break;
                case 1://右
                    x+=speed;
                    break;
                case 2://下
                    y+=speed;
                    break;
                case 3://左
                    x-=speed;
            }
            System.out.println(x+" "+y);
            //如果子弹到面板边界就应当结束线程，当子弹碰到敌人坦克时也应结束线程
            if (!(x>=0 && x<=1000 && y>=0 && y<=750 && isAlive==true)){
                //这里的isAlive==true主要是判断是否碰到敌人坦克
                isAlive=false;
                System.out.println("子弹声明周期结束");
                break;
            }
        }
    }
}
