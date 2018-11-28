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
    private int bombsAround;


    public Cell(Point position) {
        this.position = position;
        this.state = State.CLOSED;
        cellStateListeners = new ArrayList<>();
        addListener(this);
    }

    private void addListener(CellStateListener cellStateListener) {
        cellStateListeners.add(cellStateListener);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        for (CellStateListener cellStateListener : cellStateListeners
        ) {
            cellStateListener.checkCellState(state);
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
        if (isFlaged) {
            this.setIcon(new ImageIcon(ClassLoader.getSystemResource("flag.png")));
        } else this.setIcon(UIManager.getIcon(DISABLED_ICON_CHANGED_PROPERTY));
    }

    public Point getPosition() {
        return position;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround += bombsAround;
    }

    @Override
    public void checkCellState(State state) {
        switch (state) {
            case OPEN:
                if (!this.isBomb && !isFlaged) {
                    if (this.bombsAround > 0) {
                        this.setText("" + bombsAround);
                        this.setBackground(Color.lightGray);
                    } else this.setBackground(Color.lightGray);
                } else if (this.isBomb && !this.isFlaged) {
                    this.setIcon(new ImageIcon(ClassLoader.getSystemResource("BANG.png")));
                }
                break;
            case CLOSED:
                break;
            default:
                break;
        }
    }

}
