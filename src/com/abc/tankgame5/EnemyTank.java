package com.abc.tankgame5;

import java.util.Vector;

/**
 * @author abc
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable {
    boolean isLive = true;//是否存活
    //存放每一个敌人坦克的子弹
    Vector<Shot> shots = new Vector<>();
    //防止敌人坦克重叠就需要获得敌人的所有坦克
    Vector<EnemyTank> enemyTanks =new Vector<>();

    /**
     * 此方法是可以让myPanel类调用将它的EnemyTank集合赋给本类，获取敌人的所有坦克
     * @param enemyTanks
     */
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    /**
     * 此方法判断实例化对象是否与其他所有敌人坦克发生碰撞
     * 方法论：根据当前坦克的方向来判断
     * @return
     */
    public boolean isTouchEnemyTank(){
        switch (this.getDirect()){//
            case 0://up
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较，这一步必不可少
                    if (this!=enemyTank){
                        if (enemyTank.getDirect()==0 || enemyTank.getDirect()==2){//敌人坦克是上下
                            //考虑碰撞有两点，以自己的坦克为参考系，左前方的点在敌人坦克区域内和右前方的点在敌人坦克区域内
                            //左上角
                            if (this.getX()>= enemyTank.getX()
                            && this.getX()<= enemyTank.getX()+40
                            && this.getY()>= enemyTank.getY()//注意这是正确写法
                            && this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                            //右上角
                            if (this.getX()+40>= enemyTank.getX()
                                    && this.getX()+40<= enemyTank.getX()+40
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                        }

                        if (enemyTank.getDirect()==1 || enemyTank.getDirect()==3){//敌人坦克是左右
                            //左上角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+60
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                            //右上角
                            if (this.getX()+40>= enemyTank.getX()
                                    && this.getX()+40<= enemyTank.getX()+60
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://right
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较，这一步必不可少
                    if (this!=enemyTank){
                        if (enemyTank.getDirect()==0 || enemyTank.getDirect()==2){//敌人坦克是上下
                            //右上角
                            if (this.getX()+60>= enemyTank.getX()
                                    && this.getX()+60<= enemyTank.getX()+40
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                            //右下角
                            if (this.getX()+60>= enemyTank.getX()
                                    && this.getX()+60<= enemyTank.getX()+40
                                    && this.getY()+40>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+40<= enemyTank.getY()+60){
                                return true;
                            }
                        }

                        if (enemyTank.getDirect()==1 || enemyTank.getDirect()==3){//敌人坦克是左右
                            //右上角
                            if (this.getX()+60>= enemyTank.getX()
                                    && this.getX()+60<= enemyTank.getX()+60
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                            //右下角
                            if (this.getX()+60>= enemyTank.getX()
                                    && this.getX()+60<= enemyTank.getX()+60
                                    && this.getY()+40>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://down
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较，这一步必不可少
                    if (this!=enemyTank){
                        if (enemyTank.getDirect()==0 || enemyTank.getDirect()==2){//敌人坦克是上下
                            //左下角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+40
                                    && this.getY()+60>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+60<= enemyTank.getY()+60){
                                return true;
                            }
                            //右下角
                            if (this.getX()+40>= enemyTank.getX()
                                    && this.getX()+40<= enemyTank.getX()+40
                                    && this.getY()+60>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+60<= enemyTank.getY()+60){
                                return true;
                            }
                        }

                        if (enemyTank.getDirect()==1 || enemyTank.getDirect()==3){//敌人坦克是左右
                            //左下角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+60
                                    && this.getY()+60>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+60<= enemyTank.getY()+40){
                                return true;
                            }
                            //右下角
                            if (this.getX()+40>= enemyTank.getX()
                                    && this.getX()+40<= enemyTank.getX()+60
                                    && this.getY()+60>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+60<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://left
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较，这一步必不可少
                    if (this!=enemyTank){
                        if (enemyTank.getDirect()==0 || enemyTank.getDirect()==2){//敌人坦克是上下
                            //左上角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+40
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+60){
                                return true;
                            }
                            //左下角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+40
                                    && this.getY()+40>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+40<= enemyTank.getY()+60){
                                return true;
                            }
                        }

                        if (enemyTank.getDirect()==1 || enemyTank.getDirect()==3){//敌人坦克是左右
                            //左上角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+60
                                    && this.getY()>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                            //左下角
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX()+60
                                    && this.getY()+40>= enemyTank.getY()//注意这是正确写法
                                    && this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
        }
        return false;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            Shot s = null;
            if (isLive && shots.size() < 1) {//要在坦克存活的前提下给它发射第二次，第三次子弹的机会

                switch (getDirect()) {//当敌人的子弹消亡后继续给它画子弹
                    case 0://上
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1://右
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2://下
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                //把新建的shot放入集合中
                //启动线程
                new Thread(s).start();
            }


            //根据坦克方向继续移动
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {//让敌人坦克走30布再变方向
                        if (getY() > 0&& !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);//休眠50ms，为了看到移动的效果
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {//让敌人坦克走30布再变方向
                        if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);//休眠50ms，否则会乱窜
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {//让敌人坦克走30布再变方向
                        if (getY() + 60 < 750&& !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);//休眠50ms，否则会乱窜
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {//让敌人坦克走30布再变方向
                        if (getX() > 0&& !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);//休眠50ms，否则会乱窜
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }


            //然后随机的改变坦克方向0-3
            setDirect((int) (Math.random() * 4));
            //一旦写多线程,一定要考虑清除线程什么时候退出
            if (!isLive) {
                break;
            }

        }
    }
}
