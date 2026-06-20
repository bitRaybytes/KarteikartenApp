/*
*  Handling the frame.
* */


package view;

import model.MenuBar;
import model.Flashcard;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.DashboardView.btnText;
import static view.FramePrefs.setFrameSizeX;
import static view.FramePrefs.setFrameSizeY;

public class GuiView extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DashboardView dashboard;
    private ManagePanel managePanel;

    MenuBar menuBar = new MenuBar(this);

    private void setFrameView(){

        setTitle("Karteikarten-App");
        setSize(setFrameSizeX(),setFrameSizeY());
        setJMenuBar(menuBar);

        dashboard = new DashboardView();
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


    public GuiView(){
        setFrameView();
    }

    public static void start(){
        SwingUtilities.invokeLater(() -> new GuiView());
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
        void onCardSaved(Flashcard card);
    }

    public static void main(String[] args) {
        start();
    }

}
