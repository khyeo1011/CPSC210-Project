package persistence;

import model.Game;

import static org.junit.jupiter.api.Assertions.*;

// The methods for testing the classes inspired or taken from the Dem
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkGame(String name, double price, String genre, int score, Game game) {
        assertEquals(name,game.getName());
        assertEquals(price,game.getPrice());
        assertEquals(score,game.getScore());
        assertEquals(genre,game.getGenre());
    }
}
