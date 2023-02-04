package com.abc.datastructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author abc
 * @version 1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        preOrder(createHuffmanTree(arr));

    }

    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("the tree is null");
        }
    }
    /**
     *
     * @param arr
     * @return 返回赫夫曼树的头节点
     */
    public static Node createHuffmanTree(int[] arr){
        //遍历该数组，并把每个元素创建为一个Node放入ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size()>1) {//当nodes中处理到只有一个节点时退出
            //排序：从小到大
            Collections.sort(nodes);

            //取出根节点权值最小的两个二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //在nodes中删除已处理过的最小的两个二叉树并把新创建的parent加入
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

/**
 * 因为建立赫夫曼树过程中要比较各个节点，所以要实现一个接口
 */
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;//升序
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}