package com.abc.recursion;

/**
 * @author abc
 * @version 1.0
 * 汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoi('A','B','C',3);
    }
    public static void hanoi(char a,char b, char c,int num){
        if (num==1){
            System.out.println(a+"->"+c);
        }else {
            hanoi(a,c,b,num-1);
            System.out.println(a+"->"+c);
            hanoi(b,a,c,num-1);
        }
    }
}
