package model;

import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest {
    GameList test;
    Game game1;
    Game game2;
    Game game3;

    @BeforeEach
    void setUp() {
        test = new GameList();
        try {
            game1 = new Game("Test 1", 15, "Test 1", -1);
        } catch (NegativePriceException e) {
            throw new RuntimeException(e);
        } catch (InvalidScoreException e) {
            throw new RuntimeException(e);
        }
        try {
            game2 = new Game("Test 2", 90, "Test 2", 7);
        } catch (NegativePriceException e) {
            throw new RuntimeException(e);
        } catch (InvalidScoreException e) {
            throw new RuntimeException(e);
        }
        try {
            game3 = new Game("Test 3", 45, "Test 3", -1);
        } catch (NegativePriceException e) {
            throw new RuntimeException(e);
        } catch (InvalidScoreException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddGame() {
        test.addGame(game1);
        test.addGame(game3);
        test.addGame(game2);
        assertEquals(game1, test.getGame(0));
        assertEquals(game2, test.getGame(2));
        assertEquals(game3, test.getGame(1));
    }

    @Test
    void testAddGameDuplicate() {
        boolean success1 = test.addGame(game1);
        boolean success2 = test.addGame(game1);
        boolean success3 = false;
        try {
            success3 = test.addGame(new Game("Test 1", 40, "Test 1", -1));
        } catch (NegativePriceException e) {
            throw new RuntimeException(e);
        } catch (InvalidScoreException e) {
            throw new RuntimeException(e);
        }
        assertTrue(success1);
        assertFalse(success2);
        assertFalse(success3);
        assertEquals(1, test.getSize());
        assertEquals(game1, test.getGame(0));
    }

    @Test
    void testGetSize() {
        assertEquals(0, test.getSize());
        test.addGame(game1);
        assertEquals(1, test.getSize());
        test.addGame(game2);
        test.addGame(game3);
        assertEquals(3, test.getSize());
    }

    @Test
    void testGetGame() {
        test.addGame(game1);
        test.addGame(game3);
        test.addGame(game2);
        assertEquals(game1, test.getGame(0));
        assertEquals(game2, test.getGame(2));
        assertEquals(game3, test.getGame(1));
    }

    @Test
    void testAvgPrice() {
        test.addGame(game1);
        test.addGame(game3);
        test.addGame(game2);
        assertEquals(50, test.avgPrice());
    }

    @Test
    void testTotalPrice() {
        test.addGame(game1);
        test.addGame(game3);
        test.addGame(game2);
        assertEquals(150, test.totalPrice());
    }

    @Test
    void testDeleteGame() {
        test.addGame(game1);
        test.addGame(game2);
        test.addGame(game3);
        test.deleteGame(1);
        assertEquals(2, test.getSize());
        assertEquals(game1, test.getGame(0));
        assertEquals(game3, test.getGame(1));
    }

    @Test
    void testGamesInGenre() {
        test.addGame(game1);
        test.addGame(game2);
        test.addGame(game3);
        game3.setGenre("Test 1");
        List<Game> games = test.gamesInGenre(game1.getGenre());
        assertEquals(2, games.size());
        assertEquals(game1, games.get(0));
        assertEquals(game3, games.get(1));

    }

    @Test
    void testDeleteIndexNegative() {
        test.addGame(game1);
        try {
            test.deleteGame(-1);
            fail("Should throw exception");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
        assertEquals(game1, test.getGame(0));
        assertEquals(1,test.getSize());
    }

    @Test
    void testDeleteIndexAboveSize(){
        test.addGame(game1);
        test.addGame(game2);

        try {
            test.deleteGame(4012);
            fail("Should throw exception");
        } catch (IndexOutOfBoundsException e) {
            // pass
        }
        assertEquals(game1, test.getGame(0));
        assertEquals(2,test.getSize());
    }
}