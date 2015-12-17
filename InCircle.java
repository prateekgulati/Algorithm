package com.example.helloworld;
import java.util.Scanner;
/**
 * Created by Prateek on 18-Aug-15.
 */
public class InCircle {


    public static void main(String[] args) {
        System.out.println("Enter coordinates");
        Scanner key = new Scanner(System.in);
        double x, y,dis;
        x = key.nextDouble();
        y = key.nextDouble();
        dis=dist(x,y);
        if (dis<10.0)
            System.out.println("Point is inside the circle");
        else if (dis==10.0)
            System.out.println("Point is on the circle");
        else
            System.out.println("Point is outside the circle");
    }
    public static double dist(double x, double y)
    {
        double dis;
        dis=Math.sqrt(x*x+y*y);
        return dis;
    }
}
