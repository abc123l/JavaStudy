package com.abc.datastructures.stack;

/**
 * @author abc
 * @version 1.0
 * 中缀表达式实现计算器
 */
@SuppressWarnings({"all"})
public class Calculator {
    public static void main(String[] args) {
        String expression="70+2*6-4";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义一个索引用来扫描
        int index=0;
        int oper=0;
        int num1=0;
        int num2=0;
        int res=0;
        char ch=' ';//每次扫描得到char保存到ch
        String keepNum="";//用于拼接多位数
        while (true){
            ch=expression.substring(index,index+1).charAt(0);//得到一个字符
            if (operStack.isOper(ch)){//该字符是运算符
                if (!operStack.isEmpty()){//符号栈不为空的话
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //该字符的优先级小于等于栈顶的优先级,从数栈pop出两个数和从符号栈pop出一个数进行运算
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        //然后把当前操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //该字符的优先级大于栈顶的优先级
                        operStack.push(ch);
                    }
                }else {//符号栈为空
                    operStack.push(ch);
                }
            }else {//这个不能删除，否则符号位也进入了keepNum+=ch;
                //如果是数字直接入数栈
                //numStack.push(ch-48);//ASCII的'1'和1之间差了48


                //如果是多位数
                //注意不能直接入栈，需要向expression的index后再看一位，如果是运算符就直接入栈
                //如果ch是expression的最后一位就直接入栈
                keepNum+=ch;
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {//这个不能删，否则到最后一个字符（数字）还在判断下一位是不是符号位

                    if (numStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //注意拼接完之后一定要清空
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index==expression.length()){
                break;
            }
        }
        //扫描表达式完成，就顺序从数栈和符号栈中pop出相应的数和符号进行运算
        while (true){
            if (operStack.isEmpty()){//如果符号栈为空，则计算结束
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //计算结束后留在栈顶的就是最终计算结果
        System.out.println("计算结果为："+numStack.pop());
    }
}
@SuppressWarnings({"all"})
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数据放在该数组中
    private int top = -1;//表示栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 看一下栈顶值但不是真正出栈
     * @return
     */
    public int peek(){
        return stack[top];
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

    /**
     * 判断优先级
     * @param oper '+' '-' '*' '/'
     * @return 数字越大优先级越高，-1为传入数据有误
     */
    public int priority(int oper){
        if(oper=='*' || oper=='/'){
            return 1;
        } else if (oper=='+' || oper=='-') {
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 判断是否为运算符
     * @param val
     * @return
     */
    public boolean isOper(char val){
        return val=='+' || val=='-' || val=='*' || val=='/';
    }

    /**
     * 两个数的计算方法
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1,int num2,int oper){
        int result=0;
        switch (oper){
            case '+':
                result=num2+num1;
                break;
            case '-':
                result=num2-num1;
                break;
            case '*':
                result=num2*num1;
                break;
            case '/':
                result=num2/num1;
                break;
            default:
                break;
        }
        return result;
    }
}