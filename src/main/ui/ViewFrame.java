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
import java.util.List;

// Frame for viewing / changing games
public class ViewFrame extends JFrame implements ActionListener {
    private GameList games;
    private DefaultListModel gameList;
    private DefaultListModel filterGameList;
    private JList<String> list;
    private JList<String> filterList;
    private JScrollPane filterPane;
    private JScrollPane scrollPane;
    private JInternalFrame listFrame;
    private JInternalFrame changeFrame;
    private JInternalFrame filterFrame;
    private JDesktopPane desktop;
    private JButton changeButton;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField genreField;
    private JComboBox scoreField;
    private JTextField searchGenreField;
    private int selectedIndex;

    // Effects : Constructs a view frame with the GameList
    public ViewFrame(GameList games) {
        this.games = games;
        selectedIndex = -1;
        initializeFrame();
        updateList();
    }

    // Modifies : this
    // Effects : Updates the List that contains the game name
    public void updateList() {
        gameList.removeAllElements();
        for (int i = 0; i < games.getSize(); i++) {
            gameList.addElement("[" + i + "] - " + games.getGame(i).getName());
        }
    }

    // Modifies : this
    // Effects : initializes the frames and internal frames and adds them to the main frame.
    private void initializeFrame() {
        this.setTitle("Game View");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(GameListUI.WIDTH, GameListUI.HEIGHT);
        setLocationRelativeTo(null);
        desktop = new JDesktopPane();
        initializeListFrame();
        initializeChangeFrame();
        initializeFilterFrame();
        this.setContentPane(desktop);
        desktop.add(listFrame);
        desktop.add(changeFrame);
        desktop.add(filterFrame);
    }

    // Modifies : this
    // Effects: Initializes the Filter game internal frame.
    private void initializeFilterFrame() {
        filterGameList = new DefaultListModel();
        filterList = new JList<>(filterGameList);
        filterList.setBorder(new EmptyBorder(10, 10, 10, 10));
        filterPane = new JScrollPane(filterList);
        filterPane.setPreferredSize(new Dimension(GameListUI.WIDTH * 2 / 3, GameListUI.HEIGHT / 3));
        searchGenreField = new JTextField();
        searchGenreField.addActionListener(this);
        filterFrame = new JInternalFrame("Search for Game Names in Genre", true, false);
        filterFrame.setLayout(new BorderLayout());
        filterFrame.add(searchGenreField, BorderLayout.NORTH);
        filterFrame.add(filterPane, BorderLayout.CENTER);
        filterFrame.setLocation(0, GameListUI.HEIGHT / 3 + 30);
        filterFrame.pack();
        filterFrame.setVisible(true);
    }

    // Modifies : this
    // Effects: Initializes the change game internal frame.
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

    // Modifies : this
    // Effects: Initializes the score field
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

    // Modifies : this
    // Effects: Initializes the genre field
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

    // Modifies : this
    // Effects: Initializes the price field
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

    // Modifies : this
    // Effects: Initializes the name field
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

    // Modifies : this
    // Effects: Initializes the list that displays all the games
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

    // Modifies : this
    // Effects : Changes the texts of the fields to the selected game's index
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
    // Modifies: this
    // Effects: Processes user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchGenreField) {
            searchGames(searchGenreField.getText());
            return;
        }
        if (e.getSource() == changeButton) {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(null, "You have not selected any game!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
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

    // Modifies: this
    // Effects : filters the game and updates the filtered list to have the games.
    private void searchGames(String text) {
        List<Game> gamesInGenre = games.gamesInGenre(text);
        filterGameList.removeAllElements();
        if (gamesInGenre.size() == 0) {
            JOptionPane.showMessageDialog(null, "No Games Found!", "None", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (int i = 0; i < gamesInGenre.size(); i++) {
            filterGameList.addElement("• " + gamesInGenre.get(i).getName());
        }
    }

    // Modifies: this
    // Effects : changes the selected game with new fields.
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

    // Modifies: this
    // Effects : helper to set the score for the game.
    private void trySetScore(String score, Game game) {
        try {
            game.setScore(score.equals("Un-played") ? -1 : Integer.parseInt(score));
        } catch (InvalidScoreException ex) {
            throw new RuntimeException(ex);
        }
    }
}
