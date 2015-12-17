package com.example.helloworld;

import java.util.Scanner;

/**
 * Created by Prateek on 25-Aug-15.
 */
public class Prime {
    public static void main(String[] args) {
        int i, j, n,ctr;
        System.out.println("Enter number");
        Scanner key = new Scanner(System.in);
        n = key.nextInt();
        for(i=2,ctr=0;i<n;i++) {
            j = rem(i);
            if (j == 1) {
                System.out.print(" " + i);
                ctr++;
            }
            if(ctr==8) {
                ctr=0;
                System.out.println("");
            }
        }
    }
    public static int rem(int num)
    {
        int i;
        for(i=2;i<=Math.sqrt(num);i++){
            if(num%i==0)
                return -1;
        }
        return 1;
    }
}
