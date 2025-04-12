/*
*  Implementations of the dashboard view. What the user sees and able to interact.
*  Please be aware of userfriendly behaviours in the application.
* */

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboardView implements ActionListener{

    private static JButton nextBtn;
    private static JButton backBtn;
    private static JButton resetBtn;

    private static JPanel dashboardPnl;
    private static JPanel navigationPnl;
    private JPanel cardsPnl;

    private static GridBagConstraints gbc =  new GridBagConstraints();

    public static String[] btnText = {"weiter", "zurück", "reset"};


    private static JButton callBtn(JPanel panel, String text, int gridx, int gridy){
        JButton btn = new JButton(text);

        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(btn,gbc);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource().equals(btnText[0])){
//            System.out.println("Weiter");
//        }
//        if (e.getActionCommand().equalsIgnoreCase(btnText[1])){
//            System.out.println("Zurück");
//        }
//        if(e.getActionCommand().equalsIgnoreCase(btnText[2])){
//            System.out.println("Reset");
//        }

    }



    // Dashboard Panel
    private static JPanel callDashboard(){
        dashboardPnl = new JPanel();
        dashboardPnl.setPreferredSize(new Dimension(framePrefs.setFrameSizeX(),framePrefs.setFrameSizeY()-50));
        dashboardPnl.setBackground(Color.lightGray);


        return dashboardPnl;
    }

    private static JPanel callNavigationbar(){
        navigationPnl = new JPanel(new GridBagLayout());
        navigationPnl.setBackground(Color.gray);
        navigationPnl.setPreferredSize(new Dimension(framePrefs.setFrameSizeX(),50));

        nextBtn = callBtn(navigationPnl, btnText[0],2,0);
        backBtn = callBtn(navigationPnl, btnText[1],0,0);
        resetBtn = callBtn(navigationPnl,btnText[2],1,0);

        return navigationPnl;
    }

    // Instantiate Navigation Bar - to add on a JFrame/Panel
    public static JPanel setNavigationbar(){
        JPanel navigation = callNavigationbar();
        return navigation;
    }
    // Instantiate Dashboard - to add on a JFrame/Panel
    public static JPanel setDashboard(){
        JPanel dashboard = callDashboard();
        return dashboard;
    }

}
