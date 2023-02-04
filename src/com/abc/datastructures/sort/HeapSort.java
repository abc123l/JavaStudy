package com.abc.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author abc
 * @version 1.0
 * 堆排序
 * 升序排列，用到大顶堆
 * O(n*log n)
 * 800万个随机数用时2s
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4,6,8,5,9};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr=new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int) (Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println(date1Str);
        heapSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println(date2Str);
    }

    public static void heapSort(int[] arr) {
        //将该数组搭建成堆
        buildHeap(arr, arr.length);
        int temp;
        for (int i = arr.length-1; i >=0 ; i--) {
            //将最后一个节点与根节点互换
            temp=arr[0];
            arr[0]=arr[i];
            arr[i]=temp;
            //以根节点为非叶子节点heapify。注意：长度为i说明每次都砍掉最后一个节点
            heapify(arr,0,i);//heapify的长度在逐渐减少
        }
    }

    /**
     * 搭建大顶堆完全二叉树，从最后一个节点的父节点开始依次递减到根节点
     * @param arr
     * @param length
     */
    private static void buildHeap(int[] arr,int length){
        int lastNodeParent=(length-1-1)/2;//length-1是最后一个节点的索引
        for (int i = lastNodeParent; i >=0 ; i--) {
            heapify(arr,i,length);
        }
    }


    /**
     * 将以i为非叶子节点的树调整成大顶堆
     * @param arr    要调整的数组
     * @param i      要对哪一个非叶子节点heapify
     * @param length 数组的长度
     */
    private static void heapify(int[] arr, int i, int length) {
        if (i >= length) {//base case
            return;
        }
        int c1 = 2 * i + 1;//左子节点
        int c2 = 2 * i + 2;//右子节点
        int max = i;//最大值的索引
        if (c1 < length && arr[c1] > arr[max]) {
            max = c1;
        }
        if (c2 < length && arr[c2] > arr[max]) {
            max = c2;
        }
        if (max != i) {
            //交换arr[i]和arr[max]
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            heapify(arr, max, length);
        }
    }


}
