package com.example.helloworld;

import java.util.ArrayList;

/**
 * Created by Prateek on 13-Oct-15.
 */
public class Graph {
    public static void main(String[] args) {
        int E[][] = {{2, 1}, {6, 1}, {5, 1}, {6, 2}, {3, 2}, {4, 3}, {2, 4}, {4, 5}, {6, 5}, {3, 6}, {4, 6}};
        int i, j, k, max = 0, x, inorder, outorder, s = 2, e = 1;
        for (i = 0; i < E.length; i++) {
            if (max < E[i][0])
                max = E[i][0];
        }

        int Graph[][] = new int[max][max];
        int Adj[][] = Graph;

        for (i = 0; i < E.length; i++) {
            Graph[E[i][0] - 1][E[i][1] - 1] = 1;
        }
        for (i = 0; i < Graph.length; i++) {
            for (j = 0; j < Graph.length; j++)
                System.out.print(Graph[i][j] + "  ");
            System.out.println();
        }

        for (i = 0; i < Graph.length; i++) {
            for (j = 0; j < Graph.length; j++) {
                if (Graph[i][j] == 1)
                    System.out.print((i + 1) + "->" + (j + 1) + "  ");
            }
            System.out.println();
        }

        for (i = 0; i < Graph.length; i++) {
            inorder = 0;
            outorder = 0;
            for (j = 0; j < Graph.length; j++) {
                if (Graph[i][j] == 1)
                    outorder++;
                if (Graph[j][i] == 1)
                    inorder++;
            }
            System.out.println("Indegree of " + (i + 1) + " is " + inorder + " and Outdegree of " + (i + 1) + " is " + outorder);
        }
        //for(x=0;x<max;x++) {
        for (i = 0; i < Graph.length; i++) {
            for (j = 0; j < Graph.length; j++) {
                for (k = 0; k < Graph.length; k++) {

                    Adj[i][k] |= Adj[i][j] * Graph[j][k];

                }
            }
        }
        if (Adj[s][e] == 1)
            System.out.println("Path from " + s + " to " + e + "possible in length " );


        for (i = 0; i < Adj.length; i++) {
            for (j = 0; j < Adj.length; j++) {
                System.out.print(Adj[i][j] + "  ");
            }
            System.out.println();
        }
    }
//}
}
