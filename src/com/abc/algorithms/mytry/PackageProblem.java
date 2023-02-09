package com.abc.algorithms.mytry;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 * 背包问题求解
 */
public class PackageProblem {
    public static void main(String[] args) {
        int[] w={1,4,3};
        int[] val={1500,3000,2000};
        int m=4;//背包容量
        int n=w.length;//物品个数

        int[][] ans=new int[n+1][m+1];
        int[][] path=new int[n+1][m+1];

        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[0].length; j++) {
                if (w[i-1]>j){//当前物品装不进去
                    ans[i][j]=ans[i-1][j];
                }else {
                    if (ans[i][j-1]>ans[i-1][j-w[i-1]]+val[i-1]){
                        ans[i][j]=ans[i][j-1];
                    }else {
                        ans[i][j]=ans[i-1][j-w[i-1]]+val[i-1];
                        path[i][j]=1;//表明这个物品装进去了
                    }
                }
            }
        }
        for (int[] an : ans) {
            System.out.println(Arrays.toString(an));
        }

        int i= path.length-1;
        int j=path[0].length-1;
        while (i>0 && j>0){
            if (path[i][j]==1){
                System.out.println("第"+i+"个物品加入背包");
                j-=w[i-1];
            }
            i--;
        }


    }
}
