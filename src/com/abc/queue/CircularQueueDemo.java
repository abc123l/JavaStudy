package com.abc.queue;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 * 用数组实现环形队列
 */
public class CircularQueueDemo {
    public static void main(String[] args) {
        CircularQueue arrayQueue = new CircularQueue(4);
        //有效数据最多为3
        char key=' ';//接受用户输入
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
    @Test
    public void test(){
        System.out.println(2%5);
    }
}
class CircularQueue{
    int maxSize;
    int front;//指向队列头
    int rear;//指向队列末尾元素的后一个位置
    int[] arr;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
        front=rear=0;
    }
    public boolean isEmpty(){
        return rear==front;
    }
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    public void addQueue(int num){
        if (isFull()){
            System.out.println("队列已满，无法添加");
            return;
        }
        arr[rear]=num;
        rear=(rear+1)%maxSize;//指向队列末尾元素的下一个位置
    }
    public int outQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的");
        }
        //因为return后面的代码无法执行，所以无法实现返回值后再操作front的值
        // 所以只能先将要返回的值保存起来，再操作front，最后再返回要出列的值
        int value=arr[front];
        front=(front+1)%maxSize;//指向队列头部
        return value;

    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，无法显示");
            return;
        }
        int validLength=(rear+maxSize-front)%maxSize;//数组里的真实元素个数
        for (int i = front; i < front+validLength; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);//环形，要取模
        }
    }
    public int headQueue(){
        if (isFull()){
            throw new RuntimeException("队列为空，没头");
        }
        return arr[front];
    }


}
