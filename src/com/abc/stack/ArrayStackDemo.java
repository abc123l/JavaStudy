package com.abc.stack;

import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 * 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key="";
        boolean loop=true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show ：显示栈");
            System.out.println("exit ：退出程序");
            System.out.println("push ：入栈");
            System.out.println("pop ：出栈");
            System.out.println("请输入：");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println(arrayStack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();//这一步注意，用完流后要关闭，释放资源
                    loop=false;
            }
        }
        System.out.println("程序退出了");
    }
}

class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数据放在该数组中
    private int top = -1;//表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否满了
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断是否栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已经满了");
            return;
        }
        stack[++top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top--];
    }

    /**
     * 遍历，从栈顶开始显示
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}