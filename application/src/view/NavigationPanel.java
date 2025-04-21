package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NavigationPanel extends JPanel {
    private boolean isExpanded = true;
    private final MainFrame mainFrame;

    public NavigationPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, getHeight()));
        setBackground(Color.LIGHT_GRAY);
        new learnPanel();

        JButton toggleButton = new JButton("☰");
        toggleButton.addActionListener(e -> toggle());

        JButton learnButton = new JButton("Lernen");
        learnButton.addActionListener((ActionEvent e) -> mainFrame.showPanel("flashcard"));

        JButton manageButton = new JButton("Verwalten");
        manageButton.addActionListener((ActionEvent e) -> mainFrame.showPanel("manage"));


        add(toggleButton);
        add(Box.createVerticalStrut(10));
        add(learnButton);
        add(manageButton);
    }

    private void toggle() {
        isExpanded = !isExpanded;
        setPreferredSize(new Dimension(isExpanded ? 200 : 50, getHeight()));
        revalidate();
        repaint();
    }
}
