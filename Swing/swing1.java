package com.example.Algo_assignment;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.sun.javafx.geom.Shape;

public class swing1
{

    private static void initAndShowGUI() {
        // This method is invoked on the EDT thread
        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(600, 500);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }
       });
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene(); 
        fxPanel.setScene(scene);
    }    

    private static Scene createScene() {
        Group  root  =  new  Group();
        //Button send = new Button("Send");
        Scene  scene  =  new  Scene(root,Color.AQUA);
       TextArea t= new TextArea();
        
        
       Button btnSend = new Button("Send");
       Button btnRecieve = new Button("Recieve");
       Button btnExit = new Button("Exit");
       btnRecieve.setFont(Font.font("Calibri", FontPosture.ITALIC, 25));
       btnSend.setFont(Font.font("Calibri", FontPosture.ITALIC, 25));
       
       btnSend.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
       btnRecieve.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
       btnExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
       TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
       tileButtons.setPadding(new Insets(40, 10, 20, 175));
       tileButtons.setHgap(10.0);
       tileButtons.setVgap(20.0);
       tileButtons.getChildren().addAll(btnSend, btnRecieve);        
       
       GridPane gridpane = new GridPane();
       gridpane.setPadding(new Insets(10));
       gridpane.setLayoutY(80);
       gridpane.setHgap(5);
       gridpane.setVgap(10);
       
       
       TextArea messages = new TextArea();
       TextArea type = new TextArea();
       messages.setEditable(false);
       messages.setText("Holo");
       messages.setStyle("-fx-background-color: yellow" );
       messages.setFont(Font.font("Calibri", FontWeight.MEDIUM, 15));
       messages.setPrefRowCount(12);
       messages.setPrefColumnCount(200);
       messages.setWrapText(true);
       messages.setPrefWidth(563);
       GridPane.setHalignment(messages, HPos.CENTER);
       gridpane.add(messages , 0, 1);
       
       //JTextArea type = new JTextArea(8, 0);
       type.setFont(Font.font("Calibri", FontWeight.MEDIUM, 15));
       type.setPrefRowCount(2);
       type.setEditable(true);
       type.setPrefColumnCount(200);
       type.setWrapText(true);
       type.setPrefWidth(500);
       type.setLayoutY(370);
       type.setLayoutX(16);
       
       Button b=new Button("Send");
       b.setLayoutX(530);
       b.setLayoutY(387);
      // b.set
        /*Text  text  =  new  Text();
        new Font(30);
        text.setFont(Font.font("Comic Sans", FontPosture.ITALIC, 30));
        text.setText("Welcome");
        text.setX(180);
        text.setY(250);*/
        
        //text.setText("Welcome JavaFX!");

        //root.getChildren().add(text);
        root.getChildren().addAll(tileButtons,gridpane,type,b);
        return (scene);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }
}
