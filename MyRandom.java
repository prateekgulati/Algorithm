package com.example.helloworld;

import java.util.ArrayList;

/**
 * Created by Prateek on 26-Aug-15.
 */
public class MyRandom {
    public static int [] RandomUniqueArray(int size,int low,int upp){
        int i;
        int A[]=new int [size];
        ArrayList<Integer> L=new ArrayList<>(100);
        for(i=low;i<=upp;i++){
            L.add(i);
        }
        for (i=0;i<size;i++){

            A[i]=L.remove((int)(Math.random()*L.size()));
        }
        return A;
    }
    public static int [] RandomUniqueArray(int size) {
        return RandomUniqueArray(size, 1, 100);
    }
    public static int [] RandomUniqueArray() {
        return RandomUniqueArray(100, 1, 100);
    }
    public static int [] RandomUniqueArray(int size,int low) {
        return RandomUniqueArray(size, low, low+size);
    }
}
