package com.abc.huffmancode;

import java.util.*;

/**
 * @author abc
 * @version 1.0
 * 赫夫曼编码
 */
public class HuffmanCode {
    //生成赫夫曼树对应的赫夫曼编码
    //将赫夫曼编码表存放在Map<Byte,String>中，Byte为元素，String为赫夫曼树中从根节点到它的路径例如：<97,100>
    //存储路径时用StringBuilder
    static Map<Byte, String> huffmanCodes = new HashMap();
    static StringBuilder stringBuilder=new StringBuilder();
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);


        getCodes(huffmanTreeRoot);
        System.out.println("赫夫曼编码表："+huffmanCodes);
    }



    /**
     * 用于获取霍夫曼编码，放在huffmanCodes里
     * 注意为什么要StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
     * @param node          霍夫曼树的根节点
     * @param path          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String path, StringBuilder stringBuilder) {
        //这一步是必须的，将到非叶子节点路径的每一步都记录下来，方便到叶子节点之后回溯到上一层时往另一方向递归时有之前的路径保存
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(path);
        if (node != null) {
            if (node.data == null) {//证明不是非叶子节点
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {//到了叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }

        }
    }

    /**
     * 方法重载
     * @param huffmanTreeRoot
     * @return
     */
    private static Map<Byte, String> getCodes(Node huffmanTreeRoot){
        if (huffmanTreeRoot==null){
            return null;
        }else {
            getCodes(huffmanTreeRoot.left,"0",stringBuilder);
            getCodes(huffmanTreeRoot.right,"1",stringBuilder);
            return huffmanCodes;
        }
    }

    /**
     * 把一个字节数组转换成节点集合，每个节点包含该字符内容以继在字节数组中的出现次数
     *
     * @param bytes 要处理的字节数组
     * @return nodes集合
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        //使用Map来统计每个字符出现的次数
        Map<Byte, Integer> countFunc = new HashMap<>();
        for (byte data : bytes) {
            Integer dataCount = countFunc.get(data);
            if (dataCount == null) {
                countFunc.put(data, 1);
            } else {
                countFunc.put(data, dataCount + 1);
            }
        }
        //利用countFunc构建节点并放入nodes
        Set<Map.Entry<Byte, Integer>> entries = countFunc.entrySet();
        for (Map.Entry<Byte, Integer> entry : entries) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);//从小到大
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建出的新节点没有data只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            //把parent挂上左右节点
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }

    }
}

class Node implements Comparable<Node> {
    Byte data;//存储数据本身，比如'a'=>97
    int weight;//权重，在这里为数据出现次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}