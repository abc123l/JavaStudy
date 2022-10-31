package com.abc.tankgame5;

/**
 * @author abc
 * @version 1.0
 * 这是一个可以自动判断两个字符串之间有没有包含关系的Solution
 */
public class Solution {
    public static void main(String[] args) {
        soul("cdef123".toCharArray(),"c".toCharArray());
    }
    public static void soul(char[] str1,char[] str2){
        char[] mother;
        char[] son;
        int flag=1;//true
        if (str1.length>=str2.length){

            mother=str1;
            son=str2;
        }else {
            mother=str2;
            son=str1;
        }
        for (int i = 0; i < mother.length; i++) {
            flag=1;

            if ((mother.length-i)< son.length){
                flag=0;
                break;
            }

            if (mother[i]==son[0]){
                for (int j = 0; j < son.length; j++) {
                    if (son[j]!=mother[i+j]){
                        flag=0;
                        break;
                    }
                }
            }else {
                flag=0;
            }

            if (flag==1)break;

        }
        System.out.print(flag==1?"有包含关系":"没有包含关系");
    }
}
