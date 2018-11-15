package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame implements ActionListener {

    private Field field;
    private Game game;

    public Window(String name) {
        game = new Game();
        createField();
        setName(name);
        init();
        pack();
    }

    private void init() {
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
                cell.addActionListener(this);
                add(cell);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cell cell = (Cell) e.getSource();
        openCell(cell);
    }

    private void setFlag(Cell cell) {
        cell.setFlaged(true);
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