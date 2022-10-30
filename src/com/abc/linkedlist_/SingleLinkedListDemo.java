package com.abc.linkedlist_;

import java.util.Comparator;
import java.util.Stack;

/**
 * @author abc
 * @version 1.0
 * 单项链表的实现，注意flag的使用
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "ljy", "yql");
        HeroNode hero3 = new HeroNode(3, "wy", "zdx");
        HeroNode hero4 = new HeroNode(4, "lc", "bzt");

        singleLinkedList.add(hero1);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero4);

        //按编号顺序添加
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero3);
        System.out.println("修改前");
        singleLinkedList.show();

        //测试修改节点的代码
        HeroNode updateHeroNode = new HeroNode(1, "sj", "jsy");
        singleLinkedList.update(updateHeroNode);

        System.out.println("修改后");
        singleLinkedList.show();

        singleLinkedList.delete(1);
        System.out.println("删除后");
        singleLinkedList.show();


        System.out.println("链表有效元素是："+getLength(singleLinkedList.head));

        System.out.println(findLastIndexNode(singleLinkedList.head,4));//null

        reverseList(singleLinkedList.head);
        System.out.println("反转后");
        singleLinkedList.show();


        System.out.println("逆序打印");
        reversePrint(singleLinkedList.head);
    }

    //方法：获取到单链表的节点的个数（如果是带头节点的单链表,需要不统计头节点）

    /**
     *
     * @param head 链表的头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next==null){
            return -1;
        }
        int length=0;
        HeroNode temp=head.next;
        while (temp!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }

    /**
     * 找到单链表中倒数第index个节点
     * 先得到单链表有效节点的个数,再从第一个有效节点开始遍历length-index个节点返回即可
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next==null){
            return null;
        }
        int length = getLength(head);
        if (index<=0 || index>length){
            return null;
        }
        HeroNode temp=head.next;
        for (int i = 0; i < length-index; i++) {
            temp=temp.next;
        }
        return temp;
    }

    /**
     * 反转链表
     * 1.定义一个reverseHead
     * 2.遍历链表，每取出一个节点就把它放在reverseHead的最前端
     * 3.把head.next指向reverseHead.next
     * 这里用到的是双指针头插法反转单向链表
     * @param head
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空或者只有一个节点就不需要反转直接返回
        if (head.next==null || getLength(head)==1){
            return;
        }

        HeroNode cur=head.next;//工作节点
        HeroNode next=null;//指向当前节点的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");//临时头节点，把头节点和后面的节点断开
        while (cur!=null){
            next=cur.next;//先把cur的后一个节点保存起来，以便cur向后移动
            cur.next=reverseHead.next;//把cur的next指向rh的下一个节点相对于插入
            reverseHead.next=cur;//把rh的next指向当前节点，串起来
            cur=next;//cur后移
        }
        head.next=reverseHead.next;//最后把头接上
    }

    /**
     * 使用栈实现逆序打印单向链表，并没有改变链表本身结构
     * @param head
     */
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;//空链表
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur=head.next;
        while (cur!=null){
            stack.push(cur);//push和add都是入栈
            cur=cur.next;//后移
        }
        //打印栈中的节点即可
        while (stack.size()>0){
            System.out.println(stack.pop());//先进后出
        }
    }
}
class SingleLinkedList{
    //管理单项链表，有一个头节点，尾节点的next为空
    //初始化一个头节点，不存放具体数据，不能动
     HeroNode head=new HeroNode(0,"","");
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

    /**
     * 修改
     * @param heroNode 根据它的no在链表中找有没有相同的元素，如果有就修改
     */
    public void update(HeroNode heroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        boolean flag=false;//表示再链表中有没有找到传入的元素
        HeroNode temp=head;//遍历需要的辅助变量
        while (true){
            if (temp.no==heroNode.no){
                flag=true;//表示已经找到
                break;
            }
            if (temp.next==null){
                break;//找到链表的最后也没有找到，直接跳出
            }

            temp=temp.next;
        }
        //根据flag判断
        if (flag){
            temp.name= heroNode.name;
            temp.nickName= heroNode.nickName;
        }else {
            System.out.printf("没有找到，编号为%d的节点\n",heroNode.no);
        }
    }

    /**
     * 删除节点 单项链表 需要找到待删除节点的前一个节点才能执行删除操作
     * @param no 需要删除的节点
     */
    public void delete(int no){
        boolean flag=false;//表示是否找到要删除节点的前一个节点
        HeroNode temp=head;
        while (true){
            if (temp.next==null){
                break;
            }
            if (temp.next.no==no){//找到待删除节点的前一个节点
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            temp.next=temp.next.next;
        }else {
            System.out.printf("未找到编号为%d节点\n",no);
        }

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
