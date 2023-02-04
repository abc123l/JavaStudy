package com.abc.datastructures.linkedlist_;

/**
 * @author abc
 * @version 1.0
 * 约瑟夫问题的一个案例
 * 有nums个人，从第startNo个人开始报数，报CountNum数字，叫到的人出列，求出列顺序
 */
public class Josepfu {
    public static void main(String[] args) {
        CircularSingleLinkedList circularSingleLinkedList = new CircularSingleLinkedList();
        circularSingleLinkedList.addBoy(125);
        circularSingleLinkedList.showBoy();

        System.out.println("******************************");
        circularSingleLinkedList.countBoy(10,20,125);
    }
}
class CircularSingleLinkedList{//单向环形链表
    //创建一个first节点，当前没有编号
    private Boy first=null;

    /**
     * 添加节点的方法
     * @param nums 总共需要添加的数量
     */
    public void addBoy(int nums){
        if (nums<1){
            System.out.println("数量不能小于1");
            return;
        }
        Boy curBoy=null;//辅助指针，帮助构建环形链表，不能用first来创建或者遍历链表，会打乱原来的结构
        for (int i = 1; i <= nums; i++) {
            Boy boy =new Boy(i);
            if (i==1){//第一个节点单独考虑
                first=boy;
                first.setNext(first);//构成一个环状
                curBoy=first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    /**
     * 显示单向环形链表
     */
    public void showBoy(){
        if (first==null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy=first;
        while (true){
            System.out.println("小孩的编号为："+curBoy.getNo());
            if (curBoy.getNext()==first){
                break;
            }
            curBoy=curBoy.getNext();//记得让指针后移
        }
    }

    /**
     * 解决方案
     * @param startNo 从第几个小孩开始数
     * @param countNum 数几下
     * @param nums 开始总共有几个小孩
     */
    public void countBoy(int startNo,int countNum,int nums){
        //数据校验
        if (first==null || startNo<1 || startNo>nums || countNum > nums){
            System.out.println("参数有误");
            return;
        }
        //创建一个辅助指针helper，使它指向最后一个节点
        Boy helper =first;
        while (true){
            if (helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        //报数前，让first定位到startNo的位置(移动startNo-1次)，helper始终跟在他后面
        for (int i = 0; i < startNo-1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        //报数时，让first和helper同时移动countNum-1次，然后出圈
        while (true){
            if (helper==first){//只有一个节点
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向要出圈的小孩
            System.out.println("小孩"+first.getNo()+"出圈");
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号："+first.getNo());
    }
}
class Boy{//表示一个节点
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}