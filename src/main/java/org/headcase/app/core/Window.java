package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;


public class Window extends JFrame implements ActionListener {
    private Field field;

    public Window(String name) {
        setField();
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
        setResizable(false);
        setVisible(true);
    }

    public void setField() {
        this.field = new Field(10, 10);
    }

    private void render() {
        Cell[][] cells = field.getCells();
        for (Cell[] cell1 : cells) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cell1[j];
                cell.addActionListener(this);
                add(cell);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cell cell = (Cell) e.getSource();
        if (cell.getState().equals(Cell.State.CLOSED))
            cell.setState(Cell.State.OPEN);
        System.out.println(cell.getState().toString());
    }

}