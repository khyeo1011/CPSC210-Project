package model;


// Represents a single video game.
public class Game {
    private String name;  // The video game's name
    private double price; // The video game's price
    private String genre; // The video game's genre
    private int score;   // The video game's score, out of 10. -1 represents a game that has yet not been played.

    // EFFECTS: Creates a Game, with its name, price in CAD, and a broad genre.
    public Game(String name, double price, String genre) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.score = -1;
    }

    // REQUIRES: 0 <= score <= 10
    // EFFECTS: Creates a game that has been played, with the score.
    public Game(String name, double price, String genre, int score) {
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setScore(int score) {
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
}
