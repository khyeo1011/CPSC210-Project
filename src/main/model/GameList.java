package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.SaveableAndReadable;

import java.util.ArrayList;
import java.util.List;

// This class represents the game list.
public class GameList implements SaveableAndReadable {
    private ArrayList<Game> games;

    public GameList() {
        games = new ArrayList<>();
    }

    // REQUIRES: game is not null.
    // MODIFIES: this.
    // EFFECTS: Adds a game to the list if the name is not present. Returns true if added, false otherwise.
    public boolean addGame(Game game) {
        for (Game g : games) {
            if (game.getName().equals(g.getName())) {
                return false;
            }
        }
        games.add(game);
        EventLog.getInstance().logEvent(new Event("Added Game with name " + game.getName()));
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
        EventLog.getInstance().logEvent(new Event("Calculated Total Price: " + sum));
        return sum;
    }

    // REQUIRES: games.size() > 0
    // EFFECTS : returns the average price of the games.
    public double avgPrice() {
        double avg = totalPrice() / getSize();
        EventLog.getInstance().logEvent(new Event("Calculated Average Price: " + avg));
        return avg;

    }

    @Override
    // Effects: Returns the Json of a gamelist
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", gamesToJson());
        return json;
    }

    // Effects: Returns the Json array of all the games.
    public JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Game game : games) {
            jsonArray.put(game.toJson());
        }
        return jsonArray;
    }

    // Modifies: this
    // Effects: deletes game at selected index
    //          throws IndexOutOfBoundsException if index is not present;
    public void deleteGame(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException();
        }
        EventLog.getInstance().logEvent(new Event("Deleted Game with name " + games.get(index).getName()));
        games.remove(index);
    }


    // Effects: Returns List with games in the same genre
    public List<Game> gamesInGenre(String genre) {
        List<Game> ret = new ArrayList<>();
        for (Game game : games) {
            if (genre.equals(game.getGenre())) {
                ret.add(game);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for genre: " + genre
                + " and found " + ret.size() + " games"));
        return ret;
    }

}
