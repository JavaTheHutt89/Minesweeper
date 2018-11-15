package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame{

    private Game game;
    private MouseListener mouseListener;

    public Window(String name, int cols, int rows) {
        game = new Game(cols, rows);
        setName(name);
        init();
        pack();
    }

    private void init() {
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Cell cell = (Cell) e.getSource();
                if (e.getButton() == 3) {
                    game.setFlag(cell);
                    if (game.isWin())
                        System.out.println("You win!");
                }
                if (e.getButton() == 1)
                    game.openCell(cell);
            }
        };
        GridLayout gridLayout = new GridLayout(game.getRows(), game.getCols());
        setLayout(gridLayout);
        render();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void render() {
        Field field = game.getGameField();
        Cell[][] cells = field.getCells();
        for (Cell[] cell1 : cells) {
            for (int j = 0; j < field.getCols(); j++) {
                Cell cell = cell1[j];
                cell.addMouseListener(mouseListener);
                add(cell);
            }
        }
    }
}