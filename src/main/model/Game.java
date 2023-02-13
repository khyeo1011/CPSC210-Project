package model;


// Represents a single video game.
public class Game {
    private String name;  // The video game's name
    private double price; // The video game's price
    private String genre; // The video game's genre
    private int score;   // The video game's score, out of 10. -1 represents a game that has yet not been played.

    //Effects: Creates a Game, with its name, price in CAD, and a broad genre.
    public Game(String name, double price, String genre) {
        this.name = name;
        this.price = price;
        this.genre = genre;
        this.score = -1;
    }

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




    // Effects: Returns a string of a description of the gam.e
    public String toString(){
        String returnString = "Name: [" + name;
        returnString = returnString +"]\nPrice: $" + price + "\nGenre: [" + genre + "]";
        if(score != -1){
            returnString = returnString + "\nScore: " + score + "/10";
        }
        return returnString;
    }
}
