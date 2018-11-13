package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel{

    private int cols;
    private int rows;
    private Cell[][] cells;

    public Field(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        cells = new Cell[cols][rows];
        createCells();
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void createCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = new Cell();
                cell.setBackground(Color.darkGray);
                cell.setPreferredSize(new Dimension(Cell.CELL_SIZE, Cell.CELL_SIZE));
                cells[i][j] = cell;
            }

        }

    }
}
