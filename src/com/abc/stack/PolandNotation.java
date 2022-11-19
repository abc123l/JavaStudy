package com.abc.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author abc
 * @version 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //数字和符号之间用空格隔开
        String suffixExpression="3 4 + 5 * 6 -";
        //先将该表达式放入一个ArrayList，再将此ArrayList传给一个方法，配合栈完成计算
        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);

        System.out.println("计算的结果是："+calculate(listString));
    }

    /**
     * 此方法将表达式中的数字和运算符放入List中并返回
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele :split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for (String item :ls) {
            if (item.matches("\\d+")){//如果是数就入栈，使用的是正则表达式
                stack.push(item);
            }else {
                //pop出两个数并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res=0;
                if (item.equals("+")){
                    res=num1+num2;
                } else if (item.equals("-")) {
                    res=num1-num2;
                } else if (item.equals("*")) {
                    res=num1*num2;
                } else{
                    res=num1/num2;
                }
                stack.push(res+"");
            }
        }
        //最后留在栈里的就是结果
        return Integer.parseInt(stack.pop());
    }

}
