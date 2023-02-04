package com.abc.datastructures.hashtab;

import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Scanner scanner = new Scanner(System.in);
        String key="";
        while (true){
            System.out.println("add  添加雇员");
            System.out.println("list 显示雇员");
            System.out.println("find 查找雇员");
            System.out.println("del  删除雇员");
            System.out.println("exit 退出系统");
            key=scanner.next();
            switch (key){
                case "add":
                    System.out.print("请输入雇员id：");
                    int id=scanner.nextInt();
                    System.out.print("请输入雇员名字：");
                    String name=scanner.next();
                    hashTab.add(new Emp(id,name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.print("请输入要查找的id：");
                    id=scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的id：");
                    id=scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;//默认为空
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    public HashTab(int size) {//用于初始化链表个数
        this.size=size;
        empLinkedListArray=new EmpLinkedList[size];
        //有问题，这里new了之后七个链表均为空，使用empLinkedListArray[empLinkedListNo].add(emp)等方法时会报空指针异常
        //需要分别初始化每条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    /**
     * 根据员工的id号来决定加入到哪一条链表
     * @param emp
     */
    public void add(Emp emp){
        int empLinkedListNo= hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    /**
     * 散列函数，取模法
     * @param id 雇员的id
     * @return 雇员应当加入哪一条链表
     */
    public int hashFun(int id){
        return id%size;
    }

    /**
     * 遍历所有的链表，即遍历哈希表
     */
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findById(int id){
        int empLinkedListNo = hashFun(id);//确定到哪条链表查找
        Emp emp = empLinkedListArray[empLinkedListNo].findByValue(id);
        if(emp!=null){
            System.out.println("在第"+empLinkedListNo+"条链表找到该雇员");
        }
    }

    public void deleteById(int id){
        int empLinkedListNo = hashFun(id);//确定到哪条链表查找
        if(empLinkedListArray[empLinkedListNo].delEmpById(id)){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
}
class EmpLinkedList{
    private Emp head;//头指针，指向第一个

    /**
     * 添加，默认为加到最后
     * @param emp
     */
    public void add(Emp emp){
        if (head==null){//如果添加第一个的话直接会给head
            head=emp;
            return;
        }
        Emp curEmp=head;//用于找到链表的最后一个节点
        while (curEmp.next!=null){
            curEmp=curEmp.next;
        }
        curEmp.next=emp;
    }

    /**
     * 遍历链表
     */
    public void list(int no){
        if (head==null){
            System.out.println("第"+no+"条链表为空");
            return;
        }
        Emp curEmp=head;
        while (curEmp!=null){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            curEmp=curEmp.next;
        }
        System.out.println();
    }

    /**
     * 通过id查找雇员
     * @param id 员工id
     * @return
     */
    public Emp findByValue(int id){
        if (head==null){
            System.out.println("链表为空，无法查询");
            return null;
        }
        Emp curTemp=head;
        while (true){
            if (curTemp.id==id){
                break;
            }
            if (curTemp.next==null){//没有找到
                System.out.println("没有找到该id的雇员");
                curTemp=null;
                break;
            }
            curTemp=curTemp.next;
        }
        return curTemp;
    }

    public boolean delEmpById(int id){
        if (head==null){
            System.out.println("链表为空无法删除");
            return false;
        }
        if (head.id==id){//如果要删除第一个元素
            head=head.next;
            return true;
        }
        Emp curEmp=head;
        boolean flag=false;//有没有在链表中找到该id
        while (true){
            if (curEmp.next==null){//到链表末尾了
                break;
            }
            if (curEmp.next.id==id){
                flag=true;
                break;
            }
            curEmp=curEmp.next;
        }
        if (flag){
            curEmp.next=curEmp.next.next;
            return true;
        }else {
            return false;
        }
    }

}