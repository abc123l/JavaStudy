package com.abc.recursion;

/**
 * @author abc
 * @version 1.0
 * 迷宫问题
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //把上下左右置为1，作为墙不能走
        for (int i = 0; i < 8 ; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }
        //设置挡板
//        map[3][1]=map[3][2]=1;
//        map[1][2]=map[2][2]=1;

        System.out.println("当前地图情况");
        for (int[] o :map) {
            for (int o1 :o) {
                System.out.print(o1+"\t");
            }
            System.out.println();
        }

        setWay(map,1,1);//map是引用类型，递归过程中共享
        //setWay2(map,1,1);//map是引用类型，递归过程中共享

        System.out.println("小球走过并标识的地图");
        for (int[] o :map) {
            for (int o1 :o) {
                System.out.print(o1+"\t");
            }
            System.out.println();
        }


    }

    /**
     * 输入一个地图和初始点如果能到map[6][5]则返回true
     * 策略：下-》右-》上-》左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){//base case
            return true;
        }else {
            if (map[i][j]==0){//这条路还没有走过
                map[i][j]=2;//假定它能走
                if (setWay(map,i+1,j)){//下
                    return true;
                } else if (setWay(map,i,j+1)) {//右
                    return true;
                } else if (setWay(map,i-1,j)) {//上
                    return true;
                } else if (setWay(map,i,j-1)) {//左
                    return true;
                }
                //到这里说明这条路不能走
                map[i][j]=3;
                return false;
            }else {//map[i][j]==1,2,3
                return false;
            }
        }

    }

    /**
     * 修改策略：上右下左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map,int i,int j){
        if (map[6][5]==2){//base case
            return true;
        }else {
            if (map[i][j]==0){//这条路还没有走过
                map[i][j]=2;//假定它能走
                if (setWay2(map,i-1,j)){//上
                    return true;
                } else if (setWay2(map,i,j+1)) {//右
                    return true;
                } else if (setWay2(map,i+1,j)) {//下
                    return true;
                } else if (setWay2(map,i,j-1)) {//左
                    return true;
                }
                //到这里说明这条路不能走
                map[i][j]=3;
                return false;
            }else {//map[i][j]==1,2,3
                return false;
            }
        }

    }

}
