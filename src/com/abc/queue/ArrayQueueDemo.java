package com.abc.queue;

/**
 * @author abc
 * @version 1.0
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {

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
        front=-1;//指向队列头部的前一个位置
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
    }
    //显示头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没头");
        }
        return arr[++front];
    }
}