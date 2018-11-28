package org.headcase.app.core;

import java.util.ArrayList;
import java.util.Arrays;

public class Game implements GameStateListener{


    enum Difficulty{
        Beginner,
        Advanced,
        Expert
    }

    enum State {
        STARTED,
        WIN,
        GAMEOVER;
    }

    private int cols;
    private int rows;
    private ArrayList<GameStateListener> gameStateListeners;
    private State gameState;
    private Difficulty difficulty;
    private int flagsCount;
    private Field gameField;
    private TimerAndFlagsCountLabel timerAndFlagsCountLabel;

    public Game(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.gameState = State.STARTED;
        gameStateListeners = new ArrayList<>();
        createField();
        flagsCount = gameField.getBombsCount();
        addListener(this);
    }

    public void addListener(GameStateListener gameStateListener){
        this.gameStateListeners.add(gameStateListener);
    }

    public void setTimerAndFlagsCountLabel(TimerAndFlagsCountLabel timerAndFlagsCountLabel) {
        this.timerAndFlagsCountLabel = timerAndFlagsCountLabel;
        timerAndFlagsCountLabel.setFlagsCount(flagsCount);
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
        for (GameStateListener gameStateListener : gameStateListeners) {
            gameStateListener.checkGameState(this.gameState);
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getFlagsCount() {
        return flagsCount;
    }

    public void createField() {
        this.gameField = new Field(rows, cols);
    }

    public Field getGameField() {
        return gameField;
    }


    public void setFlag(Cell cell) {
        if (!cell.isFlaged() && cell.getState() != Cell.State.OPEN) {
            if (flagsCount > 0) {
                cell.setFlaged(true);
                flagsCount--;
                timerAndFlagsCountLabel.setFlagsCount(flagsCount);
            }
        } else if (cell.isFlaged()) {
            cell.setFlaged(false);
            flagsCount++;
            timerAndFlagsCountLabel.setFlagsCount(flagsCount);
        }
    }

    public void openCell(Cell cell){
        if (cell.getState().equals(Cell.State.CLOSED)){
            if (!cell.isFlaged()) gameField.openEmptyCells(cell);
        }
        gameField.repaint();
    }

    @Override
    public void checkGameState(State state) {
        switch (state){
            case WIN:
                break;
            case STARTED:
                break;
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

        return gameField.getBombsCount() == defusedBombs;
    }

    public boolean isWasted(Cell cell){
        return cell.isBomb() && !cell.isFlaged();
    }
}
