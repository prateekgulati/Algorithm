package com.example.helloworld;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Prateek on 06-Oct-15.
 */
public class SortInOn {
    public static void main(String[] args) {
        int max,i;
        int[] A= RandomArray(20,0,9);
        for(i=0;i<A.length;i++)
            System.out.print(" "+ A[i]);
        System.out.println();
        max=maximum(A);
        int[][] B=new int[max+1][2];
        for(i=0;i<A.length;i++)
        {
                B[A[i]][0] = A[i];
                B[A[i]][1] +=1 ;
        }
        printarray(B);
    }

    private static void printarray(int[][] b) {
        int i,j;
        for(i=0;i<b.length;i++)
            for(j=0;j<b[i][1];j++)
            System.out.print(" "+b[i][0] );
    }

    public static int maximum(int A[]){
        int max=A[0],i;
        for(i=0;i<A.length;i++)
            if(max<A[i])
                max=A[i];
        return max;
    }

    public static int [] RandomArray(int size,int low,int upp){
        int i;
        int A[]=new int [size];
        for (i=0;i<size;i++){

            A[i]=(int)(Math.random()*(upp-low+1));
        }
        return A;
    }
}