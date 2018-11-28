package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopPanel extends JPanel {

    private JButton restartGameButton;
    private TimerAndFlagsCountLabel timerAndFlagsCountLabel;

    public TopPanel() {
        init();
    }

    private void init() {
        timerAndFlagsCountLabel = new TimerAndFlagsCountLabel();
        timerAndFlagsCountLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        timerAndFlagsCountLabel.startTimer();
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
        restartGameButton.setSize(45,45);
        restartGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartGameButton.setMargin(new Insets(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(timerAndFlagsCountLabel);
        add(Box.createHorizontalGlue());
        add(restartGameButton);
        add(Box.createHorizontalGlue());
        JLabel jLabel = new JLabel("COUNT");
        jLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(jLabel);
        setBackground(Color.LIGHT_GRAY);
    }

    public JButton getRestartGameButton() {
        return restartGameButton;
    }
}
