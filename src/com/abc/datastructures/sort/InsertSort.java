package com.abc.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr={101,34,119,1};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr= new int[80000];
        for (int i = 0; i < args.length; i++) {
            arr[i]=(int) (Math.random()*80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
    }

    /**
     * 把arr[1]到arr[arr.length-1]依次插入到前面的有序表中
     * @param arr
     */
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertValue=arr[i];//待插入的数
            int insertIndex=i-1;//给这个待插入的数找位置

            while (insertIndex>=0 && insertValue<arr[insertIndex]){//说明位置没找到
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex!=i-1){//如果待插入的数已经在恰当位置就不赋值
                arr[insertIndex+1]=insertValue;
            }
        }

    }
}
