package com.abc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 希尔排序，移位法很快
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr={8,9,1,7,2,3,5,4,6,0};
//        shellSort2(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr=new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) (Math.random()*80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println(time1);
        shellSort2(arr);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);

    }

    /**
     * 增量式排序，每次跳过gap/2个元素凉凉交换
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp=0;
        int count=0;
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            //这个双层for循环是为了确定相邻gap的两个元素
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap; j >=0 ; j-=gap) {
                    if (arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
            //System.out.println("第"+(++count)+"轮希尔排序后："+Arrays.toString(arr));
        }



        /*
        //希尔排序第一轮，10个数据被分成了5组
        int temp=0;
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有元素，共5组，每组2个元素，步长为5
            for (int j = i-5; j >=0 ; j-=5) {
                if (arr[j]>arr[j+5]){
                    temp=arr[j];
                    arr[j]=arr[j+5];
                    arr[j+5]=temp;
                }
            }
        }

        for (int i = 2; i < arr.length; i++) {
            for (int j = i-2; j >=0 ; j-=2) {
                if (arr[j]>arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >=0 ; j-=1) {
                if (arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }

         */
    }

    /**
     * 移位法的希尔排序牛逼
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for (int gap = arr.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < arr.length; i++) {//给arr[i]找位置，使用插入排序
                int insertIndex=i;
                int temp=arr[insertIndex];
                while (insertIndex-gap>=0 && temp<arr[insertIndex-gap]){
                    arr[insertIndex]=arr[insertIndex-gap];
                    insertIndex-=gap;
                }
                if (insertIndex!=i) {//如果要插入的元素已经比有序表的所有元素都大，即已经在所需位置
                    //则不交换
                    arr[insertIndex] = temp;
                }
            }
        }
        //System.out.println(Arrays.toString(arr));
    }
}
