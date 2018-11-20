package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    private MouseListener mouseListener;
    private Game game;

    public GamePanel(int cols, int rows) {
        newGame(cols, rows);
        init();
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Cell cell = (Cell) e.getSource();
                if (e.getButton() == 3) {
                    game.setFlag(cell);
                    if (game.isWin()) {
                        System.out.println("You win!");
                    }
                }
                if (e.getButton() == 1) {
                    game.openCell(cell);
                    if (game.isWasted(cell))
                        game.setGameState(Game.State.GAMEOVER);
                }
            }
        };
    }

    private void init(){
        GridLayout gridLayout = new GridLayout(game.getRows(), game.getCols());
        setLayout(gridLayout);
        render();
    }

    private void render() {
        Field field = game.getGameField();
        Cell[][] cells = field.getCells();
        for (Cell[] cell1 : cells) {
            for (int j = 0; j < field.getCols(); j++) {
                Cell cell = cell1[j];
                cell.addMouseListener(mouseListener);
                add(cell);
            }
        }
    }

    private void newGame(int cols, int rows) {
        game = new Game(cols,rows);
    }
}
