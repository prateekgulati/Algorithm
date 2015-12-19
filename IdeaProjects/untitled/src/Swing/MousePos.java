package com.example.Algo_assignment;

/**
 * Created by Prateek on 03-Nov-15.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MousePos extends JPanel {

    private final class MouseDrag extends MouseAdapter {
        private boolean dragging = false;
        private Point last;

        @Override
        public void mousePressed(MouseEvent m) {
            last = m.getPoint();
            dragging = isInsideEllipse(last);
            if (!dragging) {
                x = m.getX();
                y = m.getY();
                width = 50;
                height = 50;
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent m) {
            last = null;
            dragging = false;
            //repaint();
        }

        @Override
        public void mouseDragged(MouseEvent m) {
            int dx = m.getX() - last.x;
            int dy = m.getY() - last.y;
            if (dragging) {
                x += dx;
                y += dy;
            }
            last = m.getPoint();
            //repaint();
        }
    }

    private int x;
    private int y;
    private int width;
    private int height;

    private MouseDrag mouseDrag;

    public MousePos() {
        setBackground(Color.WHITE);
        mouseDrag = new MouseDrag();
        addMouseListener(mouseDrag);
        addMouseMotionListener(mouseDrag);
    }

    public boolean isInsideEllipse(Point point) {
        return new Ellipse2D.Float(x, y, width, height).contains(point);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(x, y, width, height);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(300, 300);
        jFrame.add(new MousePos());
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}