package com.abc.datastructures.linkedlist_;

/**
 * @author abc
 * @version 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "ljy", "yql");
        HeroNode2 hero3 = new HeroNode2(3, "wy", "zdx");
        HeroNode2 hero4 = new HeroNode2(4, "lc", "bzt");

//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.list();
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();

        System.out.println("测试修改");
        HeroNode2 heroNode2 = new HeroNode2(4, "abc", "你爹");
        doubleLinkedList.update(heroNode2);
        doubleLinkedList.list();

        System.out.println("测试删除");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 从头到尾遍历
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 添加一个节点到双向链表的最后
     *
     * @param heroNode2
     */
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    /**
     * 按传入节点的编号顺序添加
     * @param heroNode2
     */
    public void addByOrder(HeroNode2 heroNode2){
        if (heroNode2.no<=0){
            System.out.println("编号不能<=0");
            return;
        }
        HeroNode2 temp=head;
        boolean flag=false;//判断是否有相同元素，如果有就是true
        while (true){
            if (temp.next==null){
                break;
            }
            if (heroNode2.no<temp.next.no){
                break;
            }
            if (heroNode2.no==temp.next.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            System.out.println("链表已经存在相同编号的元素");
        }else {//要添加再temp节点的后面一个位置
            HeroNode2 tempNext=temp.next;//做一个临时变量保存temp的下一个节点，避免表示混淆

            temp.next=heroNode2;
            heroNode2.pre=temp;
            if (tempNext!=null) {
                tempNext.pre = heroNode2;
                heroNode2.next = tempNext;
            }

        }

    }

    /**
     * 修改双向链表，将传入节点的name和nickname属性覆盖找到节点的相应属性
     * @param heroNode2
     */
    public void update(HeroNode2 heroNode2) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        if (heroNode2.no == 0) {
            System.out.println("不可以修改头节点");
            return;
        }
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.no == heroNode2.no) {
                flag = true;
                break;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode2.name;
            temp.nickName = heroNode2.nickName;
        } else {
            System.out.println("未找到该节点");
        }
    }

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        boolean flag = false;
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;


        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {//如果要删除最后一个元素，if里面的语句不能执行，否则是空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("未找到该节点");
        }
    }


}

class HeroNode2 {
    int no;
    String name;
    String nickName;
    HeroNode2 next;
    HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
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