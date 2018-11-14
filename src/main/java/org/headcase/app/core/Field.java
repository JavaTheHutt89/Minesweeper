package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Field extends JPanel implements CellStateListener{

    private final int cols;
    private final int rows;
    private Cell[][] cells;
    private Random random;

    public Field(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;
        this.random = new Random();
        cells = new Cell[rows][cols];
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
        int cellsCount = cols * rows;
        int bombsCount = cellsCount * 15 / 100;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = new Cell(new Point(j,i));
                int rand = random.nextInt(cellsCount);
                if (rand <= bombsCount)
                    cell.setBomb(true);
                cell.setBackground(Color.gray);
                cell.setPreferredSize(new Dimension(Cell.CELL_SIZE, Cell.CELL_SIZE));
                cells[i][j] = cell;
            }
        }
        Arrays.stream(cells).forEach(cells1 -> Arrays.stream(cells1).forEach(x -> System.out.println(x.isBomb())));
    }

    public void nearCells(Cell cell){

        int x = cell.getPosition().x;
        int y = cell.getPosition().y;
        if(!cell.isBomb()){
            int bombsCount = 0;
            try {
//                if (cells[cell.getPosition().y - 1][cell.getPosition().x - 1].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y - 1][cell.getPosition().x].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y - 1][cell.getPosition().x + 1].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y ][cell.getPosition().x + 1].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y + 1][cell.getPosition().x + 1].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y + 1][cell.getPosition().x].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y + 1][cell.getPosition().x - 1].isBomb())
//                    bombsCount++;
//                if (cells[cell.getPosition().y][cell.getPosition().x - 1].isBomb())
//                    bombsCount++;

//                for (int i = y - 1; i < y + 1; i++) {
//                    for (int j = x - 1; j < x + 1; j++) {
//                        if (cells[i][j].isBomb()){
//                            bombsCount++;
//                            cell.setIcon(new ImageIcon(ClassLoader.getSystemResource("Numbers-"+bombsCount+"-Black-icon.png")));
//                            nearCells(cell);
//                        }
//                    }
//                }



//                if (bombsCount > 0){
//                    cell.setIcon(new ImageIcon(ClassLoader.getSystemResource("Numbers-"+bombsCount+"-Black-icon.png")));
//                }
            } catch (ArrayIndexOutOfBoundsException e){

            }
        }
    }


    @Override
    public void checkCellState() {

    }

}
