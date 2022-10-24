package com.abc.linkedlist_;

import java.util.Comparator;

/**
 * @author abc
 * @version 1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "ljy", "yql");
        HeroNode hero3 = new HeroNode(3, "wy", "zdx");
        HeroNode hero4 = new HeroNode(4, "lc", "bzt");
        /* 顺序添加
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
         */
        //按编号顺序添加
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.show();
    }
}
class SingleLinkedList{//管理单项链表，有一个头节点，尾节点的next为空
    //初始化一个头节点，不存放具体数据，不能动
    private HeroNode head=new HeroNode(0,"","");
    //完成添加方法
    public void add(HeroNode heroNode){
        //当不考虑编号的顺序时，找到链表的最后节点，此节点的next指向新节点即可
        //因为头节点不能动，所以需要一个辅助节点
        HeroNode temp=head;

        //遍历链表，找到最后
        while (true){
            if (temp.next==null){//说明已经到最后
                break;
            }

            temp=temp.next;
        }//当退出此循环时，temp指向链表最后
        temp.next=heroNode;
    }

    /**
     * 按照节点的no顺序加入，如果相同则不加入
     * 此实现相当漂亮，建议学习
     * @param heroNode 传入的节点
     */
    public void addByOrder(HeroNode heroNode){
        HeroNode temp=head;
        boolean flag=false;//判断是否有两个编号相同的元素
        //因为是单链表，所以找的temp是位于添加位置的前一个节点，否则插入不了
        while (true){
            if (temp.next==null){
                break;//说明已经到链表末尾还没有找到比heroNode的no更大的元素
            } else if (temp.next.no> heroNode.no) {//说明要加在temp后面
                break;
            } else if (temp.next.no== heroNode.no) {
                flag=true;
                break;
            }
            temp=temp.next;//遍历
        }
        if (flag){
            System.out.println("待插入的人物编号已存在");
            return;
        }
        heroNode.next=temp.next;
        temp.next=heroNode;
    }



    //显示链表，还是需要一个辅助变量
    public void show(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while (true){
            if (temp==null){//注意不能时temp.next，否则链表只有一个元素的情况下无法打印
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }
}
class HeroNode{
    int no;
    String name;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                //如果加上next的话每打印一个元素就会把后面的元素全部打完！！！
                '}';
    }



}
