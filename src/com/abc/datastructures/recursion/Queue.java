package com.abc.datastructures.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abc
 * @version 1.0
 * n皇后问题通解
 */
public class Queue {
    //皇后的数量
    int max=8;//8皇后
    //定义数组，数组的索引标识第几行，值为第几行要放在的第几列
    static int count=0;
    static ArrayList<List<String>> result=new ArrayList<>();//用于存放最终结果
    int[] array = new int[max];
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.check(0);
        System.out.println("一共有"+count+"种解法");

        //显示结果集合
        for (List<String> strings : result) {
            System.out.println(strings);
        }

    }

    /**
     * for循环里有递归，效率很低，这里有回溯，从栈顶开始（即第8行开始回溯）
     * @param n 放第n个皇后
     */
    private void check(int n){
        ArrayList<String> list = new ArrayList<>();
        if (n==max){
            //这是显示成棋盘形式
            for (int i = 0; i < n; i++) {//row
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < n; j++) {//column
                    if (array[i]==j){//要在第i-1行的array[i]列放皇后，注意：array[i]为第array[i]-1列
                        stringBuilder.append('Q');
                    }else {
                        stringBuilder.append('.');
                    }
                }
                list.add(stringBuilder.toString());//添加行
            }
            result.add(list);//添加这一种解法


            //打印结果数组，不太直观
            print();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {//i代表列
            //把当前皇后放入该行的第一列
            array[n]=i;
            if (judge(n)){//判断把第n个皇后放入第i列时是否冲突
                //放第n+1个皇后
                check(n+1);
            }
            //如果冲突的话就把改行皇后放在下一列
        }
    }

    /**
     * 用来检查放第n个皇后时，与前n-1个皇后进行比较验证数据合理性
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {//与前n-1个都要进行判断
            //array[i]==array[n]表示在同一列
            //Math.abs(array[n-i])==Math.abs(array[n]-array[i])表示在相同对角线上
            //同一行不用判断，因为同一个索引不可能对应两个值
            if (array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //输出
    private void print(){
        count++;
        for (int i : array) {
            System.out.print(i+"\t");
        }
        System.out.println();
    }
}
