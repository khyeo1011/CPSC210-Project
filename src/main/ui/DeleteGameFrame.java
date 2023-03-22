package ui;

import model.GameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class DeleteGameFrame extends JFrame implements ActionListener {
    private GameList games;
    private JComboBox gameIndices;
    private JTextField gameName;
    private JButton button;

    public DeleteGameFrame(GameList games) {
        this.games = games;
        initializeFrame();
        initializeFields();
        initializeButton();
    }

    private void initializeButton() {
        button = new JButton("Delete Game");
        button.addActionListener(this);
        add(button);
    }

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

    private void initializeFrame() {
        this.setTitle("Delete a game");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 300);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);
    }

    @Override
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

    public void refreshComboBox() {
        gameIndices.removeAllItems();
        for (int i = 0; i < games.getSize(); i++) {
            gameIndices.addItem((i + ":" + games.getGame(i).getName()));
        }
        gameIndices.setSelectedItem(null);
        gameName.setText("");
    }
}
