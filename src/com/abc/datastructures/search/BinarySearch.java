package com.abc.datastructures.search;

import java.util.ArrayList;

/**
 * @author abc
 * @version 1.0
 * 二分查找，要求数组必须有序，从大到小或从小到大均可
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,8,10,89,1000,1000,1000,1234};
        ArrayList<Integer> result = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(result);

    }

    /**
     * 二分查找
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return -1为没找到
     */
    private static int binarySearch(int[] arr,int left,int right,int findValue){
        if (left>right){
            return -1;
        }
        int mid=(left+right)/2;
        int midValue=arr[mid];
        if (findValue>midValue){//向右递归
            return binarySearch(arr,mid+1,right,findValue);
        } else if (findValue<midValue) {//向左递归
            return binarySearch(arr,left,mid-1,findValue);
        }else {
            return mid;
        }
    }

    /**
     * 可以查找到所有的索引
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return 如果没找到就返回空集合
     */
    private static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findValue){

        if (left>right){
            return new ArrayList<Integer>();
        }
        int mid=(left+right)/2;//注意一定要加括号
        int midValue=arr[mid];
        if (findValue>midValue){//向右递归
            return binarySearch2(arr,mid+1,right,findValue);
        } else if (findValue<midValue) {//向左递归
            return binarySearch2(arr,left,mid-1,findValue);
        }else {//不要立即返回
            ArrayList<Integer> resIndexList = new ArrayList<>();

            int temp=mid-1;
            //向左扫描
            while (temp>=0 && arr[temp]==findValue){
                resIndexList.add(temp);
                temp--;//必不可少
            }
            resIndexList.add(mid);//这样返回的集合就是有顺序的
            //向右扫描
            temp=mid+1;
            while (temp<=arr.length-1 && arr[temp]==findValue){
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
