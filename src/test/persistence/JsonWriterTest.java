package persistence;


import model.Game;
import model.GameList;
import model.GameTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The methods for testing the classes inspired or taken from the Dem
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            GameList games = new GameList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGameList() {
        try {
            GameList games = new GameList();
            JsonWriter writer = new JsonWriter("./data/testWritingEmptyGameList.json");
            writer.open();
            writer.write(games);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWritingEmptyGameList.json");
            GameList readGames = reader.read();
            assertEquals(0, readGames.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGameList() {
        try {
            GameList games = new GameList();
            games.addGame(new Game("Half-life",4.99,"First-person shooter",-1));
            games.addGame(new Game("Kerbal Space Program", 19.99, "Simulation", 8));
            games.addGame(new Game("A Dance of Fire and Ice", 3.99, "Rhythm", 7));
            JsonWriter writer = new JsonWriter("./data/testWritingGeneralGameList.json");
            writer.open();
            writer.write(games);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWritingGeneralGameList.json");
            GameList readGames = reader.read();
            assertEquals(3, readGames.getSize());
            checkGame("Half-life",4.99,"First-person shooter", -1,readGames.getGame(0));
            checkGame("Kerbal Space Program",19.99,"Simulation", 8,readGames.getGame(1));
            checkGame("A Dance of Fire and Ice",3.99,"Rhythm", 7,readGames.getGame(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}