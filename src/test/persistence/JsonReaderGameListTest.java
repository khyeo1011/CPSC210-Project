package persistence;


import model.GameList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The methods for testing the classes inspired or taken from the Demo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderGameListTest extends JsonTest {

    @Test
        // This test was taken from the Demo
    void testReaderNonExistentFile() {
        JsonReaderGameList reader = new JsonReaderGameList("./data/nonExistentFile.json");
        try {
            GameList games = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGameList() {
        JsonReaderGameList reader = new JsonReaderGameList("./data/testReadingEmptyGameList.json");
        try {
            GameList games = reader.read();
            assertEquals(0, games.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameList() {
        JsonReaderGameList reader = new JsonReaderGameList("./data/testReadingGeneralGameList.json");
        try {
            GameList games = reader.read();
            assertEquals(3, games.getSize());
            checkGame("The Legend of Zelda", 69.99, "Role-playing", 9, games.getGame(0));
            checkGame("FIFA 23", 59.99, "Sports", -1, games.getGame(1));
            checkGame("Stardew Valley", 19.99, "Role-playing", 10, games.getGame(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNegativePrice() {
        JsonReaderGameList reader = new JsonReaderGameList("./data/testReadingNegativePrice.json");
        try {
            GameList games = reader.read();
            assertEquals(0, games.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testReaderInvalidScore() {
        JsonReaderGameList reader = new JsonReaderGameList("./data/testReadingInvalidScore.json");
        try {
            GameList games = reader.read();
            assertEquals(0, games.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



