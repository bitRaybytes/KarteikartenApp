package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboardView extends JPanel implements ActionListener {

    private JButton nextBtn;
    private JButton backBtn;
    private JButton resetBtn;

    private JPanel dashboardPnl;
    private JPanel navigationPnl;

    public static final String[] btnText = {"weiter", "zurück", "reset"};

    public dashboardView() {
        setLayout(new BorderLayout());

        dashboardPnl = buildDashboardPanel();
        navigationPnl = buildNavigationBar();

        add(dashboardPnl, BorderLayout.CENTER);
        add(navigationPnl, BorderLayout.SOUTH);
    }

    private JPanel buildDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(framePrefs.setFrameSizeX(), framePrefs.setFrameSizeY() - 50));
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    private JPanel buildNavigationBar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(framePrefs.setFrameSizeX(), 50));

        GridBagConstraints gbc = new GridBagConstraints();

        nextBtn = createButton(btnText[0], 2, 0, gbc, panel);
        backBtn = createButton(btnText[1], 0, 0, gbc, panel);
        resetBtn = createButton(btnText[2], 1, 0, gbc, panel);

        return panel;
    }

    private JButton createButton(String text, int gridx, int gridy, GridBagConstraints gbc, JPanel panel) {
        JButton btn = new JButton(text);
        btn.addActionListener(this);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(btn, gbc);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equalsIgnoreCase(btnText[0])) {
            System.out.println("Weiter");
        } else if (cmd.equalsIgnoreCase(btnText[1])) {
            System.out.println("Zurück");
        } else if (cmd.equalsIgnoreCase(btnText[2])) {
            System.out.println("Reset");
        }
    }
}
