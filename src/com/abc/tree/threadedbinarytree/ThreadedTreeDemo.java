package com.abc.tree.threadedbinarytree;

/**
 * @author abc
 * @version 1.0
 * 线索化二叉树，中序
 */
public class ThreadedTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "a");
        HeroNode node2 = new HeroNode(3, "b");
        HeroNode node3 = new HeroNode(6, "c");
        HeroNode node4 = new HeroNode(8, "d");
        HeroNode node5 = new HeroNode(10, "e");
        HeroNode node6 = new HeroNode(14, "f");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        //线索化
        threadedBinaryTree.threadedNodes();


        //线索化测试：以10号节点为例
        HeroNode node5Left = node5.getLeft();
        HeroNode node5Right = node5.getRight();
        System.out.println("10号节点的前驱节点：" + node5Left);
        System.out.println("10号节点的后继节点：" + node5Right);
        System.out.println("6号的后继节点："+node3.getRight()+"\t"+node3.getRightType());
        System.out.println("8的前驱节点："+node4.getLeftType());

        //线索化后不能使用原来的遍历方式，因为原来的遍历方式的BaseCase是左边或右边为空，线索化后这样会出现死递归
        threadedBinaryTree.threadedList();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建当前节点的前驱节点的指针，再进行递归时，pre总是保留前一个节点
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }




    /**
     * 遍历线索化二叉树的方法
     * 注意到6的rightType为0
     */
    public void threadedList(){
        //定义一个变量存储当前遍历的节点，从root开始
        HeroNode node=root;
        while (node!=null){
            //循环遍历找到leftTye==1的节点，即8
            while (node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点就一直输出
            while (node.getRightType()==1){
                node=node.getRight();//获取当前节点的后继节点
                System.out.println(node);
            }
            //替换这个遍历的节点
            node=node.getRight();
        }
    }


    public void threadedNodes() {
        threadedNodes(root);
    }
    /**
     * 对二叉树进行中序线索化的方法
     * 先线索化左子树，再线索化当前节点，最后线索化右子树
     * 注意：对于最后一个节点6，由于pre != null && pre.getRight() == null时候才会给该节点设置rightType=1，所以
     * 进入不到这个if，最终导致6的rightType为0
     * @param node
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点时是在下一次处理的
        if (pre != null && pre.getRight() == null) {//前一个不为空并且前一个的右边为空
            pre.setRight(node);//让前驱节点的右指针指向当前节点
            pre.setRightType(1);
        }


        //非常重要，每次处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        threadedNodes(node.getRight());
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {//要删除的节点为root就把该树置空
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树无法删除");
        }
    }
}

@SuppressWarnings({"all"})
class HeroNode {
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
    public void preOrder() {
        System.out.println(this);//输出当前节点（父节点）
        if (this.left != null) {//左子树不为空
            this.left.preOrder();//递归左子树
        }
        if (this.right != null) {
            this.right.preOrder();//递归右子树
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     *
     * @param no
     * @return 找到的话返回此节点否则返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进行前序查找~~~");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;//用来表示前面的栈返回的结果
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//只有在找到的情况下才返回，没找到不能返回，因为还没有找右边的树
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序查找
     *
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找~~~");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序查找
     *
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找~~~");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}
