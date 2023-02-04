package com.abc.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 基数排序，速度最快，但很耗内存，稳定的，不能排负数
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr={53,3,542,748,14,214};
//        radixSort(arr);
        int[] arr=new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) (Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        radixSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
    }
    private static void radixSort(int[] arr){
        int max=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        int maxLength=(max+"").length();//用于记录最大数的最高位，决定要排几轮
        int[][] bucket=new int[10][arr.length];
        int[] bucketElementCounts=new int[10];//用于记录每个bucket中的元素个数

        for (int i = 0,n=1; i < maxLength; i++,n*=10) {//n决定取个位数，10位数还是百位数
            //每一轮里把数组的元素放入各个桶里面
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement=arr[j]/n%10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //从桶里面按顺序取元素，覆盖原数组
            int index=0;//原数组的索引
            for (int j = 0; j < bucket.length; j++) {//一共10个桶
                if (bucketElementCounts[j]!=0){//证明这个桶里面有元素
                    for (int k = 0; k < bucketElementCounts[j]; k++) {//遍历这个桶
                        arr[index]=bucket[j][k];
                        index++;
                    }
                    bucketElementCounts[j]=0;//置0，便于下一轮使用
                }
            }
            //System.out.println("第"+(i+1)+"轮后："+ Arrays.toString(arr));
        }

    }
}
