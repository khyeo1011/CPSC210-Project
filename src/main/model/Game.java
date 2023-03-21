package model;


import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import org.json.JSONObject;
import persistence.SaveableAndReadable;

// Represents a single video game.
public class Game implements SaveableAndReadable {
    private String name;  // The video game's name
    private double price; // The video game's price
    private String genre; // The video game's genre
    private int score;   // The video game's score, out of 10. -1 represents a game that has yet not been played.


    // EFFECTS: Creates a Game, with its name, price, broad genre, with the score (-1 for unplayed).
    //          throws NegativePriceException if the price is negative, otherwise
    //          if !(0 <= score <= 10 or score == -1), throws InvalidScoreException;
    public Game(String name, double price, String genre, int score)
            throws NegativePriceException, InvalidScoreException {
        if (price < 0) {
            throw new NegativePriceException();
        }
        if (!((score >= 0 && score <= 10) || score == -1)) {
            throw new InvalidScoreException();
        }
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.score = score;
    }

    // Getters
    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    public int getScore() {
        return score;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Effects: sets the price, throws NegativePriceException if the price is negative
    public void setPrice(double price) throws NegativePriceException {
        if (price < 0) {
            throw new NegativePriceException();
        }
        this.price = price;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Effects: sets the score,
    //          if !(0 <= score <= 10 or score == -1), throws InvalidScoreException;
    public void setScore(int score) throws InvalidScoreException {
        if (!((score >= 0 && score <= 10) || score == -1)) {
            throw new InvalidScoreException();
        }
        this.score = score;
    }

    // EFFECTS: returns whether the game has been played.
    public boolean hasPlayed() {
        return score != -1;
    }

    // EFFECTS: Returns a string of a description of the gam.e
    public String toString() {
        String returnString = "\tName: [" + name;
        returnString = returnString + "]\n\tPrice: $" + price + "\n\tGenre: [" + genre + "]";
        if (score != -1) {
            returnString = returnString + "\n\tScore: " + score + "/10";
        }
        return returnString;
    }

    @Override
    // Effects: Returns the Json of the object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("genre", genre);
        json.put("score", score);
        return json;
    }
}
