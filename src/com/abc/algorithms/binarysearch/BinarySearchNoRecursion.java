package com.abc.algorithms.binarysearch;

/**
 * @author abc
 * @version 1.0
 * 非递归的二分查找
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr={1,3,8,10,11,67,100};
        System.out.println(binarySearch(arr,5));
        hanoi('A','B','C',3);

    }

    /**
     * 非递归的二分查找
     *
     * @param arr    默认升序
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {//while里面left,right,mid都在变化
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void hanoi(char a,char b,char c,int num){
        if (num==1){
            System.out.println(a+"->"+c);
        }else {
            hanoi(a,c,b,num-1);
            System.out.println(a+"->"+c);
            hanoi(b,a,c,num-1);
        }
    }
}
