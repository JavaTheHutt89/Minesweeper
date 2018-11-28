package org.headcase.app.core;

import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerAndFlagsCountLabel extends JLabel {

    private long startTime;
    private Timer timer;
    private int flagsCount;
    private Game game;

    public TimerAndFlagsCountLabel(Game game) {
        super();
        resetTimer();
        this.game = game;
    }

    private Timer getTimer(){
        if (timer == null){
            int delay = 1000; //milliseconds
            timer = new Timer(delay, e -> taskPerformed());
            timer.setInitialDelay(0);
        }
        return timer;
    }

    private void taskPerformed() {
        int seconds = (int) startTime / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        String time = String.format("Timer: %02d:%02d:%02d", hours, minutes, seconds);
        setText(time + " | Flags: " + flagsCount);
        startTime = startTime + 1000; // milliseconds
    }

    public void resetTimer(){
        setText("00:00:00");
    }

    public synchronized void startTimer(){
        startTime = 0;
        getTimer().start();
    }

    public synchronized void stopTimer(){
        getTimer().stop();
    }

    public boolean isTimerRunning(){
        return getTimer().isRunning();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setFlagsCount(int flagsCount) {
        this.flagsCount = flagsCount;
    }
}
