package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Field extends JPanel implements CellStateListener {

    private final int cols;
    private final int rows;
    private int bombsCount;
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
        Font font =new Font("Times New Roman", Font.BOLD,20);
        int cellsCount = cols * rows;
        bombsCount = cellsCount * 15 / 100;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = new Cell(new Point(j, i));
                int rand = random.nextInt(cellsCount);
                if (rand <= bombsCount)
                    cell.setBomb(true);
                cell.setBackground(Color.gray);
                cell.setFont(font);
                cell.setMargin(new Insets(5,5,5,5));
                cell.setPreferredSize(new Dimension(Cell.CELL_SIZE, Cell.CELL_SIZE));
                cells[i][j] = cell;
            }
        }
        setupNumbers();

        Arrays.stream(cells).forEach(cells1 -> Arrays.stream(cells1).forEach(x -> System.out.println(x.isBomb())));
    }

    private void setupNumbers() {
        for (Cell[] nestedCells: cells
             ) {
            Arrays.stream(nestedCells)
                    .filter(Cell::isBomb)
                    .forEach(cell -> incrementNumberAllNearCells(cell.getPosition()));
        }
    }


    public void openEmptyCells(Cell cell) {

        if (Objects.requireNonNull(cell).getState().equals(Cell.State.OPEN))
            return;
        if (cell.isFlaged())
            return;
        if (!cell.isBomb() && cell.getBombsAround() == 0) {
            cell.setState(Cell.State.OPEN);
            scanCells(cell.getPosition());
        } else if (cell.getBombsAround() > 0){
            cell.setState(Cell.State.OPEN);
        }
    }

    private void incrementNumberAllNearCells(Point position) {
        List<Cell> cellsAround = getCellsAround(position);
        for (Cell cell: cellsAround
             ) {
            cell.setBombsAround(1);
        }
    }

    private List<Cell> getCellsAround(Point position) {
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
                cellsAround.add(cell);
            }
        }

        return cellsAround;
    }

    private void scanCells(Point position){
        List<Cell> cellsAround = getCellsAround(position);
        for (Cell cell: cellsAround
        ) {
            if (!cell.isBomb() && cell.getState() == Cell.State.CLOSED)
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

    public int getBombsCount() {
        return bombsCount;
    }

    @Override
    public void checkCellState() {

    }

}
