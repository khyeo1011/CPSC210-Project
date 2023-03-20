package ui;

import model.GameList;
import javax.swing.*;

// Gamelist Application
public class GameListUI {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String DESTINATION = "./data/gamelist.json";
    private GameList games;
    private JFrame frame;
    private addGameUI addGameUI;

    public GameListUI(){
        frame = new JFrame();
        initializeFrame();
    }

    private void initializeFrame(){
        frame.setTitle("Game List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
    }
}
