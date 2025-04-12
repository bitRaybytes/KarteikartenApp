/*
*  Handling the frame.
* */


package view;

import model.menuBar;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.dashboardView.btnText;
import static view.dashboardView.*;
import static view.framePrefs.setFrameSizeX;
import static view.framePrefs.setFrameSizeY;

public class guiView extends JFrame implements ActionListener {


    menuBar menuBar = new menuBar(this);

    private void setFrameView(){

        setTitle("Karteikarten-App");
        setSize(setFrameSizeX(),setFrameSizeY());
        setJMenuBar(menuBar);

        add(setDashboard(), BorderLayout.CENTER);
        add(setNavigationbar(), BorderLayout.SOUTH);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public guiView(){
        setFrameView();
    }

    public static void start(){
        SwingUtilities.invokeLater(() -> new guiView());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnText[0])){
            System.out.println("Weiter");
        }
        if (e.getActionCommand().equalsIgnoreCase(btnText[1])){
            System.out.println("Zurück");
        }
        if(e.getActionCommand().equalsIgnoreCase(btnText[2])){
            System.out.println("Reset");
        }

    }

    public static void main(String[] args) {
        start();
    }

}
