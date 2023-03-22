package ui;

import model.GameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Gamelist Application
public class GameListUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private static final String DESTINATION = "./data/gamelist.json";
    private AddGameFrame addGameFrame;
    private DeleteGameFrame deleteGameFrame;
    private GameList games;

    public GameListUI() {
        games = new GameList();
        initializeFrames();
        initializeAddButton();
        initializeDeleteButton();
        this.setVisible(true);

    }

    private void initializeDeleteButton() {
        JButton deleteGameButton = new JButton("Delete Game");
        deleteGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGameFrame.setVisible(true);
                deleteGameFrame.refreshComboBox();
            }
        });
        add(deleteGameButton);
    }


    private void initializeAddButton() {
        JButton addGameButton = new JButton("Add Game");
        addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGameFrame.setVisible(true);
            }
        });
        add(addGameButton);
    }


    private void initializeFrames() {
        this.setTitle("Game List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(4,2));
        addGameFrame = new AddGameFrame(games);
        deleteGameFrame = new DeleteGameFrame(games);
    }




    public static void main(String[] args) {
        new GameListUI();
    }


}
