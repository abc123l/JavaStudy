package com.abc.tree;

/**
 * @author abc
 * @version 1.0
 *
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建一些节点
        HeroNode root = new HeroNode(1, "a");
        HeroNode node2 = new HeroNode(2, "b");
        HeroNode node3 = new HeroNode(3, "c");
        HeroNode node4 = new HeroNode(4, "d");
        HeroNode node5 = new HeroNode(5, "d");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        //测试
        binaryTree.setRoot(root);
        System.out.println("前序遍历");
        binaryTree.preOrder();//12354

        System.out.println("中序遍历");
        binaryTree.infixOrder();//21534

        System.out.println("后序遍历");
        binaryTree.postOrder();//25431
    }
}
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    public void preOrder(){
        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //前序，中序，后序遍历方法
    public void preOrder(){
        System.out.println(this);//输出当前节点（父节点）
        if (this.left!=null){//左子树不为空
            this.left.preOrder();//递归左子树
        }
        if (this.right!=null){
            this.right.preOrder();//递归右子树
        }
    }

    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    public void postOrder(){
        if (this.left!=null){
            this.left.postOrder();
        }
        if (this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
}