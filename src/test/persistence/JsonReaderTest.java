package persistence;


import model.Game;
import model.GameList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The methods for testing the classes inspired or taken from the Demo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    // This test was taken from the Demo
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            GameList games = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGameList() {
        JsonReader reader = new JsonReader("./data/testReadingEmptyGameList.json");
        try {
            GameList games = reader.read();
            assertEquals(0, games.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameList() {
        JsonReader reader = new JsonReader("./data/testReadingGeneralGameList.json");
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
}
