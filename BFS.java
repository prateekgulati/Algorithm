package com.example.helloworld;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Prateek on 21-Oct-15.
 */
public class BFS {

    int[][] Graph, MST;
    int[] Visited;//,Explored;
    int max;

    BFS(int E[][]) {
        int i;
        this.max = 0;
        for (i = 0; i < E.length; i++) {
            if (this.max < E[i][0])
                this.max = E[i][0];
            if (this.max < E[i][1])
                this.max = E[i][1];
        }

        this.Graph = new int[this.max][this.max];
        this.MST = new int[this.max][this.max];
        this.Visited = new int[this.max];
        //this.Explored = new int[this.max];

        for (i = 0; i < E.length; i++) {
            this.Graph[E[i][0] - 1][E[i][1] - 1] = 1;
            this.Graph[E[i][1] - 1][E[i][0] - 1] = 1;
        }
    }

    void printMatrix() {
        int i,j;
        System.out.println("Adjacency Matrix");
        for (i = 0; i < this.Graph.length; i++) {
            for (j = 0; j < this.Graph.length; j++)
                System.out.print(this.Graph[i][j] + "  ");
            System.out.println();
        }
    }

    void printMST() {
        int i, j;
        System.out.println("Minimal Spanning Tree");
        for (i = 0; i < this.MST.length; i++) {
            for (j = 0; j < this.MST.length; j++)
                System.out.print(this.MST[i][j] + "  ");
            System.out.println();
        }
    }

    void BFS() {
        int vertex, i;
        Queue<Integer> Vqueue = new LinkedList<Integer>();
        Vqueue.add(0);
        this.Visited[0] = 1;
        while (!Vqueue.isEmpty()) {
            vertex = Vqueue.remove();
            for (i = 0; i < this.max; i++) {
                //System.out.print("Vert " + vertex+" "+this.Graph[vertex][i] + " " + this.Visited[i]);
                //System.out.println();
                if (this.Graph[vertex][i] == 1 && this.Visited[i] == 0) {
                    this.MST[vertex][i] = 1;
                    Vqueue.add(i);
                    //System.out.print(Vqueue);
                    //Sorting.PrintArray(Visited);
                    this.Visited[i] = 1;
                }
            }
            //printMST();
            //Explored[vertex] = 1;
        }
    }

    void DFSiterative() {
        int vertex, i;
        Stack<Integer> Vstack = new Stack<Integer>();
        Vstack.add(0);
        this.Visited[0] = 1;
        vertex = 0;
        while (!Vstack.isEmpty()) {
            for (i = 0; i < this.max; i++) {
                if (this.Graph[vertex][i] == 1 && this.Visited[i] == 0) {
                    this.MST[vertex][i] = 1;
                    Vstack.push(vertex);
                    this.Visited[i] = 1;
                    vertex=i;
                    i=0;
                }
            }

            //Explored[vertex] = 1;
            //printMST();
            vertex = Vstack.pop();
        }
    }

    void DSearch() {
        int vertex, i;
        boolean flag;
        Stack<Integer> Vqueue = new Stack<Integer>();
        Vqueue.add(0);
        this.Visited[0] = 1;
        while (!Vqueue.isEmpty()) {
            vertex = Vqueue.pop();
            for (i = 0; i < this.max; i++) {
                //System.out.print("Vert " + vertex+" "+this.Graph[vertex][i] + " " + this.Visited[i]);
                //System.out.println();
                if (this.Graph[vertex][i] == 1 && this.Visited[i] == 0) {
                    this.MST[vertex][i] = 1;
                    Vqueue.push(i);
                    this.Visited[i] = 1;
                }
            }
            //Explored[vertex] = 1;
            //printMST();
        }
    }

    void DFS(int v) {
        int vertex, i;
        this.Visited[v] = 1;
        vertex = v;
        for (i = 0; i < this.max; i++) {
            if (this.Graph[vertex][i] == 1 && this.Visited[i] == 0) {
                this.MST[vertex][i] = 1;
                printMST();
                DFS(i);
            }
        }
        printMST();
    }
}

class BFSTest{
    public static void main(String[] args) {

        int E[][] = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {4,8}, {5,8}, {6,8}, {7, 8}};//Original
        //int E[][] = {{1, 3}, {3,2},{3,6},{ 4,2},{4,6},{6,8},{7,3},{5,8}};//BFS
        //int E[][] = {{1,4},{1,3},{2,4},{2,7},{3,5},{4,8},{5,7},{6,8}};

        BFS tree= new BFS(E);
        tree.printMatrix();
        tree.DFS(0);
        tree.printMST();

    }
}