package com.abc.search;

/**
 * @author abc
 * @version 1.0
 * 二分查找，要求数组必须有序，从大到小或从小到大均可
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,8,10,89,1000,1234};
        System.out.println(binarySearch(arr,0,arr.length-1,5));
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
        int mid=left+right/2;
        int midValue=arr[mid];
        if (findValue>midValue){//向右递归
            return binarySearch(arr,mid+1,right,findValue);
        } else if (findValue<midValue) {//向左递归
            return binarySearch(arr,left,mid-1,findValue);
        }else {
            return mid;
        }
    }
}
