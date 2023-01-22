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

        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        //测试

//        System.out.println("前序遍历");
//        binaryTree.preOrder();//12354
//
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();//21534
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();//25431

//        System.out.println("前序查找");//4次
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode!=null){
//            System.out.println("找到了："+resNode);
//        }else {
//            System.out.println("没有找到");
//        }
//        System.out.println("中序查找");//3次
//        HeroNode resNode = binaryTree.infixOrderSearch(5);
//        if (resNode!=null){
//            System.out.println("找到了："+resNode);
//        }else {
//            System.out.println("没有找到");
//        }
//        System.out.println("后序查找");//2次
//        HeroNode resNode = binaryTree.postOrderSearch(5);
//        if (resNode!=null){
//            System.out.println("找到了："+resNode);
//        }else {
//            System.out.println("没有找到");
//        }
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除后");
        binaryTree.preOrder();


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

    public HeroNode preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no){
        if (root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }
    public void delNode(int no){
        if (root!=null){
            if (root.getNo()==no){//要删除的节点为root就把该树置空
                root=null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树无法删除");
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

    /**
     * 前序查找
     * @param no
     * @return 找到的话返回此节点否则返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进行前序查找~~~");
        if (this.no==no){
            return this;
        }
        HeroNode resNode=null;//用来表示前面的栈返回的结果
        if (this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if (resNode!=null){//只有在找到的情况下才返回，没找到不能返回，因为还没有找右边的树
            return resNode;
        }
        if (this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        System.out.println("进入中序查找~~~");
        if (this.no==no){
            return this;
        }
        if (this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no){
        HeroNode resNode=null;
        if (this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if (resNode!=null){
            return resNode;
        }
        System.out.println("进入后序查找~~~");
        if (this.no==no){
            return this;
        }
        return resNode;
    }
    public void delNode(int no){
        if (this.left!=null && this.left.no==no){
            this.left=null;
            return;
        }
        if (this.right!=null && this.right.no==no){
            this.right=null;
            return;
        }
        if (this.left!=null){
            this.left.delNode(no);
        }
        if (this.right!=null){
            this.right.delNode(no);
        }
    }
}