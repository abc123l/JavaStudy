package com.abc.queue;

import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key=' ';//接收用户输入
        boolean loop=true;
        Scanner scanner=new Scanner(System.in);
        while (loop){
            System.out.println("s 显示队列");
            System.out.println("e 退出程序");
            System.out.println("a 添加数据到队列");
            System.out.println("g 从队列取出数据");
            System.out.println("h 队列的头");
            key=scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();//退出之前要把扫描器关闭
                    loop=false;
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int result=arrayQueue.outQueue();
                        System.out.println("取出的数据是："+result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head=arrayQueue.headQueue();
                        System.out.println("队列的头是："+head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }


        }
        System.out.println("程序退出");
    }
}
class ArrayQueue{
    private int maxSize;//队列的最大容量
    private int rear;//指向后一个的指针
    private int front;//指向前面的指针
    private int[] arr;//数组队列

    public ArrayQueue(int maxSize) {//初始化操作在构造器里完成
        this.maxSize = maxSize;
        arr=new int[maxSize];//创建队列
        front=-1;//指向队列头部的前一个位置！！！！
        rear=-1;//指向队列尾，就是队列最后一个数据
    }
    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //添加数据到队列，入列
    public void addQueue(int num){
        if (isFull()){
            System.out.println("队列已满，不能加入");
            return;
        }
        arr[++rear]=num;
    }
    //获取队列数据，出列
    public int outQueue(){
        if (isEmpty()){
            //return;不能return，否则后面必须跟值，应当抛出异常
            throw new RuntimeException("队列为空，无法取数据");
        }
        return arr[++front];
    }
    //显示队列的所有数据
    public void showQueue(){
        //注意，仍然要判断队列是否为空
        if (isEmpty()){
            System.out.println("队列为空，无法显示");
            return;
        }
        for (int data :arr) {
            System.out.printf("%d\t",data);
        }
        System.out.println();//换行
    }
    //显示头数据
    public int headQueue(){//注意arr的索引
        if (isEmpty()){
            throw new RuntimeException("队列为空，没头");
        }
        return arr[front+1];//注意这里不能是front++，因为如果是front++相当于改变了front的值，这就不是在查看数据
        //而是在取出数据
    }
}