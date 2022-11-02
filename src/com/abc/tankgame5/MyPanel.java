package com.abc.tankgame5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author abc
 * @version 1.0
 * 坦克大战绘图区域
 */
@SuppressWarnings({"all"})
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //为了实现过一段时间就重回的效果，需要把MyPanel实现Runnable接口
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克放入到集合中，考虑到多线程问题使用Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个Vector用于存放炸弹，当子弹击中坦克时就加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();
    //定义一个集合，存放再文件中的敌人坦克信息
    Vector<Node> nodes = new Vector<>();

    //定义三张图片用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    int enemyTankSize = 3;

    public MyPanel(String key) {
        nodes = Recorder.getNodesAndEnemyTankNum();
        hero = new Hero(500, 100);//初始化一个坦克
        hero.setSpeed(2);
        switch (key) {
            case "1":
                Recorder.setAllEnemyTankNum(0);
                for (int i = 0; i < enemyTankSize; i++) {
                    //先创建敌人坦克，再设置方向，再加入
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);

                    enemyTank.setDirect(2);
                    //启动线程
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);//100 200 300

                    //给该对象加入一颗子弹，并把它加入敌人坦克的子弹集合
                    //注意这里的语句一定要放在enemyTank.setDirect(2);的后面
                    //否则enemyTank.getDirect()是默认为0，子弹向上发射
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    new Thread(shot).start();

                    enemyTank.shots.add(shot);
                }
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    enemyTankSize = nodes.size();
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());

                    enemyTank.setDirect(node.getDirect());
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);

                    //给该对象加入一颗子弹，并把它加入敌人坦克的子弹集合
                    //注意这里的语句一定要放在enemyTank.setDirect(2);的后面
                    //否则enemyTank.getDirect()是默认为0，子弹向上发射
                    Shot shot = null;
                    switch (node.getDirect()) {
                        case 0://上
                            shot = new Shot(enemyTank.getX() + 20, enemyTank.getY(), 0);
                            break;
                        case 1://右
                            shot = new Shot(enemyTank.getX() + 60, enemyTank.getY() + 20, 1);
                            break;
                        case 2://下
                            shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, 2);
                            break;
                        case 3:
                            shot = new Shot(enemyTank.getX(), enemyTank.getY() + 20, 3);
                            break;
                    }

                    new Thread(shot).start();
                    enemyTank.shots.add(shot);
                }
                break;
            default:
                System.out.println("输入有误");
        }


        //为了实现敌人坦克之间不能重叠的效果需要让每一个敌方坦克都有地方坦克的集合
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            enemyTank.setEnemyTanks(enemyTanks);
        }

        Recorder.setEnemyTanks(enemyTanks);//将敌人坦克的集合给Recorder方便记录信息存盘退出
        //初始化三张爆炸图片
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }

    /**
     * 记录玩家信息
     *
     * @param g
     */
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累积击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.BLACK);//画坦克的时候画笔颜色变了
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        showInfo(g);
        //画出自己坦克，把画的过程封装到方法里面
        if (hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }

        //画出hero射击子弹,子弹的集合遍历取出
        for (int i = 0; i < hero.shots.size(); i++) {
            if (hero.shots.get(i) != null && hero.shots.get(i).isAlive == true) {
                g.draw3DRect(hero.shots.get(i).x, hero.shots.get(i).y, 1, 1, false);
            } else {
                hero.shots.remove(hero.shots.remove(hero.shots.get(i)));
            }
        }


        //画出敌人的坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive == true) {//当敌人坦克是或者的时候才绘画
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //画出所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isAlive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {//生命周期结束的子弹要remove，不然会一直绘制这些已经声明周期结束的子弹
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

        //如果bombs集合是有炸弹的,就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            //根据当前bomb对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            //如果bomb的生命值为0，就从bombs的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
    }

    /**
     * @param x      坦克左上角横坐标
     * @param y      坦克左上角纵坐标
     * @param g      画笔
     * @param direct 坦克方向：上下左右
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型绘制坦克
        switch (type) {
            case 0://敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1://自己的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克方向绘制坦克
        //0 向上 1 向右 2 向下 3 向左
        switch (direct) {
            case 0://up
                g.fill3DRect(x, y, 10, 60, false);//左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//炮筒
                break;
            case 1://right
                g.fill3DRect(x, y, 60, 10, false);//上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//中间矩形
                g.fillOval(x + 20, y + 10, 20, 20);//盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//炮筒
                break;
            case 2://down
                g.fill3DRect(x, y, 10, 60, false);//左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间矩形
                g.fillOval(x + 10, y + 20, 20, 20);//盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
                break;
            case 3://left
                g.fill3DRect(x, y, 60, 10, false);//上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//中间矩形
                g.fillOval(x + 20, y + 10, 20, 20);//盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//炮筒
                break;
            default:
                System.out.println("暂时未处理");
        }
    }

    public void hitEnemyTank() {
        for (int i = 0; i < hero.shots.size(); i++) {//把子弹库里的每一个子弹与每一个敌人坦克去判断有没有击中
            Shot shot = hero.shots.get(i);
            //判断是否击中敌人坦克
            if (shot != null && shot.isAlive) {//当自己的子弹还存活,注意当用户还未按下j时hero.shot是null
                //hero.shot!=null必不可少
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    //编写方法，判断我方子弹是否击中敌人坦克
    //什么时候去判断坦克被击中，在run方法
    public void hitTank(Shot s, Tank tank) {
        //判断s是否击中坦克
        switch (tank.getDirect()) {
            /*
            坦克上和下方向
             */
            case 0:
            case 2:
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                        && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isAlive = false;
                    tank.isLive = false;
                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove(tank);//一定要拿掉，否则发出炮弹坦克消失，再发炮弹还会爆炸
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象加入bombs
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            /*
            坦克想右和向左
             */
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60
                        && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isAlive = false;
                    tank.isLive = false;
                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove(tank);
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象加入bombs
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;

        }
    }

    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {

            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (hero.isLive && shot.isAlive) {
                    hitTank(shot, hero);
                }
            }
        }
    }

    //处理移动问题
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下w键
            hero.setDirect(0);
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }
        //如果用户按下J，就需要发射，不按下的话shot为空
        if (e.getKeyCode() == KeyEvent.VK_J) {
            /*
            //判断hero的子弹是否消亡,消亡了再发射一颗子弹
            if (hero.shot==null || !hero.shot.isAlive) {
                //null对应的是第一次创建时的情况，第二个条件是子弹碰撞的别的坦克和到边界时的情况
                hero.shotEnemyTank();
            }
            */
            hero.shotEnemyTank();


        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100ms，重绘区域，刷新绘图区域，子弹就移动起来了
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
