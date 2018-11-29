package org.headcase.app.UI;

import org.headcase.app.core.Cell;
import org.headcase.app.core.Field;
import org.headcase.app.core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    private MouseListener mouseListener;

    public Game getGame() {
        return game;
    }

    private Game game;

    public GamePanel(int cols, int rows) {
        newGame(cols, rows);
        mouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Cell cell = (Cell) e.getSource();
                if (e.getButton() == 3) {
                    game.setFlag(cell);
                    if (game.isWin()) {
                        game.setGameState(Game.State.WIN);
                    }
                }
                if (e.getButton() == 1) {
                    game.openCell(cell);
                    if (game.isWasted(cell))
                        game.setGameState(Game.State.GAMEOVER);
                }
            }
        };
        init();
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

    public void newGame(int cols, int rows) {
        game = new Game(cols,rows);
    }
}
