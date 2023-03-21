package persistence;


import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import model.Game;
import model.GameList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The methods for testing the classes inspired or taken from the Dem
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterGameListTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            GameList games = new GameList();
            JsonWriterGameList writer = new JsonWriterGameList("./data/my\0illegal:fileName.json");
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGameList() {
        try {
            GameList games = new GameList();
            JsonWriterGameList writer = new JsonWriterGameList("./data/testWritingEmptyGameList.json");
            writer.write(games);
            writer.close();

            JsonReaderGameList reader = new JsonReaderGameList("./data/testWritingEmptyGameList.json");
            GameList readGames = reader.read();
            assertEquals(0, readGames.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGameList() throws NegativePriceException, InvalidScoreException {
        try {
            GameList games = new GameList();
            games.addGame(new Game("Half-life",4.99,"First-person shooter",-1));
            games.addGame(new Game("Kerbal Space Program", 19.99, "Simulation", 8));
            games.addGame(new Game("A Dance of Fire and Ice", 3.99, "Rhythm", 7));
            JsonWriterGameList writer = new JsonWriterGameList("./data/testWritingGeneralGameList.json");
            writer.write(games);
            writer.close();

            JsonReaderGameList reader = new JsonReaderGameList("./data/testWritingGeneralGameList.json");
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