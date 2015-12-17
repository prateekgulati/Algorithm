package com.example.helloworld;

/**
 * Created by Prateek on 18-Aug-15.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] A={4,3,6,2,1,7,5};
        A=HeapSort(A,7);
        Sorting.PrintArray(A);


    }
    public static int[] HeapSort(int[] A, int n){
        Heapify(A,n);
        Sorting.PrintArray(A);
        int i,t;
        for(i=n-1;i>=1;i--){
            t=A[i];
            A[i]=A[0];
            A[0]=t;
            Sorting.PrintArray(A);
            Adjust(A,0,i-1);
        }
        return A;
    }

    public static int[]Heapify(int []A,int n) {
        int i;
        for (i=(n)/2;i>=0;i--) {
            //Sorting.PrintArray(A);
            Adjust(A, i, n-1);
        }
        return A;
    }

    public static int[] Adjust(int[] A, int i, int n){
        int j=2*i+1,item=A[i];
        while(j<=n){
            if(j<n && A[j]<A[j+1])
                j++;
            if (item>=A[j])
                break;
            A[j/2]=A[j];
            j=2*j;
        }
        A[j/2]=item;
        return A;
    }

}
