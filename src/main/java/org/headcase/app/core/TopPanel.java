package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopPanel extends JPanel {

    private JButton restartGameButton;
    private TimerLabel timerLabel;
    private FlagsCountLabel flagsCountLabel;

    public TopPanel() {
        init();
    }

    private void init() {
        flagsCountLabel = new FlagsCountLabel();
        flagsCountLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        flagsCountLabel.setFlagsCount(5);
        flagsCountLabel.setAlignmentX(JLabel.CENTER);
        flagsCountLabel.setPreferredSize(new Dimension(80,45));
        timerLabel = new TimerLabel();
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        timerLabel.startTimer();
        restartGameButton = new JButton();
        restartGameButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("think.png")));
        restartGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                restartGameButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("sleepy.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                restartGameButton.setIcon(new ImageIcon(ClassLoader.getSystemResource("think.png")));
            }
        });
        restartGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartGameButton.setMargin(new Insets(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Box box = Box.createHorizontalBox();
        box.add(timerLabel);
        box.add(Box.createHorizontalStrut(30));
        box.add(restartGameButton);
        box.add(Box.createHorizontalStrut(40));
        box.add(flagsCountLabel);
        add(Box.createHorizontalGlue());
        add(box);
        add(Box.createHorizontalGlue());
        setBackground(Color.LIGHT_GRAY);
    }

    public JButton getRestartGameButton() {
        return restartGameButton;
    }

    public TimerLabel getTimerLabel() {
        return timerLabel;
    }

    public FlagsCountLabel getFlagsCountLabel() {
        return flagsCountLabel;
    }

}
