package org.headcase.app;

import org.headcase.app.core.GamePanel;
import org.headcase.app.core.Window;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static Window window;
    private static MenuBar menuBar;
    private static Menu menu;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createGUI);
    }

    private static void createGUI() {
        window = new Window();
        window.setMenuBar(setupMenuBar());
    }

    private static MenuBar setupMenuBar() {
        menuBar = new MenuBar();
        menu = new Menu("Game settings");
        MenuItem beginnerItem = new MenuItem("Beginner");
        menu.add(beginnerItem);
        MenuItem advancedItem = new MenuItem("Advanced");
        menu.add(advancedItem);
        MenuItem expertItem = new MenuItem("Expert");
        menu.add(expertItem);

        menu.addActionListener(e -> {
            menuActions(e.getActionCommand());
        });
        menuBar.add(menu);

        return menuBar;
    }

    private static void menuActions(String actionCommand) {
        switch (actionCommand) {
            case "Beginner":
                setupDifficulty(10,10);
                break;
            case "Advanced":
                setupDifficulty(15,15);
                break;
            case "Expert":
                setupDifficulty(20,20);
                break;
            default: break;
        }
    }

    private static void  setupDifficulty(int cols, int rows) {
        window.startTheNewGame(cols, rows);
    }
}
