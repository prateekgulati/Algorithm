package com.example.helloworld;

/**
 * Created by Prateek on 15-Sep-15.
 */
public class MagicSquare {
    public static void main(String[] args) {
    even(8);
    }
    public static void odd(int n) {
        int i = 0, j = n / 2, counter = 1;
        int A[][] = new int[n][n];
        A[i][j] = counter;
        counter++;
        for (; counter <= n * n; counter++) {
            j++;
            i--;
            if (i == -1)
                i = n - 1;
            j = j % n;
            if (A[i][j] == 0)
                A[i][j] = counter;
            else {
                i = i + 2;
                j--;
                if (j == -1)
                    j = n - 1;
                i = i % n;
                A[i][j] = counter;
            }
        }
        printArray2D(A,n);

    }
    public static void printArray2D(int A[][],int n){
        int i,j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print("  " + A[i][j]);
            System.out.println();
        }
    }
    public static void even(int n){
        int A[][]=new int[n][n];
        int msquare = n/4, cnt = 1, invCnt = n*n;
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(j>=msquare && j<n-msquare){
                    if(i>=msquare && i<n-msquare)
                        A[i][j] = cnt;    //central box
                    else
                        A[i][j] = invCnt; // up & down boxes

                }
                else if(i<msquare || i>=n-msquare){
                    A[i][j]=cnt;	         // 4 corners
                }
                else
                    A[i][j] = invCnt;  	// left & right boxes

                cnt++;
                invCnt--;
            }

        }
        printArray2D(A,n);
    }

}
