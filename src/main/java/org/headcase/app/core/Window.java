package org.headcase.app.core;

import javax.swing.*;

public class Window extends JFrame implements GameStateListener{

    private final String NAME = "Minesweeper";
    private GamePanel gamePanel;

    public Window() {
        gamePanel = new GamePanel(5,5);
        gamePanel.getGame().addListener(this);
        init();
    }

    private void init() {
        setName(NAME);
        setContentPane(gamePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pack();
        repaint();
    }

    @Override
    public void checkGameState(Game.State state) {
        if (state.equals(Game.State.GAMEOVER)){
            String[] options = {"Try again?"};
            int position = JOptionPane.showOptionDialog(
                    null, new WinOrLoosePanel(state),"ПОТРАЧЕНО!",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
            if (position == 0){
                restartTheGame();
            }
        }
        if (state.equals(Game.State.WIN)){
            String[] options = {"Beginner", "Advanced", "Expert", "Try again?"};
            int position = JOptionPane.showOptionDialog(
                    null, new WinOrLoosePanel(state),"ЭТО ПОБЕДА",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[3]);
            if (position == 3){
                restartTheGame();
            }
            if (position == 0){
                startTheNewGame(10,10);
            }
            if (position == 1){
                startTheNewGame(15,15);
            }
            if (position == 2){
                startTheNewGame(20,20);
            }
        }
    }

    public void startTheNewGame(int cols, int rows){
        GamePanel gamePanel = new GamePanel(cols, rows);
        gamePanel.getGame().addListener(this);
        setContentPane(gamePanel);
        pack();
        repaint();
    }

    public void restartTheGame(){
        startTheNewGame(gamePanel.getGame().getCols(),gamePanel.getGame().getRows());
    }
}