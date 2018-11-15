package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Field extends JPanel implements CellStateListener {

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
                Cell cell = new Cell(new Point(j, i));
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

    public void openEmptyCells(Cell cell) {
        int x = cell.getPosition().x;
        int y = cell.getPosition().y;

        if (Objects.requireNonNull(cell).getState() == Cell.State.OPEN)
            return;
        if (!cell.isBomb()) {
            cell.setState(Cell.State.OPEN);
            scanCellsAround(cell.getPosition());
        }
    }

    private void scanCellsAround(Point position) {
        int x = position.x;
        int y = position.y;
        List<Cell> cellsAround = new ArrayList<>();
        ArrayList<Point> conditions = new ArrayList<>();
        conditions.add(new Point(x - 1, y - 1));
        conditions.add(new Point(x - 1, y));
        conditions.add(new Point(x - 1, y + 1));
        conditions.add(new Point(x, y + 1));
        conditions.add(new Point(x + 1, y + 1));
        conditions.add(new Point(x + 1, y));
        conditions.add(new Point(x + 1, y - 1));
        conditions.add(new Point(x, y - 1));

        for (Point conditionPosition : conditions) {
            if (inRange(conditionPosition.x,conditionPosition.y)){
                Cell cell = cell(conditionPosition);
                if (!cell.isBomb() && cell.getState() == Cell.State.CLOSED)
                    cellsAround.add(cell);
            }
        }

        for (Cell cell: cellsAround
             ) {
            openEmptyCells(cell);
        }

    }


    public boolean inRange(int x, int y){
        return x >= 0 && x < cols && y >=0 && y < rows;
    }

    public Cell cell(Point position){
        return getCell(position.x, position.y);
    }

    private Cell getCell(int x, int y){
        return cells[y][x];
    }


    @Override
    public void checkCellState() {

    }

}
