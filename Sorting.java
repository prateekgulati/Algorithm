package com.example.helloworld;
import java.util.ArrayList;
/**
 * Created by Prateek on 26-Aug-15.
 */
public class Sorting {
    static int steps=0;
    static int[] B=new int[7];
    public static void main(String[] args) {
        int i,j,n=7,max=0;
        int []A,temp;
        A=MyRandom.RandomUniqueArray(n,1,7);
        PrintArray(A);

        MergeSort(A);
        System.out.println("Steps in Sort are " + steps);

        //A=QuickSort(temp);
        PrintArray(A);

    }
    public static int[] BubbleSort(int A[]){
        int i,j,size=A.length;
        for(i=0;i<size-1;i++)
            for(j=0;j<size-1;j++)
            {
                if(A[j]<A[j+1])
                    A=swap(A,j,j+1);
            }
        return A;
    }

    public static int[] SelectionSort(int A[]) {
        int i,j,size=A.length,max,pos;
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
                swap(A,i,pos);
        }
    return A;
    }

    public static int[] InsertionSort(int A[]) {
        int i,j,size=A.length;
        for(i=0;i<size-1;i++)
            for (j=i;j>=0&&A[j+1]>A[j];j--)
                swap(A, j, j + 1);
        return A;
    }

    public static int[] MergeSort(int A[],int low,int upp) {
        int mid;
        if (low < upp) {
            mid=(low+upp)/2;
            MergeSort(A, low, mid);
            MergeSort(A, mid + 1, upp);
            Merge(A,low,mid,upp);
        }
        return A;
    }

    public static int[] MergeSort(int A[]){
        MergeSort(A, 0, A.length-1);
        return A;
    }

    public static int[] Merge(int A[],int low, int mid,int upp) {

        int i=low,j=low,k=mid+1,temp;
        while(j<=mid && k<=upp)
        {
            if(A[j]<=A[k]){
                B[i]=A[j];
                steps++;
                j++;
            }
            else{
                B[i]=A[k];
                steps++;
                k++;
            }
            i++;
        }
        if(j>mid)
        {
            for(temp=k;temp<=upp;temp++) {
                B[i] = A[temp];
                steps++;
                i++;
            }
        }
        else {
            for (temp = j; temp <= mid; temp++) {
                B[i] = A[temp];
                steps++;
                i++;
            }
        }
        for(temp=low;temp<=upp;temp++)
            A[temp]=B[temp];

        return A;
    }

    public static int[] QuickSort(int A[],boolean rand,int low,int upp){
        int p;
        if (rand) {
                if (low < upp) {
                    p = part(A, low, upp);
                    QuickSort(A, rand, low, p);
                    QuickSort(A, rand, p + 1, upp);
                }

                return A;
        }
        else {
            if (low < upp) {
                p = partrand(A, low, upp);
                QuickSort(A, rand, low, p);
                QuickSort(A, rand, p + 1, upp);
            }
            return A;
        }
    }

    public static int[] QuickSort(int A[],boolean rand){
        QuickSort(A, rand,0, A.length);
        return A;
    }

    public static int part(int A[],int low,int upp){
        int pivot,i,j;
        pivot=A[low];
        i=low;
        j=upp-1;
        while (true){
            while (A[i]<pivot&&i<j)
                i++;
            while (A[j]>pivot&&i<j)
                j--;
            if(i<j)
                swap(A, i, j);
            else break;
        }
        return j;
    }


    public static int partrand(int A[],int low,int upp){
        int pivot,i,j,pos;
        pos=(int)(Math.random()*(upp-low));
        pivot=A[pos];
        i=low;
        j=upp-1;
        while (true){
            while (A[i]<pivot&&i<j)
                i++;
            while (A[j]>pivot&&i<j)
                j--;
            if(i<j)
                swap(A, i, j);
            else break;
        }
        return j;
    }


    public static void PrintArray(int A[],int low,int upp) {
        int i;
        for(i=low;i<upp;i++)
            System.out.print(" " +A[i]);
        System.out.println();
    }

    public static void PrintArray(int A[]) {
        int size=A.length;
        PrintArray(A,0,size);
    }

    public static int[] swap(int A[],int pos1,int pos2) {
        int temp;
        steps++;
        temp=A[pos1];
        A[pos1]=A[pos2];
        A[pos2]=temp;
        return  A;
    }
}
