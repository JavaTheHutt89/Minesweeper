package org.headcase.app.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.stream.Stream;


public class Window extends JFrame{

    private Game game;
    private MouseListener mouseListener;
    private MenuBar menuBar;
    private Menu menu;

    public Window(String name, int cols, int rows) {
        setupMenuBar();
        game = new Game(cols, rows);
        setName(name);
        init();
        pack();
    }

    private void setupMenuBar() {
        menuBar = new MenuBar();
        menu = new Menu("Game settings");
        MenuItem beginnerItem = new MenuItem("Beginner");
        menu.add(beginnerItem);
        MenuItem advansedItem = new MenuItem("Advanced");
        menu.add(advansedItem);
        MenuItem expertItem = new MenuItem("Expert");
        menu.add(expertItem);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                menuActions(e.getActionCommand());
            }
        });
        menuBar.add(menu);
    }


    private void init() {
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
        setMenuBar(menuBar);
        GridLayout gridLayout = new GridLayout(game.getRows(), game.getCols());
        setLayout(gridLayout);
        render();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
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

    private void menuActions(String actionCommand){
        switch (actionCommand){
            case "Beginner":
                game = new Game(20,20);
                repaint();
                break;
            case "Advanced": break;
            case "Expert": break;
            default: break;
        }
    }
}