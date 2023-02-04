package com.abc.datastructures.linkedlist_;

import java.util.Stack;

/**
 * @author abc
 * @version 1.0
 * 演示栈的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
