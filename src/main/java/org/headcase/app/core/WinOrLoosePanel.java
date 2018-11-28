package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;

public class WinOrLoosePanel extends JPanel {

    private ImageIcon imageIcon;
    private JLabel imageLablel;
    private JLabel textLablel;

    public WinOrLoosePanel(Game.State state) {
        setLayout(new BorderLayout());
        this.imageLablel = new JLabel();
        this.textLablel = new JLabel();
        if (state.equals(Game.State.WIN)){
            this.imageIcon = new ImageIcon(ClassLoader.getSystemResource("win.gif"));
            this.textLablel.setFont(new Font("Time New Roman", Font.BOLD, 16));
            this.textLablel.setText("Выбрать другую сложность?");
            this.textLablel.setHorizontalTextPosition(SwingConstants.CENTER);
        } else this.imageIcon = new ImageIcon(ClassLoader.getSystemResource("wasted.gif"));
        imageLablel.setIcon(imageIcon);
        add(textLablel,BorderLayout.SOUTH);
        add(imageLablel,BorderLayout.CENTER);
    }
}
