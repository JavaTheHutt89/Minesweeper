package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame{

    private Field field;
    private Game game;
    private MouseListener mouseListener;

    public Window(String name) {
        game = new Game();
        createField();
        setName(name);
        init();
        pack();
    }

    private void init() {
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Cell cell = (Cell) e.getSource();
                if (e.getButton() == 3)
                    setFlag(cell);
                if (e.getButton() == 1)
                    openCell(cell);
            }
        };
        GridLayout gridLayout = new GridLayout(field.getRows(), field.getCols());
        setLayout(gridLayout);
        render();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    public void createField() {
        this.field = new Field(20, 20);
    }

    private void render() {
        Cell[][] cells = field.getCells();
        for (Cell[] cell1 : cells) {
            for (int j = 0; j < field.getCols(); j++) {
                Cell cell = cell1[j];
                cell.addMouseListener(mouseListener);
                add(cell);
            }
        }
    }

    private void setFlag(Cell cell) {
        if (!cell.isFlaged() && cell.getState() != Cell.State.OPEN) {
            cell.setFlaged(true);
            System.out.println("flag set!");
        }
    }

    private void openCell(Cell cell){
        if (cell.getState().equals(Cell.State.CLOSED)){
            if (cell.isBomb()) {
                cell.setIcon(new ImageIcon(ClassLoader.getSystemResource("BANG.png")));
                game.setGameState(Game.State.GAMEOVER);
            } else field.openEmptyCells(cell);
        }
    }
}