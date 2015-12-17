package com.example.helloworld;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Prateek on 10-Nov-15.
 */
public class RandomMatrix {
    int[][] A,B;

    int len;
    RandomMatrix(int n){
        int i,j,k;
        A=new int[n][n];
        B=new int[n][n];
        for ( i=0;i<n;i++)
            for (j=0;j<n;j++)
                A[i][j]= (int) (Math.random()*10);
        for ( i=0;i<n;i++)
            for (j=0;j<n;j++)
                for (k=0;k<n;k++)
                B[i][j]+=A[i][k]*A[k][j];
    }

    private void printInitialArray() {
        int i,j;
        System.out.println("Initialized Array:->");
        for(i=0;i<this.A.length;i++) {
            for (j = 0; j < this.A.length; j++)
                System.out.print(" " + A[i][j]);
            System.out.println();
        }
    }
    private void printProdArray() {
        int i,j;
        System.out.println("Product Array:->");
        for(i=0;i<this.B.length;i++) {
            for (j = 0; j < this.B.length; j++)
                System.out.print(" " + B[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int size;
        Scanner key = new Scanner(System.in);
        System.out.println("Enter size of Matrix");
        size=key.nextInt();
    RandomMatrix Matrix= new RandomMatrix(size);
        Matrix.printInitialArray();
        System.out.println();
        Matrix.printProdArray();

    }
}
