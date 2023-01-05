package com.abc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 冒泡排序
 * 时间复杂度O(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr={3,9,-1,10,20};
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));
        //测试冒泡拍戏所花费的时间,80000个随机数
        int[] arr=new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*80000);//[0,80000)
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间："+date1Str);

        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间："+date2Str);

    }
    public static void bubbleSort(int[] arr){
        int temp;//交换所使用的临时变量
        boolean flag=false;//在某一次排序中是否发生过交换
        for (int i = 0; i < arr.length-1; i++) {

            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            if (!flag){//如果未发生交换证明已经有序就退出
                break;
            }else {
                flag=false;
            }
//            System.out.println("第"+(i+1)+"趟排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
