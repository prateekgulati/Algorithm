package com.example.helloworld;

import javax.swing.*;
import java.util.Stack;

/**
 * Created by Prateek on 17-Oct-15.
 */
public class BinaryTree {

    private Node root;
    private int max_left,max_right,max;

    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int newData) {
            left = null;
            right = null;
            data = newData;
        }
    }

    BinaryTree() {
        root = null;
    }

    public void insert_Recursive(int data) {
        root=insert_Recursive(root,data);
    }

    public Node insert_Recursive(Node N, int data) {
        if (N == null) {
            N = new Node(data);
            return N;
        }

        if (N.data > data) {
            N.left = insert_Recursive(N.left, data);
        }
        if (N.data < data) {
            N.right = insert_Recursive(N.right, data);
        }
        return N;
    }


    int findmax(){
        return findmax(root);
    }

    int findmax(Node p){
        if(p!=null) {
            findmax(p.left);
            if(p.data>max)
                max_right=p.data;
            if (max_left > max_right)
                max = max_left;
            else max = max_right;
            findmax(p.right);
        }
        return max;

    }


    public void printInorder() {
        printInorder(root);
    }

    public void printInorder(Node N){
        Node temp=N;
        if(temp==null)
            return;
        printInorder(N.left);
        System.out.print(temp.data + "  ");
        printInorder(N.right);
    }

}

class TreeMainClass {
    public static void main(String[] args) {
        BinaryTree Tree = new BinaryTree();
        String str[];
        int data,i=0,num;
        num=Integer.parseInt(JOptionPane.showInputDialog("Input number of nodes "));
        while(num-->0){
            data=Integer.parseInt(JOptionPane.showInputDialog("Enter node "));
            Tree.insert_Recursive(data);
        }
        
        JOptionPane.showMessageDialog(null, " The max nodes is : " + Tree.findmax());
    }
}