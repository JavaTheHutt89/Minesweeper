package org.headcase.app.UI;

import javax.swing.*;

public class TimerLabel extends JLabel {

    private long startTime;
    private Timer timer;
    private int seconds;
    private int minutes;
    private int hours;

    public TimerLabel() {
        super();
        seconds = 0;
        minutes = 0;
        hours = 0;
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

        if (seconds == 60) {
            seconds = 0;
            startTime = 0;
            minutes++;
        }

        if (minutes == 60){
            minutes = 0;
            hours++;
        }

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        setText(time);

        startTime = startTime + 1000;
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
