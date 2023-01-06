package com.abc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 选择排序，O(n^2)，耗时2s左右
 */
public class SelectSort {
    public static void main(String[] args) {

//        int[] arr={101,34,119,1,-1,90,123};
//        selectSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr=new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) (Math.random()*80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);


    }

    /**
     * 选择排序，第一次从第0个位置遍历到最后找出最小值并把它与第0位交换，第二次从第1个位置遍历到最后
     * 找出最小值并把它与第一位进行交换......
     * @param arr
     */
    public static void selectSort(int[] arr){

        int temp;//存储每一轮中的最小值
        int index;//找出每一轮的最小数的索引
        for (int i = 0; i < arr.length-1; i++) {
            index=i;//假定每一次的开始位置为最小值
            temp=arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<arr[index]){
                    index=j;
                    temp=arr[j];
                }
            }
            if (index!=i){//如果本轮最小值就是开始位置的话就不交换
                //交换arr[i]和arr[index]，利用temp的话只要写两行，if里面的语句越少越好，因为这是在循环里
                arr[index]=arr[i];
                arr[i]=temp;
            }

        }


    }
}
