package com.example.helloworld;
import java.util.Scanner;
/**
 * Created by Prateek on 18-Aug-15.
 */
public class Lottery {
    public static void main(String[] args) {
        System.out.println("Enter Your ticket number");
        Scanner key = new Scanner(System.in);
        int num,lot,lot1,lot2,num1,num2;
        num=key.nextInt();
        lot=(int)(Math.random()*90)+10;
        System.out.println("Lottery Number "+ lot);
        lot1=lot%10;
        lot2=lot/10;
        num1=num/10;
        num2=num%10;
        if (num==lot)
            System.out.println("You won Lottery Prize of 10,000");
        else if (num1==lot1 && num2==lot2)
            System.out.println("You won Lottery Prize of 6,000");
        else if (num1==lot1 ||num1==lot2 || num2==lot1 || num2==lot2)
            System.out.println("You won Lottery Prize of 1,000");
        else
            System.out.println("You lost");
    }
}
