package ui;

import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import model.Game;
import model.GameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class AddGameFrame extends JFrame implements ActionListener {
    private final String[] scores = {"Un-played", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private JTextField nameField;
    private JTextField priceField;
    private JTextField genreField;
    private JComboBox scoreField;
    private JButton submit;
    private JButton clear;
    private GameList games;

    public AddGameFrame(GameList games) {
        this.games = games;
        initializeFrame();
        initializeNameField();
        initializePriceField();
        initializeGenreField();
        initializeScoreField();
        initializeSubmitButton();
    }

    private void initializeSubmitButton() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        clear = new JButton("Clear");
        clear.addActionListener(this);
        submit = new JButton("Add Game!");
        submit.addActionListener(this);
        panel.add(submit);
        panel.add(clear);
        add(panel);
    }

    private int getScore() {
        String result = (String) scoreField.getSelectedItem();
        if (result.equals("Un-played")) {
            return -1;
        } else {
            return Integer.parseInt(result);
        }

    }

    private void initializeScoreField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel score = new JLabel("Genre:");
        score.setVerticalAlignment(JLabel.CENTER);
        score.setHorizontalAlignment(JLabel.CENTER);
        panel.add(score);
        scoreField = new JComboBox(scores);
        panel.add(scoreField);
        add(panel);
    }

    private void initializeGenreField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel genre = new JLabel("Genre:");
        genre.setVerticalAlignment(JLabel.CENTER);
        genre.setHorizontalAlignment(JLabel.CENTER);
        panel.add(genre);
        genreField = new JTextField();
        panel.add(genreField);
        add(panel);
    }

    private void initializePriceField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel price = new JLabel("Price:");
        price.setVerticalAlignment(JLabel.CENTER);
        price.setHorizontalAlignment(JLabel.CENTER);
        panel.add(price);
        priceField = new JTextField();
        panel.add(priceField);
        add(panel);
    }

    private void initializeFrame() {
        this.setTitle("Add a Game!");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 200);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);
    }

    private void initializeNameField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel name = new JLabel("Name:");
        name.setVerticalAlignment(JLabel.CENTER);
        name.setHorizontalAlignment(JLabel.CENTER);
        panel.add(name);
        nameField = new JTextField();
        panel.add(nameField);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            clearFields();
        }
        if (e.getSource() == submit) {
            String name = new String(nameField.getText());
            String price = priceField.getText();
            String genre = new String(priceField.getText());
            String score = (String) scoreField.getSelectedItem();
            boolean success = false;
            try {
                success = addGame(name, Double.parseDouble(price), genre,
                        score.equals("Un-played") ? -1 : Integer.parseInt(score));
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(null, "Price is not a number!", "Number",
                        JOptionPane.ERROR_MESSAGE);
            }
            printMessage(success);


        }
    }

    private void printMessage(boolean success) {
        if (!success) {
            JOptionPane.showMessageDialog(null,
                    "Adding Game Failed... There might be a game with the same name or an error",
                    "Fail", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Game Added Successfully!",
                    "Game added", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        }
    }

    private boolean addGame(String name, Double price, String genre, int score) {
        Game game;
        boolean success = false;
        try {
            game = new Game(name, price, genre, score);
            success = games.addGame(game);
            System.out.println(game);
        } catch (NegativePriceException e) {
            JOptionPane.showMessageDialog(null, "Invalid Price!",
                    "Invalid Price!", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidScoreException e) {
            throw new RuntimeException(e);
        }
        return success;

    }

    private void clearFields() {
        priceField.setText("");
        nameField.setText("");
        genreField.setText("");
        scoreField.setSelectedItem(null);
    }
}
