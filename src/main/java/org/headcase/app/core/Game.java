package org.headcase.app.core;

import java.util.ArrayList;
import java.util.Arrays;

public class Game implements GameStateListener{

    enum State {
        STARTED,
        WIN,
        GAMEOVER;
    }

    private int cols;
    private int rows;
    private ArrayList<GameStateListener> gameStateListeners;
    private State gameState;
    private Field gameField;

    public Game(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.gameState = State.STARTED;
        gameStateListeners = new ArrayList<>();
        createField();
        addListener(this);
    }

    public void addListener(GameStateListener gameStateListener){
        this.gameStateListeners.add(gameStateListener);
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
        for (GameStateListener gameStateListener : gameStateListeners) {
            gameStateListener.checkGameState();
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public void createField() {
        this.gameField = new Field(rows, cols);
    }

    public Field getGameField() {
        return gameField;
    }


    public void setFlag(Cell cell) {
        if (!cell.isFlaged() && cell.getState() != Cell.State.OPEN) {
            cell.setFlaged(true);
        } else if (cell.isFlaged()){
            cell.setFlaged(false);
        }
    }

    public void openCell(Cell cell){
        if (cell.getState().equals(Cell.State.CLOSED)){
            if (!cell.isFlaged()) gameField.openEmptyCells(cell);
        }
        gameField.repaint();
    }

    @Override
    public void checkGameState() {
        switch (gameState){
            case WIN:
                System.out.println("Win!");break;
            case STARTED:
                System.out.println("Started");break;
            case GAMEOVER:
                gameField.openAllCells();
                break;
            default: break;
        }
    }

    public boolean isWin() {
        long defusedBombs = 0;
        for (Cell[] nestedCells: gameField.getCells()
             ) {
           defusedBombs += Arrays.stream(nestedCells).filter(cell -> cell.isBomb() && cell.isFlaged()).count();
        }

        System.out.println("Bombs count: " + gameField.getBombsCount() +
                "| Bombs defused: " + defusedBombs);

        return gameField.getBombsCount() == defusedBombs;
    }

    public boolean isWasted(Cell cell){
        return cell.isBomb() && !cell.isFlaged();
    }
}
