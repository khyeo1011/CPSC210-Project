package ui;

import model.GameList;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

// Code in this class used
// https://www.javatpoint.com/java-plot#:~:text=In%20Java%2C%20plotting%20of%20graph,and%20JPanel%20in%20our%20program.
// As a reference
//
public class GraphFrame extends JFrame {
    private GameList games;


    public GraphFrame(GameList games) {
        this.games = games;
        initialize();
    }



    private void initialize() {
        this.setTitle("Score vs. Price");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(GameListUI.WIDTH, GameListUI.WIDTH);
        this.setResizable(false);
        setLocationRelativeTo(null);
        add(new GraphPanel(games));
    }




}
