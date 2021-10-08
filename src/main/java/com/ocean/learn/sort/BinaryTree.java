package com.ocean.learn.sort;/**
 * @author chy
 * @date 2021/3/9 0009 下午 15:01
 * Description：
 */

import com.alibaba.fastjson.JSONObject;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 功能描述
 *
 * @author chy
 * https://blog.csdn.net/wang454592297/article/details/79472938
 * @date 2021/3/9 0009
 */
public class BinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(int value) {

        Node newNode = new Node(value);
        Node root = getRoot();
        Node current = root;
        Node parent;

        if (root == null) {
            setRoot(newNode);
            return;
        }
        while (true) {
            parent = current;
            if (current.getValue() > value) {
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(newNode);
                    return;
                }
            } else {
                current = current.getRight();
                if (current == null) {
                    parent.setRight(newNode);
                    return;
                }
            }
        }
    }


    public void insertRecursion(int value) {

        Node newNode = new Node(value);
        Node root = getRoot();

        if (root == null) {
            setRoot(newNode);
            return;
        }

        insertRecursion(root, newNode);

    }

    public void insertRecursion(Node root, Node newNode) {

        if (newNode.getValue() > root.getValue()) {
            if (root.getRight() == null) {
                root.setRight(newNode);
            } else {
                insertRecursion(root.getRight(), newNode);
            }
        } else {
            if (root.getLeft() == null) {
                root.setLeft(newNode);
            } else {
                insertRecursion(root.getLeft(), newNode);
            }
        }

    }

    // 递归遍历
    public static void PrintBinaryTreeMidRecur(Node root) {
        if (root != null) {
            PrintBinaryTreeMidRecur(root.getLeft());
            System.out.print(root.getValue() + " ");
            PrintBinaryTreeMidRecur(root.getRight());
        }
    }

    // while循环
    public static void PrintBinaryTreeMidRecurWhile(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                Node tem = stack.pop();
                System.out.print(tem.getValue() + " ");
                node = tem.getRight();
            }
        }
    }

    // while循环2
    public static void PrintBinaryTreeMidRecurWhile2(Node root) {
        Stack<Node> stack = new Stack<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            System.out.print(root.getValue() + " ");
            root = root.getRight();
        }


    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 4, 5, 2, 1, 0, 8, 7, 5, 5, 4, 4, 4, 5);

        BinaryTree binaryTree = new BinaryTree();

        for (Integer integer : integers) {
            binaryTree.insert(integer);
//            sortTree.insertRecursion(integer);
        }

        System.out.println(JSONObject.toJSONString(binaryTree));

        System.out.println("递归输出：");
        PrintBinaryTreeMidRecur(binaryTree.getRoot());

        System.out.println("\nwhile循环遍历输出：");
        PrintBinaryTreeMidRecurWhile(binaryTree.getRoot());

        System.out.println("\nwhile循环遍历输出2：");
        PrintBinaryTreeMidRecurWhile2(binaryTree.getRoot());
    }


    public class Node {

        private Integer value;

        private Node left;

        private Node right;


        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

    }


}
