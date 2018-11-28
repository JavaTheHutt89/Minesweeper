package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements GameStateListener{

    private final String NAME = "Minesweeper";
    private GamePanel gamePanel;

    public TimerAndFlagsCountLabel getTimerAndFlagsCountLabel() {
        return timerAndFlagsCountLabel;
    }

    private TimerAndFlagsCountLabel timerAndFlagsCountLabel;
    private JPanel jPanel;

    public Window() {
        gamePanel = new GamePanel(10,10);
        gamePanel.getGame().addListener(this);
        setupTimer();
        init();
    }

    private void init() {
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(gamePanel, BorderLayout.CENTER);
        jPanel.add(timerAndFlagsCountLabel, BorderLayout.AFTER_LAST_LINE);
        setName(NAME);
        setContentPane(jPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        repaint();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void checkGameState(Game.State state) {
        if (state.equals(Game.State.GAMEOVER)){
            timerAndFlagsCountLabel.stopTimer();
            String[] options = {"Try again?"};
            int position = JOptionPane.showOptionDialog(
                    null, new WinOrLoosePanel(state),"ПОТРАЧЕНО!",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
            if (position == 0){
                restartTheGame();
                refreshFlagsCount();
            }
        }
        if (state.equals(Game.State.WIN)){
            timerAndFlagsCountLabel.stopTimer();
            String[] options = {"Beginner", "Advanced", "Expert", "Try again?"};
            int position = JOptionPane.showOptionDialog(
                    null, new WinOrLoosePanel(state),"ЭТО ПОБЕДА",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[3]);
            if (position == 3){
                restartTheGame();
                refreshFlagsCount();
            }
            if (position == 0){
                startTheNewGame(10,10);
                refreshFlagsCount();
            }
            if (position == 1){
                startTheNewGame(15,15);
                refreshFlagsCount();
            }
            if (position == 2){
                startTheNewGame(20,20);
                refreshFlagsCount();
            }
        }
    }

    public void startTheNewGame(int cols, int rows){
        jPanel.remove(gamePanel);
        gamePanel = new GamePanel(cols, rows);
        gamePanel.getGame().addListener(this);
        jPanel.add(gamePanel);
        jPanel.repaint();
        pack();
        repaint();
        timerAndFlagsCountLabel.resetTimer();
        timerAndFlagsCountLabel.startTimer();
    }

    public void restartTheGame(){
        startTheNewGame(gamePanel.getGame().getCols(),gamePanel.getGame().getRows());
    }

    private void setupTimer(){
        timerAndFlagsCountLabel = new TimerAndFlagsCountLabel(gamePanel.getGame());
        timerAndFlagsCountLabel.resetTimer();
        timerAndFlagsCountLabel.startTimer();
        gamePanel.getGame().setTimerAndFlagsCountLabel(timerAndFlagsCountLabel);
    }

    public void refreshFlagsCount(){
        gamePanel.getGame().setTimerAndFlagsCountLabel(timerAndFlagsCountLabel);
    }
}