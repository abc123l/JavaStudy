package com.abc.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author abc
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class PolandNotation {
    public static void main(String[] args) {
        /*
        //先定义一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //数字和符号之间用空格隔开
        String suffixExpression="3 4 + 5 * 6 -";
        //先将该表达式放入一个ArrayList，再将此ArrayList传给一个方法，配合栈完成计算
        List<String> listString = getListString(suffixExpression);
        System.out.println(listString);

        System.out.println("计算的结果是："+calculate(listString));
         */
        String expression="1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);//转成中缀list
        System.out.println(infixExpressionList);

        System.out.println();

        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);//转成后缀list
        System.out.println(suffixExpressionList);

        System.out.println("1+((2+3)*4)-5的结果是："+calculate(suffixExpressionList));//后缀list计算
    }

    /**
     * 将一个中缀表达式的list转换为后缀表达式的list
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>();//符号栈
        ArrayList<String> s2 = new ArrayList<>();
        //存放中间结果的栈，因为该栈在整个操作中并没有pop的操作而且最终还要逆序输出所以就用ArrayList代替
        for (String item :ls) {
            if (item.matches("\\d+")){//数字直接入s2
                s2.add(item);
            } else if (item.equals("(")) {//左括号入s1
                s1.push(item);
            } else if (item.equals(")")) {//右括号就把s1的内容往s2弹，直到在s1中遇到左括号
                while (!s1.peek().equals("(")){//这里不用担心会不会把s1栈弹空的问题
                    s2.add(s1.pop());
                }
                s1.pop();//一定要记得消掉s1栈顶的左括号
            } else {
                //当item的优先级小于s1栈顶的优先级，就把s1的运算符往s2弹，直到遇到比自己优先级小的
                while (s1.size()!=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    //item的优先级小于等于栈顶的优先级
                    s2.add(s1.pop());
                }
                s1.push(item);//最后把item入s1
            }
        }
        //再将s1剩余的内容加入s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//不需要逆序，本身添加的顺序就是正确的顺序
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

    /**
     * 将一个中缀表达式转成一个list集合，这样是为了扫描
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s){
        List<String> ls=new ArrayList<>();
        int i=0;//扫描的索引
        char c;//遍历出来的字符
        String str;//用于多位数字拼接
        do {
            if ((c=s.charAt(i))<48 || (c=s.charAt(i))>57){//非数字
                ls.add(c+"");
                i++;
            }else {//数字，但要考虑多位数
                str="";//清空上次使用的拼接字符串
                while (i<s.length() && s.charAt(i) >=48 && s.charAt(i)<=57){
                    //后面的判断必不可少，可以确定s.charAt(i)为数字，但下一位是不是就不确定了
                    c=s.charAt(i);
                    str+=c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
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

/**
 * 返回运算符的优先级
 */
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    public static int getValue(String operation){
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                break;
        }
        return result;
    }

}
