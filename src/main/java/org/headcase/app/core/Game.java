package org.headcase.app.core;

import java.util.ArrayList;

public class Game implements GameStateListener{

    enum State {
        STARTED,
        WIN,
        GAMEOVER;
    }

    private ArrayList<GameStateListener> gameStateListeners;
    private State gameState;

    public Game() {
        this.gameState = State.STARTED;
        gameStateListeners = new ArrayList<>();
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

    @Override
    public void checkGameState() {
        switch (gameState){
            case WIN:
                System.out.println("Win!");break;
            case STARTED:
                System.out.println("Started");break;
            case GAMEOVER:
                System.out.println("You lose");break;
            default: break;
        }
    }


}
