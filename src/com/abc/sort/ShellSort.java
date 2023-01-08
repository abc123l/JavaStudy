package com.abc.sort;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        shellSort(arr);




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
            System.out.println("第"+(++count)+"轮希尔排序后："+Arrays.toString(arr));
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
}
