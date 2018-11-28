package org.headcase.app.core;

import javax.swing.*;

public class TimerLabel extends JLabel {

    private long startTime;
    private Timer timer;

    public TimerLabel() {
        super();
        resetTimer();
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
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        setText(time);
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
}
