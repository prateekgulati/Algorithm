package com.example.helloworld;

import java.util.Scanner;
/**
 * Created by Prateek on 19-Aug-15.
 */
public class LasVegas {
    public static void main(String[] args) {
        Scanner key=new Scanner(System.in);
        int n=500,i,j,zero=n,index,flag=1,ctr=0;
        int [] A=new int [n];
        A = initran(n);

        //for (i=0; i < n; i++)
        //{
        //   System.out.print(" " + A[i]);
        //}
        System.out.println("");

        ctr = rep(A, n);
        System.out.println("Steps Taken" + ctr);

    }
    public static int calc(int A[],int n)
    {
        int i,j,ctr=0;
        while(true) {
            ctr++;
            i=(int)(Math.random()*(n));
            j=(int)(Math.random()*(n));
            if(i!=j && A[i]==A[j])
                return ctr;
        }

    }
    public static int rep(int A[], int n) {
        int i, j,k, ctr = 0;
        int [] Arr=new int [n/2+1];
        Arr=new int [n/2+1];
        for (i = 0; i < n; i++)
        {
            for(j=0;j<n/2+1;j++)
            {
                ctr++;
                if (A[i] == Arr[j])
                    return ctr;
                if (Arr[j] == 0)
                {
                    Arr[j] = A[i];
                    break;
                }

            }
        }
        return ctr;
    }
    public static int[] initran(int n){
        int i,j,zero=n,index;
        int [] A=new int[n];
        //A=init(n);
        while(true)
        {
            index=(int)(Math.random()*(n));
            A[index]=1;

            zero=0;
            for (i=0;i<n;i++)
            {
                if (A[i]==1)
                    zero++;
            }
            if(zero==n/2)
                break;
        }
        for (i=0,j=n/2;i<n;i++)
        {
            if (A[i]==0) {
                A[i] = j;
                j++;
            }
        }
        return A;

    }

}
