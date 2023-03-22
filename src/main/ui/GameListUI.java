package ui;

import model.GameList;
import persistence.JsonReaderGameList;
import persistence.JsonWriterGameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Gamelist Application
public class GameListUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;
    private static final String DEFAULT_DESTINATION = "./data/gamelist.json";
    private AddGameFrame addGameFrame;
    private DeleteGameFrame deleteGameFrame;
    private JButton saveButton;
    private JButton loadButton;
    private JButton avgButton;
    private JButton totalButton;
    private GameList games;

    public GameListUI() {
        games = new GameList();
        initializeFrames();
        initializeAddButton();
        initializeDeleteButton();
        initializeSaveButton();
        initializeLoadButton();
        initializeAvgButton();
        initializeTotalButton();
        this.setVisible(true);

    }

    private void initializeTotalButton() {
        totalButton = new JButton("Compute Total Price");
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(games.getSize() == 0){
                    JOptionPane.showMessageDialog(null,"No Games!", "Total Price",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double total = games.totalPrice();
                JOptionPane.showMessageDialog(null,"Total is: " + total, "Total Price",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(totalButton);
    }

    private void initializeAvgButton() {
        avgButton = new JButton("Compute Average Price");
        avgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(games.getSize() == 0){
                    JOptionPane.showMessageDialog(null,"No Games!", "Average Price",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double avg = games.avgPrice();
                JOptionPane.showMessageDialog(null,"Average is: " + avg, "Average Price",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(avgButton);
    }

    private void initializeLoadButton() {
        loadButton = new JButton("Load from Default");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReaderGameList reader = new JsonReaderGameList(DEFAULT_DESTINATION);
                try {
                    games = reader.read();
                    addGameFrame = new AddGameFrame(games);
                    deleteGameFrame = new DeleteGameFrame(games);
                    JOptionPane.showMessageDialog(null,"Loading Successful!",
                            "Loading Successful!",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Failed to load", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loadButton);
    }

    private void initializeSaveButton() {
        saveButton = new JButton("Save to Default");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(games.getSize() == 0){
                    JOptionPane.showMessageDialog(null,"No Games to save!", "Save",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    JsonWriterGameList writer = new JsonWriterGameList(DEFAULT_DESTINATION);
                    writer.write(games);
                    writer.close();
                    JOptionPane.showMessageDialog(null,"Save Successful!", "Save Successful!",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Failed to open destination", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        add(saveButton);
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
        setLayout(new GridLayout(5, 2));
        // Add, delete, save, load, avg price, total price, graph,view, save as /load from
        setLocationRelativeTo(null);
        addGameFrame = new AddGameFrame(games);
        deleteGameFrame = new DeleteGameFrame(games);
    }


    public static void main(String[] args) {
        new GameListUI();
    }


}
