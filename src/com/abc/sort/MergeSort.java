package com.abc.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 归并排序
 * youtube
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr=new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) (Math.random()*80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        mergeSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
//        System.out.println("before");
//        System.out.println(Arrays.toString(arr));
//        mergeSort(arr);
//        System.out.println("\nafter");
//        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr){
        //第一步，将数组分割成一个个长度为1的小数组
        int arrLength= arr.length;
        if (arrLength<2){
            return;
        }
        int midIndex=arrLength/2;
        int[] leftHalf=new int[midIndex];
        int[] rightHalf=new int[arrLength-midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i]=arr[i];
        }
        for (int i = midIndex; i < arrLength; i++) {
            rightHalf[i-midIndex]=arr[i];
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(arr,leftHalf,rightHalf);//合并
    }

    /**
     * 用于将
     * @param arr
     * @param leftHalf
     * @param rightHalf
     */
    private static void merge(int[] arr,int[] leftHalf,int[] rightHalf){
        int leftSize=leftHalf.length;
        int rightSize= rightHalf.length;

        int i=0,j=0,k=0;//用来扫描leftHalf,rightHalf,arr
        while (i<leftSize && j<rightSize){//当左边数组或右边数组比较完后退出
            if (leftHalf[i]<=rightHalf[j]){
                arr[k]=leftHalf[i];
                i++;
            }else {
                arr[k]=rightHalf[j];
                j++;
            }
            k++;
        }
        //将有剩余元素的数组的元素加入到arr数组当中
        while (i<leftSize){
            arr[k]=leftHalf[i];
            i++;
            k++;
        }
        while (j<rightSize){
            arr[k]=rightHalf[j];
            j++;
            k++;
        }
    }
}
