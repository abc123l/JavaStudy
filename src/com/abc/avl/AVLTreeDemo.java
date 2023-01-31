package com.abc.avl;

/**
 * @author abc
 * @version 1.0
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr={4,3,6,5,7,8};要左旋转
        //int[] arr = {10, 12, 8, 9, 7, 6};//要右旋转
        int[] arr = {10, 11, 7, 6, 8, 9};//要双旋转
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("树的高度：" + avlTree.root.height());
        System.out.println("树的左子树的高度：" + avlTree.root.leftHeight());
        System.out.println("树的右子树的高度：" + avlTree.root.rightHeight());


    }
}

class AVLTree {
    Node root;


    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 找到以node位根节点的二叉排序树的最小值并删除
     *
     * @param node
     * @return 该最小值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的找左子节点
        while (target.left != null) {
            target = target.left;
        }
        //这是target就指向了最小的节点
        delNode(target.value);
        return target.value;
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        }
        Node targetNode = search(value);//找要删除的节点
        if (targetNode == null) {//没有找到
            return;
        }
        if (root.left == null && root.right == null) {//找到了但根节点的左右节点为空，即二叉排序树只有一个根节点
            root = null;
            return;
        }
        Node parent = searchParent(value);

        if (targetNode.left == null && targetNode.right == null) {//要删除的节点是叶子节点
            //判断要删除的节点是父节点的左子节点还是右子节点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {//删除的节点有两个子节点
            int rightTreeMin = delRightTreeMin(targetNode.right);//从左子树找最小的节点，找到之后把最小的节点删了
            targetNode.value = rightTreeMin;//再把该最小值赋给要删除的节点
        } else {//要删除的节点有一个子节点
            if (targetNode.left != null) {//要删除的节点有左子节点
                if (parent != null) {
                    if (parent.left.value == value) {//要删除的节点是父节点的左子节点
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {//只有一个根节点和左子节点时
                    root = targetNode.left;
                }
            } else {//要删除的节点有右子节点
                if (parent != null) {
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                } else {//只有一个根节点和右子节点时
                    root = targetNode.right;
                }
            }
        }

    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("顺序二叉树为空，无法遍历");
        } else {
            root.infixOrder();
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 排序二叉排序树添加
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {//添加的node的值小于当前节点的值
            if (this.left == null) {//当前节点的左边为空的话
                this.left = node;//挂在左边
            } else {//否则递归的向左子树添加
                this.left.add(node);
            }
        } else {//添加的node的值大于等于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }


        //添加完成之后如果右子树高度-左子树高度>1就要左旋转
        if (rightHeight() - leftHeight() > 1) {//每次添加都是从根节点添加的这里的左右子树高度都是相对于根节点而言的
            //如果右子树的左子树的高度大于右子树的右子树的高度，则要先对右子树进行右旋转
            if (right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;//这里必须要，满足一种情况并处理后就结束
        }
        //添加完成之后如果左子树高度-右子树高度>1就要右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果当前节点的左子树的右子树的高度大于左子树的左子树的高度，则要先对左子树进行左旋转
            if (left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
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

    /**
     * 查找要删除的节点
     *
     * @param value 要查找的节点的值
     * @return 找到返回该节点，找不到返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//要查找的节点的值小于当前节点的值
            if (this.left == null) {//如果当前节点的左节点为空则没找到
                return null;
            }
            return this.left.search(value);//否则向左递归查找
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        //如果当前节点就是要找的节点的父节点就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//没有父节点
            }
        }
    }

    /**
     * 统计以该节点为根节点的树的高度，很巧妙背下来
     * 左右子树的高度的最大值
     *
     * @return
     */
    public int height() {
        //+1必不可少，将高度累积起来
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 左旋转，详解见无边际app
     */
    private void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}