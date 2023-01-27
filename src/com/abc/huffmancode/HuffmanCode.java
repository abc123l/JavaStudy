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
    static StringBuilder stringBuilder = new StringBuilder();
    static int compensationZeroNumber;

    public static void main(String[] args) {
        //String content = "i like like like java do you like a java";
        String content="Then i go to play.";
        byte[] contentBytes = content.getBytes();
        byte[] zipCode = huffmanZip(contentBytes);
        System.out.println("字节数组："+Arrays.toString(zipCode));
        System.out.println("字典："+huffmanCodes);


        byte[] originalBytes = decode(huffmanCodes, zipCode);
        System.out.println(new String(originalBytes));
    }
    /*
    数据解压：
    1. 将zipCode转成1010100010111111110010001011....
    2. 将1010100010111111110010001011....对照赫夫曼编码表转成原始字符串
     */

    /**
     * 用字典和zipCode来得到contentBytes
     * @param huffmanCodes 字典
     * @param zipCode
     * @return 原始字符串对应的字节数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] zipCode){
        //得到zipCode对应的二进制字符串101010001011111.....
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < zipCode.length; i++) {
            byte b = zipCode[i];
            boolean flag=(i== zipCode.length-1);
            stringBuilder.append(byteToBitString(b,!flag));//到最后一个时才会考虑补位
        }
        System.out.println("解码二进制数："+stringBuilder);
        //利用字典把二进制字符串进行解码, 首先反转一下字典的key和value方便查询
        Map<String, Byte> map = new HashMap<>();
        Set<Map.Entry<Byte, String>> entries = huffmanCodes.entrySet();
        for (Map.Entry<Byte, String> entry : entries) {
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合存放byte，并不能直接创建一个byte数组，因为不知道大小
        List<Byte> list = new ArrayList<>();
        //暴力匹配
        for (int i = 0; i < stringBuilder.length(); ) {//注意这里不能写i++否则和下面i+=gap互斥
            int gap=1;//小计数器
            Byte temp=null;//存放匹配到的Byte
            while (true){
                String key = stringBuilder.substring(i, i+gap);
                temp = map.get(key);
                if (temp==null){//如果在字典中没有找到该key对应的value则继续往后找
                    gap++;
                }else {
                    break;
                }
            }
            list.add(temp);
            i+=gap;//让i移动到gap
        }
        byte[] result = new byte[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i]=list.get(i);
        }

        return result;
    }
    /**
     * 将一个字符转成二进制的字符串
     * @param b
     * @param flag 是否需要补高位
     * @return
     */
    private static String byteToBitString(byte b,boolean flag){



        //由于byte没有toBinaryString方法，所以要先把byte转成int
        int temp=b;

        if (flag) {//如果是zipCode的非最后一个字节元素需要补0，例如传过来b为1那么str为1前面没有0，
            // 而存储的zipCode之前的huffmanCode是有0的
            temp |= 256;//256的二进制 1 0000 0000 如果temp为1 那么temp经过此运算为1 0000 0001最后切割一下就可以了
        }
        String str = Integer.toBinaryString(temp);//返回的二进制的补码，但有4个字节的需要压缩
        if (flag || temp<0) {//如果最后一个元素为负数的话仍然需要切割
            return str.substring(str.length() - 8);
        }else {//只有是zipCode的最后一个元素且它为正数时才直接返回，不补位
            String compensationZero="";
            if (compensationZeroNumber>0){

                for (int i = 0; i < compensationZeroNumber; i++) {
                    compensationZero=compensationZero+"0";
                }
            }
            return compensationZero+str;
        }

    }

    /**
     * 赫夫曼编码，压缩
     * @param originalBytes 原始字节数组
     * @return 要发送的字节数组
     */
    private static byte[] huffmanZip(byte[] originalBytes){
        //根据原始的字节数组创建节点
        List<Node> nodes = getNodes(originalBytes);
        //根据节点创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据创建的赫夫曼树得到赫夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据原始字节数组和赫夫曼编码表得到要发送的字节数组
        byte[] zipCode = zip(originalBytes, huffmanCodes);
        return zipCode;
    }
    /**
     * 将原始字符串对应的字节数组，通过赫夫曼编码表生成要发送的字节数组
     *
     * @param originalBytes 原始的字节数组contentBytes
     * @param huffmanCodes
     * @return 转换后的要发送的字节数组，每8位一组，记作huffmanCodeBytes
     * 1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
     * 注意10101000存储的时候是补码，显示的话要转成原码
     */
    private static byte[] zip(byte[] originalBytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        //遍历originalBytes利用huffmanCodes存储要发的编码
        for (byte b : originalBytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //将10101000101111111100.....转成byte[]数组，首先计算要多少个字节来存储
        System.out.println("原始二进制数："+stringBuilder);
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {//每8位一存储
            String strByte;//临时存储8位数据
            if (i + 8 > stringBuilder.length()) {//代表数据不够8位了

                strByte = stringBuilder.substring(i);//代表从i取到末尾

                while (i < stringBuilder.length()&&"0".equals(stringBuilder.charAt(i)+"")  ){//如果最后不足8位的正数前面有0的话
                    compensationZeroNumber++;
                    i++;
                }

            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 用于获取霍夫曼编码，放在huffmanCodes里
     * 注意为什么要StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
     *
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
     *
     * @param huffmanTreeRoot
     * @return
     */
    private static Map<Byte, String> getCodes(Node huffmanTreeRoot) {
        if (huffmanTreeRoot == null) {
            return null;
        } else {
            getCodes(huffmanTreeRoot.left, "0", stringBuilder);
            getCodes(huffmanTreeRoot.right, "1", stringBuilder);
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