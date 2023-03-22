package ui;

import model.GameList;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

// Gamelist Application
public class GameListUI extends JFrame{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String DESTINATION = "./data/gamelist.json";
    private GameList games;
    private JPanel addAndDeletePane;
    private JPanel addPanel;
    private JComboBox score;
    private JTextField name;
    private JTextField genre;
    private JFormattedTextField price;
    public static final String scores[] = {"Un-played", "0", "1","2","3","4","5","6","7","8","9"};

    public GameListUI() {
        initializeFrame();
        initializeTopPane();

    }

    void initializeTopPane(){
        addAndDeletePane = new JPanel();
        addAndDeletePane.setLayout(new GridLayout(1,2));
        addAndDeletePane.setBorder(new LineBorder(Color.LIGHT_GRAY));
        addGamePane();
        addDeletePane();
        add(addAndDeletePane);
    }

    private void addGamePane() {
        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel,BoxLayout.Y_AXIS));
        addNameField();
        addPriceField();
        addGenreField();
        addScoreField();
        addAndDeletePane.add(addPanel);
    }

    private void addNameField() {
        JLabel label = new JLabel("Name");
        name = new JTextField();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);
        panel.add(name);
        addPanel.add(panel);
    }

    private void addPriceField() {
    }

    private void addGenreField() {

    }

    private void addScoreField() {

    }

    private void addDeletePane() {

    }


    private void initializeFrame() {
        this.setTitle("Game List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(3,1));
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new GameListUI();
    }

}
