package model;

import javax.swing.*;
import java.util.Arrays;
import java.util.EventListener;

public class menuBar extends JMenuBar{
    private static JMenu menu = new JMenu();
    private static JMenuItem menuItem = new JMenuItem();

    private static final String[] menuLabels = {"Datei", "Bearbeiten","Hilfe"};
    private static final String[] menuFile = {"Neu laden","Schließen"};
    private static final String[] menuEditSubLbl = {"Neue Karteikarte hinzufügen", "Karteikarten bearbeiten", "Karteikarten ansehen"};
    private static final String[] menuHelpSubLbl = {"Support"};

    private JMenu getMenuBar(){
        for(int i = 0; i<menuLabels.length;i++){
            menu = new JMenu(menuLabels[i]);
            add(menu);
        }

        return menu;
    }

    private JMenuItem getMenuItem(String[] text){
        for (int mi=0;mi<text.length;mi++){
            menuItem.setText(text[mi]);
            menuItem.setActionCommand(text[mi]);
            menuItem.addActionListener(null);
            getMenuBar().add(menuItem);
        }

        return menuItem;
    }

    public menuBar(EventListener listener){
        add(getMenuBar());

        if (menu.isMenuComponent(getMenu(0))){
            for (int i = 0; i<menuFile.length;i++){
                getMenuItem(menuFile);
                getMenuBar().add(getMenuItem(menuFile));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(menuFile.length);
    }
}
