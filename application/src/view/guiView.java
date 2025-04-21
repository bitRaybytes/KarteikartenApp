/*
*  Handling the frame.
* */


package view;

import model.menuBar;
import model.flashcard;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.dashboardView.btnText;
import static view.dashboardView.*;
import static view.framePrefs.setFrameSizeX;
import static view.framePrefs.setFrameSizeY;

public class guiView extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private dashboardView dashboard;
    private ManagePanel managePanel;

    menuBar menuBar = new menuBar(this);

    private void setFrameView(){

        setTitle("Karteikarten-App");
        setSize(setFrameSizeX(),setFrameSizeY());
        setJMenuBar(menuBar);

        dashboard = new dashboardView();
        managePanel = new ManagePanel();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(dashboard, "dashboard");
        mainPanel.add(managePanel, "manage");

        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showDashboard() {
        cardLayout.show(mainPanel, "dashboard");
    }

    public void showManagePanel() {
        cardLayout.show(mainPanel, "manage");
    }


    public guiView(){
        setFrameView();
    }

    public static void start(){
        SwingUtilities.invokeLater(() -> new guiView());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equalsIgnoreCase(btnText[0])) {
            showManagePanel();
        } else if (command.equalsIgnoreCase(btnText[1])) {
            showDashboard();
        } else if (command.equalsIgnoreCase(btnText[2])) {
            // resetLogik
        }

    }

    public interface CardSaveListener {
        void onCardSaved(flashcard card);
    }

    public static void main(String[] args) {
        start();
    }

}
