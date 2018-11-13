package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    enum State {
        OPEN,
        CLOSED,
        isBomb
    }

    public static final int CELL_SIZE = 32;
    private State state = State.CLOSED;
    private Point position;


    public Cell(String text, Point position) {
        super(text);
        this.position = position;
    }

    public Cell(Icon icon, Point position) {
        super(icon);
        this.position = position;
    }

    public Cell() {
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Point getPosition() {
        return position;
    }

}
