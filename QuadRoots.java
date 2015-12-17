package com.example.helloworld;
import java.util.Scanner;
/**
 * Created by Prateek on 18-Aug-15.
 */
public class QuadRoots {
    public static void main(String[] args) {
        System.out.println("Enter coefficients for ax^2 +bx+c");
        Scanner key=new Scanner(System.in);
        double a,b,c,det,x,y;
        a=key.nextDouble();
        b=key.nextDouble();
        c=key.nextDouble();
        det=deter(a, b, c);
        if (det<0.0)
        {
            System.out.println("Imaginary Roots");
            x=-b/2*a;
            det = Math.abs(det);
            det=Math.sqrt(det)/2*a;
            System.out.println("Roots are " + x +"-" + det +"i"+ " and " + x +"+" + det+"i");
            return;
        }

        if (det==0.0)
        {
            System.out.println("Equal Roots");
            x=(-b+Math.pow(det,0.5))/2*a;
            System.out.println("Roots are "+ x);
            return;
        }

        System.out.println("Real Roots");
        x=(-b+Math.pow(det,0.5))/2*a;
        y=(-b-Math.pow(det,0.5))/2*a;
        System.out.println("Roots are "+ x + " and " + y);
        return;


    }

    public static double deter(double a,double b, double c)
    {
        double d;
        d=b*b-4*a*c;
        return d;
    }
}
