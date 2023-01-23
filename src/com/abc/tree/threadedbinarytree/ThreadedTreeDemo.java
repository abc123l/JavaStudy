package com.abc.tree.threadedbinarytree;

/**
 * @author abc
 * @version 1.0
 * 线索化二叉树，中序
 */
public class ThreadedTreeDemo {
    public static void main(String[] args) {

    }
}
class ThreadedBinaryTree{
    private HeroNode root;
    //为了实现线索化，需要创建当前节点的前驱节点的指针，再进行递归时，pre总是保留前一个节点
    private HeroNode pre;
    public void setRoot(HeroNode root) {
        this.root = root;
    }



    /**
     * 对二叉树进行中序线索化的方法
     * 先线索化左子树，再线索化当前节点，最后线索化右子树
     * @param node
     */
    public void threadedNodes(HeroNode node){
        if (node==null){
            return;
        }
        threadedNodes(node.getLeft());

        if (node.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点时是在下一次处理的
        if (pre!=null && pre.getRight()==null){//前一个不为空并且前一个的右边为空
            pre.setRight(node);//让前驱节点的右指针指向当前节点
            pre.setRightType(1);
        }


        //非常重要，每次处理一个节点后，让当前节点是下一个节点的前驱节点
        pre=node;
        threadedNodes(node.getRight());
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
@SuppressWarnings({"all"})
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    private int leftType;//0表示左子树，1表示前驱节点
    private int rightType;//0表示右子树，1表示后继节点
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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
