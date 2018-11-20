package org.headcase.app.core;

import javax.swing.*;

public class Window extends JFrame{

    private final String NAME = "Minesweeper";

    public Window() {
        init();
    }

    private void init() {
        setName(NAME);
        setContentPane(new GamePanel(10,10));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pack();
        repaint();
    }
}