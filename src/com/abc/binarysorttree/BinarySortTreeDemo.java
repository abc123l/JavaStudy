package com.abc.binarysorttree;

/**
 * @author abc
 * @version 1.0
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();

        //测试删除叶子节点
        binarySortTree.delNode(10);
        System.out.println("删除节点后：");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
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


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}