package com.abc.algorithms.dynamic;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 * 背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w={1,4,3};//物品重量
        int[] val={1500,3000,2000};//物品价值
        int m=4;//背包容量
        int n= val.length;//物品的个数




        int[][] v=new int[n+1][m+1];//创建表
        //v[i][j] 表示在前i个物品中装入容量为j的最大价值

        //为了揭露背包里放了什么东西，需要一个二维数组
        int[][] path=new int[n+1][m+1];

        //初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;//第一列
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0;//第一行
        }
        //不处理第一行和第一列所以i和j是从1开始
        //由于w和val的索引都从0开始，所以相应的要减一
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i-1]>j){//考虑前i个物品，其中第i个物品的重量大于当前背包的重量
                    v[i][j]=v[i-1][j];
                }else {
                    //第i个物品的重量小于等于当前背包的重量，如果加入该物品，该背包的价值是该物品的价值加上当前背包
                    //容量去掉该物品的重量后在前i-1个物品的最大质量，将它和不加入该物品的价值做最大值
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了看到背包里具体放了什么东西，不能直接用上面的公式
                    if (v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j]=1;
                    }else {
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }

        for (int[] ints : v) {//打印该表
            System.out.println(Arrays.toString(ints));
        }



        int i= path.length-1;//最后一行
        int j=path[0].length-1;//最后一列
        while (i>0 && j>0){
            if (path[i][j]==1){
                System.out.println("第"+i+"个物品放到背包");
                j-=w[i-1];//当前背包容量减去放入物品的重量
            }
            i--;//考虑前i-1个物品
        }

    }
}
