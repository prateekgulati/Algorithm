package com.example.Algo_assignment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Override;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class HeadTail extends JFrame{
    private static mylabel label;
    public HeadTail(){
        setLayout(new GridLayout(3, 3));
        
        for(int i=0;i<9;i++){
            mylabel label=new mylabel("H");
            label.setFont(new Font("Times Roman",Font.BOLD,25));
            label.setBorder(new LineBorder(Color.BLACK));
            getContentPane().add(label);
            label.setAlignmentX(CENTER_ALIGNMENT);
        }
    }

    public static void main(String args[]){
        HeadTail n=new HeadTail();
        n.setVisible(true);
        n.setSize(200,200);
        n.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    class mylabel extends JLabel{
        mylabel(String def){
            super(def);
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(getText().equals("H"))
                        setText("T");
                    else
                        setText("H");
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
                
            });
        }

        

        
        
    }
}
