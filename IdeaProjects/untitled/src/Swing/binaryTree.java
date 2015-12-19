package com.example.Algo_assignment;

/**
 * Created by Prateek on 12-Nov-15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class binaryTree extends JFrame {
    private JPanel buttonPanel;                     //Panel has all buttons
    private JPanel circleInputPanel;                //Left panel for input nodes
    private JPanel circleOutputPanel;               //Right panel for output nodes
    private JLabel sequenceLabel;
    private Point currentPoint,nextPoint;           //Points between which line is to be drawn
    boolean dragging=false,insideStartNode=false,insideEndNode=false;   //used in mouse released
    private ArrayList<Point> circleInputCoordinates =new ArrayList<Point>(); //contain centre coordinates of drawn nodes
    private ArrayList<Point> circleOutputCoordinates =new ArrayList<Point>();//contain centre coordinates of output nodes
    ArrayList<Integer> startIndex = new ArrayList<Integer>();           //start point of edge
    ArrayList<Integer> endIndex = new ArrayList<Integer>();             //end point of edge
    public int [] treeArray;
    private int i=0,startNodeNo,endNodeNo,height;
    private String sequence;

    binaryTree() {
        buttonPanel = new JPanel();
        circleInputPanel = new JPanel();
        circleOutputPanel = new JPanel();
        sequenceLabel=new JLabel("Sequence: ");
        sequence ="Sequence: ";
        sequenceLabel.setFont(new Font(Font.SERIF,Font.ITALIC,(int) 25));
        sequenceLabel.setBounds(600,500,600,100);
        circleInputPanel.setBounds(0, 0, 600, 600);
        circleOutputPanel.setBounds(600, 0, 600, 600);
        buttonPanel.setBounds(0, 600, 1200, 100);
        buttonPanel.setLayout(new FlowLayout());
        circleInputPanel.setLayout(null);
        circleOutputPanel.setLayout(null);
        buttonPanel.setBackground(Color.red);
        circleInputPanel.setBackground(Color.black);
        circleOutputPanel.setBackground(Color.white);
        JButton PreOrder = new JButton("PreOrder");
        JButton InOrder = new JButton("InOrder");
        JButton PostOrder = new JButton("PostOrder");
        JButton LevelOrder = new JButton("LevelOrder");
        JButton MirrorImage = new JButton("Mirror Image");
        JButton Clear = new JButton("Clear");
        PreOrder.setActionCommand("PreOrder");
        InOrder.setActionCommand("InOrder");
        PostOrder.setActionCommand("PostOrder");
        LevelOrder.setActionCommand("LevelOrder");
        MirrorImage.setActionCommand("MirrorImage");
        Clear.setActionCommand("Clear");
        PreOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTree();
                //printTree();
                preOrder(0);
            }
        });
        InOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTree();
                //printTree();
                inOrder(0);
            }
        });
        PostOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTree();
                //printTree();
                postOrder(0);
            }
        });
        LevelOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTree();
                //printTree();
                levelOrder(0);
            }
        });
        MirrorImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mirrorImage();
            }
        });
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reInitialize();
            }
        });
        buttonPanel.add(PreOrder);
        buttonPanel.add(InOrder);
        buttonPanel.add(PostOrder);
        buttonPanel.add(LevelOrder);
        buttonPanel.add(MirrorImage);
        buttonPanel.add(Clear);
        circleOutputPanel.add(sequenceLabel);

        add(buttonPanel);
        add(circleInputPanel);
        add(circleOutputPanel);
        circleInputPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentPoint=e.getPoint();
                //currentPoint.x +=5; currentPoint.y+= 0;
                drawCircle(currentPoint.x, currentPoint.y);
                circleInputCoordinates.add(currentPoint);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dragging=true;
                currentPoint=e.getPoint();
                for (startNodeNo=0;startNodeNo<circleInputCoordinates.size();startNodeNo++) {
                    Point p=circleInputCoordinates.get(startNodeNo);
                    if (currentPoint.getX() > p.x-30 && currentPoint.getX() < p.x+30 && currentPoint.getY() > p.y-30 && currentPoint.getY() < p.y+30){
                        insideStartNode=true;
                        currentPoint=p;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                nextPoint = e.getPoint();
                for (endNodeNo=0;endNodeNo<circleInputCoordinates.size();endNodeNo++) {
                    Point p=circleInputCoordinates.get(endNodeNo);
                    if (nextPoint.getX() > p.x - 30 && nextPoint.getX() < p.x + 30 && nextPoint.getY() > p.y - 30 && nextPoint.getY() < p.y + 30){
                        insideEndNode = true;
                        nextPoint=p;
                    }
                    if (dragging && insideStartNode && insideEndNode) {
                        drawLine(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
                        //System.out.println(startNodeNo + " " + endNodeNo);
                        startIndex.add(startNodeNo);
                        endIndex.add(endNodeNo);
                        //System.out.println(startIndex + " " + endIndex);
                        insideStartNode = false;
                        insideEndNode = false;
                    }
                }
                dragging = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    void reInitialize(){
        sequenceLabel.setText("Sequence: ");
        sequence="Sequence: ";

        sequenceLabel.repaint();
        buttonPanel.repaint();
        circleInputPanel.repaint();
        circleOutputPanel.repaint();
        circleInputCoordinates =new ArrayList<Point>();
        circleOutputCoordinates =new ArrayList<Point>();
        startIndex = new ArrayList<Integer>();
        endIndex = new ArrayList<Integer>();
        this.i=0;
    }

    void preOrder(int index) {
        Stack stack = new Stack<Integer>();
        stack.push(index);
        while (!stack.isEmpty()){
            index= (int) stack.pop();
            highlightCircle(treeArray[index]);
            if (2 * index + 2 < treeArray.length && treeArray[index*2+2] != -1)
                stack.push(index * 2 + 2);
            if (2 * index + 1 < treeArray.length && treeArray[index*2+1] != -1)
                stack.push(index * 2 + 1);
        }
    }

    void inOrder(int index) {
        Stack stack = new Stack<Integer>();
        while(true){
            if (index  < treeArray.length && treeArray[index] != -1) {
                stack.push(index);
                index=index * 2 + 1;
            }
            else {
                if (!stack.isEmpty()) {
                    index = (int) stack.pop();
                    highlightCircle(treeArray[index]);
                    index = index * 2 + 2;
                }
                else break;
            }
        }
    }

    void postOrder(int index) {
        Stack stack = new Stack<Integer>();
        while (true) {
            //System.out.println(stack);
            while (index < treeArray.length && treeArray[index] != -1) {
                if (2 * index + 2 < treeArray.length && treeArray[index * 2 + 2] != -1)
                    stack.push(2 * index + 2);
                stack.push(index);
                index = 2 * index + 1;
            }
            index = (int) stack.pop();
            if (2 * index + 2 < treeArray.length && treeArray[index * 2 + 2] != -1 && !(stack.isEmpty()) && (int) stack.peek() == (index * 2 + 2)) {
                stack.pop();
                stack.push(index);
                index = index * 2 + 2;
            } else {
                highlightCircle(treeArray[index]);
                index = treeArray.length;
            }
            if (stack.isEmpty())
                break;
        }
    }

    void levelOrder(int index) {
    int i;
        for(i=0;i<treeArray.length;i++)
            if (i < treeArray.length && treeArray[i] != -1)
                highlightCircle(treeArray[i]);
    }

    void mirrorImage() {
        int len;
        ArrayList<Point> circleMirrorCoordinates = new ArrayList<Point>();
        len = circleInputCoordinates.size();
        for (i = 0; i < len; i++) {
            Point p = circleInputCoordinates.get(i);
            p.x = 1200 - p.x;
            circleMirrorCoordinates.add(p);
        }
        Graphics2D g= (Graphics2D) this.getGraphics();
        len=startIndex.size();
        for(i=0;i<len;i++) {
            Point p1 = circleInputCoordinates.get(startIndex.get(i));
            Point p2 = circleInputCoordinates.get(endIndex.get(i));
            g.setStroke(new BasicStroke(4.0f));
            g.setColor(Color.BLUE);
            g.drawLine(p1.x + 10, p1.y + 25, p2.x + 10, p2.y + 25);
        }
        len = circleMirrorCoordinates.size();
        for (i = 0; i < len; i++) {
            Point p = circleMirrorCoordinates.get(i);
            g.setColor(Color.yellow);
            g.fillOval(p.x - 30 + 5, p.y - 30 + 30, 60, 60);
            g.setColor(Color.BLUE);
            g.drawString(String.valueOf(i + 1), p.x, p.y + 30);
        }
    }

    void assignTree(){
        int i,len;
        this.treeArray = new int[(int) Math.pow(2,this.i)-1];
        len=startIndex.size();

        for(i=1;i<treeArray.length;i++)
            treeArray[i]=-1;

        for(i=0;i<len;i++) {
            Point p1 = circleInputCoordinates.get(startIndex.get(i));
            Point p2 = circleInputCoordinates.get(endIndex.get(i));
            drawLine(p1.x+600,p1.y,p2.x+600,p2.y);
            //printTree();
            if (p1.x > p2.x) {
                treeArray[2 * findPosition(startIndex.get(i))+1] = endIndex.get(i);
            }
            else if (p1.x < p2.x) {
                treeArray[2 * findPosition(startIndex.get(i)) + 2] = endIndex.get(i);
                //System.out.println(2 * findPosition(startIndex.get(i))+2);
            }
        }

        len=circleInputCoordinates.size();
        for(i=0;i<len;i++){
            Point p=circleInputCoordinates.get(i);
            p.x=p.x+600;
            circleOutputCoordinates.add(p);
        }
        drawOutputCircle();
    }

    int findPosition(int value){
        int i;
        for(i=0;i<treeArray.length;i++)
        {
            if(treeArray[i]==value)
                return i;
        }
        return -1;
    }

    public void drawOutputCircle() {
        int i,len;
        Graphics g=this.getGraphics();
        len=circleOutputCoordinates.size();
        for(i=0;i<len;i++){
            Point p=circleOutputCoordinates.get(i);
            g.setColor(Color.yellow);
            g.fillOval(p.x - 30+5, p.y - 30 +30, 60, 60);
            g.setColor(Color.BLUE);
            g.drawString(String.valueOf(i + 1), p.x, p.y + 30);
        }
    }

    void printTree(){
        int i;
        System.out.println("TreeArray");
        for(i=0;i<treeArray.length;i++)
        {
            System.out.print((treeArray[i]+1) + "  ");
        }
        System.out.println();
    }

    public void drawCircle(int x, int y) {
        Graphics g=this.getGraphics();
        g.setColor(Color.yellow);
        g.fillOval(x - 30+5, y - 30 +30, 60, 60);
        i++;
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(i), x, y + 30);
    }

    private void highlightCircle(int index) {
        sequenceLabel.setText(sequenceLabel.getText() + (index + 1) +", ");
        sequence=sequence+(index+1)+", ";


        Graphics g=this.getGraphics();
        Font f=g.getFont();
        g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
        g.drawString(sequence,610-2,600-10);
        g.setFont(f);
        //System.out.println(index);
        Point p=circleOutputCoordinates.get(index);

        g.setColor(Color.GREEN);
        g.fillOval(p.x - 30 + 5, p.y - 30 + 30, 60, 60);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(index + 1), p.x, p.y + 30);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.setColor(Color.YELLOW);
        g.fillOval(p.x - 30 + 5, p.y - 30 + 30, 60, 60);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(index + 1), p.x, p.y + 30);

    }

    public void drawLine(int x1, int y1,int x2,int y2) {
        Graphics2D g= (Graphics2D) this.getGraphics();
        g.setStroke(new BasicStroke(4.0f));
        g.setColor(Color.BLUE);
        g.drawLine(x1 + 10, y1 + 25, x2 + 10, y2 + 25);
        g.setColor(Color.yellow);
        g.fillOval(x1 - 30 + 5, y1 - 30 + 30, 60, 60);
        g.fillOval(x2 - 30+5, y2 - 30 +30, 60, 60);
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(startNodeNo + 1), x1, y1 + 30);
        g.drawString(String.valueOf(endNodeNo+1), x2, y2 + 30);
    }

    public static void main(String[] args) {
        binaryTree frame = new binaryTree();
        frame.setTitle("Binary Trees");
        frame.setVisible(true);
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}


