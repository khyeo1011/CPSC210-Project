package ui;

import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import model.Game;
import model.GameList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;


public class ViewFrame extends JFrame implements ActionListener {
    private GameList games;
    private DefaultListModel gameList;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JInternalFrame listFrame;
    private JInternalFrame changeFrame;
    private JDesktopPane desktop;
    private JButton changeButton;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField genreField;
    private JComboBox scoreField;
    private int selectedIndex;

    public ViewFrame(GameList games) {
        this.games = games;
        selectedIndex = -1;
        initializeFrame();
        updateList();
    }

    public void updateList() {
        gameList.removeAllElements();
        for (int i = 0; i < games.getSize(); i++) {
            gameList.addElement("[" + i + "] - " + games.getGame(i).getName());
        }
    }

    private void initializeFrame() {
        this.setTitle("Game View");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(GameListUI.WIDTH, GameListUI.HEIGHT);
        setLocationRelativeTo(null);
        desktop = new JDesktopPane();
        initializeListFrame();
        initializeChangeFrame();
        this.setContentPane(desktop);
        desktop.add(listFrame);
        desktop.add(changeFrame);
    }

    private void initializeChangeFrame() {
        changeFrame = new JInternalFrame("Info", true, false);
        changeButton = new JButton("Change!");
        changeFrame.setLayout(new GridLayout(5, 1));
        changeButton.addActionListener(this);
        initializeNameField();
        initializePriceField();
        initializeGenreField();
        initializeScoreField();
        changeFrame.add(changeButton);
        changeFrame.setLocation(GameListUI.WIDTH / 3 + 10, 0);
        changeFrame.pack();
        changeFrame.setVisible(true);
    }

    private void initializeScoreField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel score = new JLabel("Score:");
        score.setVerticalAlignment(JLabel.CENTER);
        score.setHorizontalAlignment(JLabel.CENTER);
        panel.add(score);
        scoreField = new JComboBox(AddGameFrame.SCORES);
        scoreField.setEditable(false);
        panel.add(scoreField);
        changeFrame.add(panel);
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
        genreField.setEditable(false);
        changeFrame.add(panel);
    }

    private void initializePriceField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel price = new JLabel("Price:");
        price.setVerticalAlignment(JLabel.CENTER);
        price.setHorizontalAlignment(JLabel.CENTER);
        panel.add(price);
        priceField = new JTextField();
        priceField.setEditable(false);
        panel.add(priceField);
        changeFrame.add(panel);
    }

    private void initializeNameField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel name = new JLabel("Name:");
        name.setVerticalAlignment(JLabel.CENTER);
        name.setHorizontalAlignment(JLabel.CENTER);
        panel.add(name);
        nameField = new JTextField();
        nameField.setEditable(false);
        panel.add(nameField);
        changeFrame.add(panel);
    }

    private void initializeListFrame() {
        gameList = new DefaultListModel();
        list = new JList<>(gameList);
        list.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(GameListUI.WIDTH / 3, GameListUI.HEIGHT / 3));
        listFrame = new JInternalFrame("Games", true, false);
        listFrame.setLayout(new BorderLayout());
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.pack();
        listFrame.setVisible(true);
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectedIndex = list.getSelectedIndex();
                    changeTextFields(selectedIndex);
                }
            }
        };
        list.addMouseListener(mouseListener);
    }

    private void changeTextFields(int index) {
        nameField.setEditable(true);
        genreField.setEditable(true);
        priceField.setEditable(true);
        Game game = games.getGame(index);
        nameField.setText(game.getName());
        genreField.setText(game.getGenre());
        priceField.setText(Double.toString(game.getPrice()));
        scoreField.setSelectedItem(game.getScore() == -1 ? AddGameFrame.SCORES[0] : Integer.toString(game.getScore()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "You have not selected any game!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (e.getSource() == changeButton) {
            String name = nameField.getText();
            String price = priceField.getText();
            String genre = genreField.getText();
            String score = (String) scoreField.getSelectedItem();
            if (JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to change game with name \""
                            + name + "\"?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                changeGame(name, price, genre, score);
            }
            updateList();
        }
    }

    private void changeGame(String name, String price, String genre, String score) {
        Game game = games.getGame(selectedIndex);
        String originalName = game.getName();
        String originalGenre = game.getGenre();
        game.setGenre(genre);
        game.setName(name);

        try {
            game.setPrice(Double.parseDouble(price));
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(null, "Price is not a number!", "Number",
                    JOptionPane.ERROR_MESSAGE);
            game.setGenre(originalGenre);
            game.setName(originalName);
            return;
        } catch (NegativePriceException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Price!",
                    "Invalid Price!", JOptionPane.ERROR_MESSAGE);
            game.setGenre(originalGenre);
            game.setName(originalName);
            return;
        }
        trySetScore(score, game);
        JOptionPane.showMessageDialog(null, "Successfully Changed!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void trySetScore(String score, Game game) {
        try {
            game.setScore(score.equals("Un-played") ? -1 : Integer.parseInt(score));
            System.out.println(game.getScore());
        } catch (InvalidScoreException ex) {
            throw new RuntimeException(ex);
        }
    }
}
