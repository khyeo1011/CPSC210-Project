package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

// Code in this class used
// https://www.javatpoint.com/java-plot#:~:text=In%20Java%2C%20plotting%20of%20graph,and%20JPanel%20in%20our%20program.
// As a reference

public class GraphPanel extends JPanel {
    public final static int MARGIN = 60;
    private GameList games;
    private double xScale;
    private double yScale;

    public GraphPanel(GameList games) {
        this.games = games;
    }

    @Override
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

    private void drawAxis(Graphics2D graph, int width, int height) {
        graph.drawString("Relative Price(Scaled Price)", MARGIN-50, MARGIN-10);
        graph.drawString("Score given", width-MARGIN-10,height-MARGIN+20);
    }

    private void drawPoints(Graphics2D g, int width, int height) {
        xScale = (double) (width - 2 * MARGIN) / 10;
        yScale = (double) (height - 2 * MARGIN) / getMaxPriceNoUnPlayed();
        g.setPaint(Color.BLUE);
        for(int i = 0; i < games.getSize(); i++){
            drawPoint(g, width, height, games.getGame(i));
        }
    }

    private void drawPoint(Graphics2D g, int width, int height, Game game) {
        if(game.getScore() == -1){
            return;
        }
        double x = game.getScore() * xScale  + MARGIN;
        double y = height - MARGIN - game.getPrice() * yScale;
        g.fill(new Ellipse2D.Double(x-3,y-3,9,9));
    }

    private double getMaxPriceNoUnPlayed() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < games.getSize(); i++) {
            if (games.getGame(i).getPrice() > max) {
                if(games.getGame(i).getScore() != -1)
                    max = games.getGame(i).getPrice();
            }
        }
        return max;
    }
}
