package com.example.Algo_assignment;

/**
 * Created by Prateek on 09-Nov-15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class undirectedGraph extends JFrame {
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
    public int [][] adjacencyMatrix;
    private int i=0,startNodeNo,endNodeNo;
    String sequence;

    int[][] minimalSpanningTree;                    //Final Tree adjacency Matrix
    int[] Visited;                                  //Visited vertices
    
    undirectedGraph() {
        buttonPanel = new JPanel();
        circleInputPanel = new JPanel();
        circleOutputPanel = new JPanel();
        sequenceLabel=new JLabel("Sequence: ");
        sequenceLabel.setFont(new Font(Font.SERIF,Font.ITALIC,(int) 25));
        sequence ="Sequence: ";

        sequenceLabel.setBounds(600, 500, 600, 100);
        circleInputPanel.setBounds(0, 0, 600, 600);
        circleOutputPanel.setBounds(600, 0, 600, 600);
        buttonPanel.setBounds(0, 600, 1200, 100);
        buttonPanel.setLayout(new FlowLayout());
        circleInputPanel.setLayout(null);
        circleOutputPanel.setLayout(null);
        buttonPanel.setBackground(Color.red);
        circleInputPanel.setBackground(Color.black);
        circleOutputPanel.setBackground(Color.white);
        JButton BFS = new JButton("Breadth First Traversal");
        JButton DFS = new JButton("Depth First Traversal");
        JButton DTraversal = new JButton("D Traversal");
        JButton Clear = new JButton("Clear");
        BFS.setActionCommand("BFS");
        DFS.setActionCommand("DFS");
        DTraversal.setActionCommand("DTraversal");
        Clear.setActionCommand("Clear");
        BFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(JOptionPane.showInputDialog("Enter starting Node"));
                a = a - 1;
                assignAdjacency();
                //printAdjacency();
                BFS(a);
            }
        });
        DFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(JOptionPane.showInputDialog("Enter starting Node"));
                a = a - 1;
                assignAdjacency();
                //System.out.println("DFS");
                //printAdjacency();
                sequenceLabel.setText(sequenceLabel.getText() + (a + 1) + ", ");
                sequence+=(a+1)+", ";
                DFS(a);
            }
        });
        DTraversal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(JOptionPane.showInputDialog("Enter starting Node"));
                a=a-1;
                assignAdjacency();
                //System.out.println("DTraversal");
                //printAdjacency();
                DTraversal(a);
            }
        });
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reInitialize();
            }
        });
        buttonPanel.add(BFS);
        buttonPanel.add(DFS);
        buttonPanel.add(DTraversal);
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

    void BFS(int a) {
        int vertex, i;
        Queue<Integer> Vqueue = new LinkedList<Integer>();
        Vqueue.add(a);
        Visited[a] = 1;
        sequenceLabel.setText(sequenceLabel.getText() + (a+1) +", ");
        sequence+=(a+1)+", ";
        Graphics g=this.getGraphics();
        Font f=g.getFont();
        g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
        g.drawString(sequence, 610 - 2, 600 - 10);
        g.setFont(f);
        while (!Vqueue.isEmpty()) {
            vertex = Vqueue.remove();
            for (i = 0; i < this.i; i++) {
                if (this.adjacencyMatrix[vertex][i] == 1 && this.Visited[i] == 0) {
                    this.minimalSpanningTree[vertex][i] = 1;
                    startNodeNo=vertex;endNodeNo=i;
                    Point p1=circleOutputCoordinates.get(startNodeNo),p2=circleOutputCoordinates.get(endNodeNo);
                    sequenceLabel.setText(sequenceLabel.getText() + (endNodeNo + 1) +", ");
                    sequence+=(endNodeNo + 1)+", ";
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    f=g.getFont();
                    g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
                    g.drawString(sequence, 610 - 2, 600 - 10);
                    g.setFont(f);
                    drawLine(p1.x,p1.y,p2.x,p2.y);
                    Vqueue.add(i);
                    this.Visited[i] = 1;
                }
            }
        }
    }

    void DFS(int v) {

        int vertex, i;
        this.Visited[v] = 1;
        vertex = v;
        Graphics g=this.getGraphics();
        Font f=g.getFont();
        g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
        g.drawString(sequence, 610 - 2, 600 - 10);
        g.setFont(f);
        for (i = 0; i < this.i; i++) {
            if (this.adjacencyMatrix[vertex][i] == 1 && this.Visited[i] == 0) {
                this.minimalSpanningTree[vertex][i] = 1;
                startNodeNo=vertex;endNodeNo=i;
                Point p1=circleOutputCoordinates.get(startNodeNo),p2=circleOutputCoordinates.get(endNodeNo);
                sequenceLabel.setText(sequenceLabel.getText() + (endNodeNo + 1) +", ");
                sequence+=(endNodeNo + 1)+", ";
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                f=g.getFont();
                g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
                g.drawString(sequence, 610 - 2, 600 - 10);
                g.setFont(f);
                drawLine(p1.x,p1.y,p2.x,p2.y);
                DFS(i);
            }
        }
    }

    void DTraversal(int a) {
        int vertex, i;
        Stack<Integer> Vqueue = new Stack<Integer>();
        Vqueue.add(a);
        this.Visited[a] = 1;
        sequenceLabel.setText(sequenceLabel.getText() + (a+1) +", ");
        sequence+=(a+1)+", ";
        Graphics g=this.getGraphics();
        Font f=g.getFont();
        g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
        g.drawString(sequence, 610 - 2, 600 - 10);
        g.setFont(f);
        while (!Vqueue.isEmpty()) {
            vertex = Vqueue.pop();
            for (i = 0; i < this.i; i++) {
                if (this.adjacencyMatrix[vertex][i] == 1 && this.Visited[i] == 0) {
                    this.minimalSpanningTree[vertex][i] = 1;
                    startNodeNo=vertex;endNodeNo=i;
                    Point p1=circleOutputCoordinates.get(startNodeNo),p2=circleOutputCoordinates.get(endNodeNo);
                    sequenceLabel.setText(sequenceLabel.getText() + (endNodeNo + 1) +", ");
                    sequence+=(endNodeNo + 1)+", ";
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    f=g.getFont();
                    g.setFont(new Font(Font.SERIF, Font.ITALIC, (int) 25));
                    g.drawString(sequence, 610 - 2, 600 - 10);
                    g.setFont(f);
                    drawLine(p1.x,p1.y,p2.x,p2.y);
                    Vqueue.push(i);
                    this.Visited[i] = 1;
                }
            }
        }
    }

    void assignAdjacency(){
        int i,j,len;
        len=this.i;
        this.minimalSpanningTree = new int[this.i][this.i];
        this.Visited = new int[this.i];

        adjacencyMatrix=new int[len][len];
        for(i=0;i<len;i++)
            for(j=0;j<len;j++)
                adjacencyMatrix[i][j]=0;
        len=startIndex.size();
        for(i=0;i<len;i++){
            adjacencyMatrix[startIndex.get(i)][endIndex.get(i)]=1;
            adjacencyMatrix[endIndex.get(i)][startIndex.get(i)]=1;
        }

        len=circleInputCoordinates.size();
        for(i=0;i<len;i++){
            Point p=circleInputCoordinates.get(i);
            p.x=p.x+600;
            circleOutputCoordinates.add(p);
        }
        drawOutputCircle();
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
            g.drawString(String.valueOf(i+1), p.x, p.y + 30);
        }
    }

    void printAdjacency() {
        int i,j;
        System.out.println("Adjacency Matrix");
        for (i = 0; i < adjacencyMatrix.length; i++) {
            for (j = 0; j < adjacencyMatrix.length; j++)
                System.out.print(adjacencyMatrix[i][j] + "  ");
            System.out.println();
        }
    }

    public void drawCircle(int x, int y) {
        Graphics g=this.getGraphics();
        g.setColor(Color.yellow);
        g.fillOval(x - 30+5, y - 30 +30, 60, 60);
        i++;
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(i), x , y+30);
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
        undirectedGraph frame = new undirectedGraph();
        frame.setVisible(true);
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

