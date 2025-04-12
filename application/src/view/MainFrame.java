package view;

import model.menuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainContent;
    private menuBar menuBar = new menuBar(this);

    public MainFrame() {
        setTitle("Karteikarten App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setJMenuBar(menuBar);
        // Navigation Panel (links)
        NavigationPanel navigationPanel = new NavigationPanel(this);
        add(navigationPanel, BorderLayout.WEST);

        // Content Panel (zentral mit CardLayout)
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        mainContent.add(new FlashcardPanel(), "flashcard");
        mainContent.add(new JLabel("Verwaltungspanel (bald verfügbar)"), "manage");

        add(mainContent, BorderLayout.CENTER);

        setVisible(true);
    }

    public void showPanel(String name) {
        cardLayout.show(mainContent, name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
