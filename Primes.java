package com.example.helloworld;

import java.util.Scanner;

/**
 * Created by Prateek on 25-Aug-15.
 */
public class Primes {
    public static void main(String[] args) {
        int i, j, n,ctr,way,attempts;
        System.out.println("Enter number");
        Scanner key = new Scanner(System.in);
        n = key.nextInt();
        System.out.println("Enter way");
        way =key.nextInt();
        if(isPrime(n,way)==1)
            System.out.println(n+" is prime.");
        else
            System.out.println(n+" is not prime.");
        System.out.println("Number of Operations " + Operation(n,way));
        System.out.println("Monte Carlo Approach");
        System.out.println("Enter number of attempts");
        attempts=key.nextInt();
        if(MonteCarlo(n,attempts)==1)
            System.out.println(n+" is prime.");
        else
            System.out.println(n+" is not prime.");
    }
    public static int isPrime(int num,int way)
    {
        int i,w=num-1;
        if (way==1)
            w=(int)Math.sqrt(num);
        if (way==2)
            w=(int)num/2;
        for(i=2;i<=w;i++){
            if(num%i==0)
                return 0;
        }
        return 1;
    }
    public static int Operation(int num,int way)
    {
        int i,w=num-1,ctr=0;
        if (way==1)
            w=(int)Math.sqrt(num);
        if (way==2)
            w=(int)num/2;
        for(i=2;i<=w;i++){
            ctr++;
            if(num%i==0)
                return ctr;
        }
        return ctr;
    }

    public static int MonteCarlo (int num, int attempts) {
        int i;
        while(attempts>0) {
            i = (int) (Math.random() * (Math.sqrt(num) - 2 + 1) + 2);
            if(num%i==0)
                return 0;
            attempts--;
        }
        return 1;
    }
}
