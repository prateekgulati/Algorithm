package com.example.helloworld;

/**
 * Created by Prateek on 22-Nov-15.
 */
public class NQueen {
    int[][] board;
    NQueen(int n){
        board=new int[n][n];
        placedAt();
        //board= new int[][]{{1, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
    }

    void printBoard(){
        int i,j;
        for(i=0;i<board.length;i++){
            for (j=0;j<board.length;j++)
                System.out.print(board[i][j] + "  ");
            System.out.println();
        }
    }

    boolean checkPosition(int row,int col){
        int i,j;
        for(j=0;j<col;j++) {
            if (board[row][j] == 1)
                return false;
        }
        for(i=row,j=col;i>=0&&j>=0;i--,j--){
            if(board[i][j]==1)
                return false;
        }
        for(i=row,j=col;i<board.length&&j>=0;i++,j--){
            if(board[i][j]==1)
                return false;
        }
        return true;
    }

    void placedAt(){
        if(placedAt(0)==false)
            System.out.println("No solution exist");
    }
    boolean placedAt(int col){
        int i;
        if(col>=board.length)
            return true;
        for(i=0;i<board.length;i++) {
            if (checkPosition(i, col)) {
                board[i][col] = 1;
                if (placedAt(col + 1) == true)
                    return true;
                else board[i][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NQueen q=new NQueen(5);
        //System.out.println("No solution possible");
        q.printBoard();
    }
}
