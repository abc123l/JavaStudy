package com.abc.search;

import java.util.ArrayList;

/**
 * @author abc
 * @version 1.0
 * 插值查找，和二分查找类似，区别在于mid计算公式不同，也需要有序，适用于数据均匀的情况下（如果不均匀不一定比二分查找好），查找速度快
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length-1, 1));
    }

    private static ArrayList<Integer> insertValueSearch(int[] arr, int left, int right, int findValue) {
        //findValue < arr[0] || findValue > arr[arr.length - 1]这两句必须要有，否则可能造成数组越界
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findValue > midValue) {//向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {//向左递归
            return insertValueSearch(arr, 0, mid - 1, findValue);
        } else {
            ArrayList<Integer> resultIndexList = new ArrayList<>();
            int temp = mid - 1;//做临时索引，往左找
            while (temp >= 0 && arr[temp] == findValue) {
                resultIndexList.add(temp);
                temp--;
            }
            resultIndexList.add(mid);
            //往右找
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findValue) {
                resultIndexList.add(temp);
                temp++;
            }
            return resultIndexList;
        }

    }
}
