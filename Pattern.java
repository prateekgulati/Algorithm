package com.example.helloworld;

import java.util.Scanner;

/**
 * Created by Prateek on 25-Aug-15.
 */
public class Pattern {
    public static void main(String[] args) {

        int i,j,n;
        System.out.println("Enter number");
        Scanner key = new Scanner(System.in);
        n = key.nextInt();
        for(i=1;i<=n;i++) {
            for (j = 1; j <= i; j++)
                System.out.print(" " + j);
            System.out.println("");
        }

    }
}
