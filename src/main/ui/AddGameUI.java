package ui;

import javax.swing.*;
import java.awt.*;

public class AddGameUI {
    private JPanel addGamePanel;
    private Button submit;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField genreField;
    private JTextField scoreField;

    public AddGameUI(JFrame frame) {
        initializeAddGamePanel();
        frame.add(addGamePanel);
    }

    private void initializeAddGamePanel() {
        addGamePanel = new JPanel();
        addGamePanel.setSize(GameListUI.WIDTH / 3, GameListUI.HEIGHT / 3);
        addGamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        addGamePanel.setBackground(Color.RED);

    }


}
