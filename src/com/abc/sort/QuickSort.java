package com.abc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 快速排序，速度比希尔快，但用到了递归，空间换时间
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr={-9,78,0,-9,-567,70};
//        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        int[] arr=new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) (Math.random()*80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        quickSort(arr,0, arr.length-1);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
    }
    public static void quickSort(int[] arr,int left,int right){
        if (left>=right){
            return;
        }
        int l=left;
        int r=right;
        int pivot=arr[l];//pivot是数组第一个值的值
        while (l<r){
            while (l<r && arr[r]>=pivot){//从右边往左边遍历，找到比pivot小的值，l<r必不可少否则越界
                r--;
            }
            if (l<r){//确保跳出上面while的是找到了比pivot更小的数而不是索引溢出
                arr[l]=arr[r];
            }
            while (l<r && arr[l]<=pivot){//从左边往右边遍历找到比pivot更大的数
                l++;
            }
            if (l<r){
                arr[r]=arr[l];
            }
            if (l>=r){
                arr[l]=pivot;
            }
        }
        quickSort(arr,left,r-1);
        quickSort(arr,r+1,right);
    }
}
