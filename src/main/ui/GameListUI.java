package ui;

import model.GameList;

import javax.swing.*;

// Gamelist Application
public class GameListUI {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String DESTINATION = "./data/gamelist.json";
    private GameList games;
    private JFrame frame;
    private AddGameUI addGamePanel;

    public GameListUI() {
        initializeFrame();
        addGamePanel = new AddGameUI(frame);
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setTitle("Game List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }


}
