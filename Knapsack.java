package com.example.helloworld;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Prateek on 08-Sep-15.
 */
public class Knapsack {
    public static void main(String[] args) {
        int m=15,n=7,i;
        double wt=0,profit=0;
        double[] W,P; //=new int [n];
        double[] R=new double[n];
        P= new double[]{10.0, 5.0, 15.0, 7.0, 6.0, 18.0, 3.0};
        W=new double[] {2.0,3.0,5.0,7.0,1.0,4.0,1.0};
        ArrayList<Double> sack=new ArrayList<Double>();
        ArrayList<Double> fraction=new ArrayList<Double>();
        for(i=0;i<n;i++){
            R[i]=(double)P[i]/W[i];
        }
        R=Sorter(R,P,W);
        System.out.println("Including Fractions");
        for(i=0;i<n;i++)
        {
            if (W[i]>(m-wt))
            {
                sack.add(W[i]);
                fraction.add(((m-wt)/W[i]));
                profit=profit+P[i]*((m-wt))/W[i];
                wt=wt+(m-wt);


            }
            if(wt>=m)
                break;
            if(W[i]<(m-wt)) {
                sack.add(W[i]);
                fraction.add(1.0);
                wt = wt + W[i];
                profit = profit + P[i];
            }
        }
        System.out.println("Weights included "+sack);
        System.out.println("Fraction of weight included "+fraction);
        System.out.println("Total Profit " + profit + " Total Weight " + wt);

        System.out.println("Taking 0 or 1\n");
        System.out.println("a)Largest Profit");
        Sorter2(P, W);
        sack.clear();profit=0;wt=0;


        for(i=0;i<n;i++)
        {
            if(W[i]>(m-wt))
                continue;
            if(W[i]<=(m-wt)) {
                sack.add(W[i]);
                wt = wt + W[i];
                profit = profit + P[i];
            }
        }
        System.out.println("Weights included "+sack);
        System.out.println("Total Profit " + profit + " Total Weight " + wt);

        System.out.println("\nb)Non decreasing Weight ");
        P= new double[]{10.0, 5.0, 15.0, 7.0, 6.0, 18.0, 3.0};
        W=new double[] {2.0,3.0,5.0,7.0,1.0,4.0,1.0};
        sack.clear();profit=0;wt=0;
        double prev=0;


        for(i=0;i<n;i++)
        {
            if(W[i]>(m-wt))
                continue;
            if(W[i]<=(m-wt)&&W[i]>=prev) {
                sack.add(W[i]);
                wt = wt + W[i];
                profit = profit + P[i];
                prev=W[i];
            }

        }
        System.out.println("Weights included " + sack);
        System.out.println("Total Profit " + profit + " Total Weight " + wt);
        System.out.println("\nc)Max profit per unit Capacity ");
        Sorter(R,P,W);
        sack.clear();profit=0;wt=0;
        for(i=0;i<n;i++)
        {
            if(W[i]>(m-wt))
                continue;
            if(W[i]<=(m-wt)) {
                sack.add(W[i]);
                wt = wt + W[i];
                profit = profit + P[i];
            }

        }

        System.out.println("Weights included " + sack);
        System.out.println("Total Profit " + profit + " Total Weight " + wt);

    }
    public static double[] Sorter(double A[], double[] P, double[] W) {
        int i,j,size=A.length,pos;
        double max,temp,temp1,temp2;
        for (i=0;i<size-1;i++)
        {
            pos=i;
            max=A[i];
            for(j=i+1;j<size;j++)
            {
                if(A[j]>max){
                    pos=j;
                    max=A[j];
                }
            }
            if(i!=pos)
            {
                temp=A[i];
                temp1=P[i];
                temp2=W[i];
                A[i]=A[pos];
                P[i]=P[pos];
                W[i]=W[pos];
                A[pos]=temp;
                P[pos]=temp1;
                W[pos]=temp2;

            }
        }
        return A;
    }
    public static double[] Sorter2(double A[], double[] W) {
        int i,j,size=A.length,pos;
        double max,temp,temp1,temp2;
        for (i=0;i<size-1;i++)
        {
            pos=i;
            max=A[i];
            for(j=i+1;j<size;j++)
            {
                if(A[j]>max){
                    pos=j;
                    max=A[j];
                }
            }
            if(i!=pos)
            {
                temp=A[i];
                temp2=W[i];
                A[i]=A[pos];
                W[i]=W[pos];
                A[pos]=temp;
                W[pos]=temp2;
            }
        }
        return A;
    }
}
