package com.abc.tree;

/**
 * @author abc
 * @version 1.0
 * 顺序存储二叉树，数组当成二叉树遍历时，左子节点为2n+1,右子节点为2n+2,n为第几个元素，父节点为(n-1)/2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println();
        arrBinaryTree.infixOrder(0);//这种就还要传参数
    }
}
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 方法重载，真正调用的是这个方法，就不用传参数了！！！
     */
    public void preOrder(){
        preOrder(0);
    }
    /**
     * 把数组当成二叉树前序遍历
     * @param index 数组的索引
     */
    public void preOrder(int index){
        if (arr==null || arr.length==0){
            System.out.println("数组为空，不能前序遍历");
            return;
        }
        System.out.print(arr[index]+"\t");//输出自己
        if ((index*2+1)<arr.length){//向左递归
            preOrder(index*2+1);
        }
        if ((index*2+2)<arr.length){//向右递归
            preOrder(index*2+2);
        }
    }
    public void infixOrder(int index){
        if (arr==null || arr.length==0){
            System.out.println("数组为空，不能中序遍历");

            return;
        }

        if ((index*2+1)<arr.length){//向左递归
            infixOrder(index*2+1);
        }
        System.out.print(arr[index]+"\t");//输出自己
        if ((index*2+2)<arr.length){//向右递归
            infixOrder(index*2+2);
        }
    }
}