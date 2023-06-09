package ui;

import model.GameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

// Frame that enables the user to delete a Game.
public class DeleteGameFrame extends JFrame implements ActionListener {
    private GameList games;
    private JComboBox gameIndices;
    private JTextField gameName;
    private JButton button;

    // Effects : constructs a DeleteGameFrame.
    public DeleteGameFrame(GameList games) {
        this.games = games;
        initializeFrame();
        initializeFields();
        initializeButton();
    }

    // Modifies: this
    // Effects : Initializes delete button
    private void initializeButton() {
        button = new JButton("Delete Game");
        button.addActionListener(this);
        add(button);
    }

    // Modifies: this
    // Effects : Initializes fields that display game info
    private void initializeFields() {
        gameIndices = new JComboBox<>();
        gameIndices.addActionListener(this);
        gameName = new JTextField();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        gameName.setEditable(false);
        panel.add(gameIndices);
        panel.add(gameName);
        add(panel);
    }

    // Modifies: this
    // Effects : Initializes the frame
    private void initializeFrame() {
        this.setTitle("Delete a game");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 300);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);
    }

    @Override
    // Modifies: this
    // Effects : Processes user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameIndices) {
            if (gameIndices.getSelectedItem() != null) {
                gameName.setText(games.getGame(gameIndices.getSelectedIndex()).getName());
            }
        }
        if (e.getSource() == button) {
            if (gameIndices.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select a game",
                        "No Game Selected", JOptionPane.ERROR_MESSAGE);
            } else {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this game?",
                        "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
                    games.deleteGame(gameIndices.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Game Deleted!", "Deleted",
                            JOptionPane.INFORMATION_MESSAGE);
                    refreshComboBox();
                }
            }
        }
    }

    // Modifies: this
    // Effects : Refreshes the items in the comboBox
    public void refreshComboBox() {
        gameIndices.removeAllItems();
        for (int i = 0; i < games.getSize(); i++) {
            gameIndices.addItem((i + ":" + games.getGame(i).getName()));
        }
        gameIndices.setSelectedItem(null);
        gameName.setText("");
    }
}
