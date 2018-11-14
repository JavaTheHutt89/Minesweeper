package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cell extends JButton implements CellStateListener {

    enum State {
        OPEN,
        CLOSED
    }

    public static final int CELL_SIZE = 32;
    private final Point position;
    private State state;
    private ArrayList<CellStateListener> cellStateListeners;
    private boolean isBomb = false;
    private boolean isFlaged = false;



    public Cell(String text, Point position) {
        super(text);
        this.position = position;
        this.state = State.CLOSED;
        cellStateListeners = new ArrayList<>();
        addListener(this);
    }

    public Cell(Icon icon, Point position) {
        super(icon);
        this.position = position;
        this.state = State.CLOSED;
        cellStateListeners = new ArrayList<>();
        addListener(this);
    }

    public Cell(Point position) {
        this.position = position;
        this.state = State.CLOSED;
        cellStateListeners = new ArrayList<>();
        addListener(this);
    }

    private void addListener(CellStateListener cellStateListener){
        cellStateListeners.add(cellStateListener);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        for (CellStateListener cellStateListener:cellStateListeners
             ) {
            cellStateListener.checkCellState();
        }
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isFlaged() {
        return isFlaged;
    }

    public void setFlaged(boolean flaged) {
        isFlaged = flaged;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public void checkCellState() {
        switch (state){
            case OPEN:
                this.setBackground(Color.lightGray); break;
            case CLOSED:
                System.out.println("CLOSED"); break;
            default: break;
        }
    }

}
