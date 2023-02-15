package model;

import java.util.ArrayList;

// This class represents the game list.
public class GameList {
    private ArrayList<Game> games;

    public GameList() {
        games = new ArrayList<>();
    }

    // REQUIRES: game is not null.
    // MODIFIES: this.
    // EFFECTS: Adds a game to the list if the name is not present. Returns true if added, false otherwise.
    public boolean addGame(Game game) {
        for(Game g : games){
            if(game.getName().equals(g.getName())){
                return false;
            }
        }
        games.add(game);
        return true;
    }

    // EFFECTS: Returns the size of the list.
    public int getSize() {
        return games.size();
    }

    // REQUIRES: 0 <= index < games.size()
    // EFFECTS:  Returns game at index;
    public Game getGame(int index) {
        return games.get(index);
    }

    public double totalPrice() {
        double sum = 0;
        for (Game game : games) {
            sum += game.getPrice();
        }
        return sum;
    }

    // REQUIRES: games.size() > 0
    // EFFECTS : returns the average price of the games.
    public double avgPrice() {
        return totalPrice() / getSize();
    }

}
