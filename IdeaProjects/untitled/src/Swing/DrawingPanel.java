package com.example.Algo_assignment;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Prateek on 24-Nov-15.
 */

public class DrawingPanel extends JPanel {

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Point p1,p2,p3,p4;
        p1 = new Point(50,50);
        p2 = new Point(200,50);
        p3 = new Point(200,100);
        p4 = new Point(50,100);

        int[] x = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
        int[] y = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};

        Polygon poly = new Polygon(x, y, x.length);
        g2d.draw(poly);

        p1.setLocation(p1.getX() * Math.cos(Math.toRadians(90)) - p1.getY() * Math.sin(Math.toRadians(90)),
                p1.getX() * Math.sin(Math.toRadians(90)) + p1.getY() * Math.cos(Math.toRadians(90)));
        p2.setLocation(p2.getX() * Math.cos(Math.toRadians(90)) - p2.getY() * Math.sin(Math.toRadians(90)),
                p2.getX() * Math.sin(Math.toRadians(90)) + p2.getY() * Math.cos(Math.toRadians(90)));
        p3.setLocation(p3.getX() * Math.cos(Math.toRadians(90)) - p3.getY() * Math.sin(Math.toRadians(90)),
                p3.getX() * Math.sin(Math.toRadians(90)) + p3.getY() * Math.cos(Math.toRadians(90)));
        p4.setLocation(p4.getX() * Math.cos(Math.toRadians(90)) - p4.getY() * Math.sin(Math.toRadians(90)),
                p4.getX() * Math.sin(Math.toRadians(90)) + p4.getY() * Math.cos(Math.toRadians(90)));

        int[] x2 = {(int) p1.getX(), (int) p2.getX(), (int)p3.getX(), (int) p4.getX()};
        int[] y2 = {(int) p1.getY(), (int) p2.getY(), (int)p3.getY(), (int) p4.getY()};

        Polygon poly2 = new Polygon(x2, y2, x2.length);
        g2d.draw(poly2);

    }

    public static void main(String[] args) {

        DrawingPanel d=new DrawingPanel();
        d.setSize(400, 300);
        //d.setLocationRelativeTo(null);
        //d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //d.setVisible(true);
        d.setVisible(true);
    }

}