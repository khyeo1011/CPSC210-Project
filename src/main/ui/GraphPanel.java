package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

// Code in this class used
// https://www.javatpoint.com/java-plot#:~:text=In%20Java%2C%20plotting%20of%20graph,and%20JPanel%20in%20our%20program.
// As a reference

// Panel that stores the graph
public class GraphPanel extends JPanel {
    public static final int MARGIN = 60;
    private GameList games;
    private double horiScale;
    private double vertScale;

    // Effects : Constructs a GraphPanel
    public GraphPanel(GameList games) {
        this.games = games;
    }

    @Override
    // Modifies : this
    // Effects : Draws the graph
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        graph.draw(new Line2D.Double(MARGIN, MARGIN, MARGIN, height - MARGIN));
        graph.draw(new Line2D.Double(MARGIN, height - MARGIN, width - MARGIN, height - MARGIN));
        drawPoints(graph, width, height);
        graph.setPaint(Color.BLACK);
        drawAxis(graph, width, height);
    }

    // Modifies : this
    // Effects : Draws the axis
    private void drawAxis(Graphics2D graph, int width, int height) {
        graph.drawString("Relative Price(Scaled Price)", MARGIN - 50, MARGIN - 10);
        graph.drawString("Score given", width - MARGIN - 10, height - MARGIN + 20);
    }

    // Modifies : this
    // Effects : Draws the points
    private void drawPoints(Graphics2D g, int width, int height) {
        horiScale = (double) (width - 2 * MARGIN) / 10;
        vertScale = (double) (height - 2 * MARGIN) / getMaxPriceNoUnPlayed();
        g.setPaint(Color.BLUE);
        for (int i = 0; i < games.getSize(); i++) {
            drawPoint(g, width, height, games.getGame(i));
        }
    }

    // Modifies : this
    // Effects : Draws one single point
    private void drawPoint(Graphics2D g, int width, int height, Game game) {
        if (game.getScore() == -1) {
            return;
        }
        double x = game.getScore() * horiScale + MARGIN;
        double y = height - MARGIN - game.getPrice() * vertScale;
        g.fill(new Ellipse2D.Double(x - 3, y - 3, 9, 9));
    }

    // Effects : Returns max price of playes games.
    private double getMaxPriceNoUnPlayed() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < games.getSize(); i++) {
            if (games.getGame(i).getPrice() > max) {
                if (games.getGame(i).getScore() != -1) {
                    max = games.getGame(i).getPrice();
                }
            }
        }
        return max;
    }
}
