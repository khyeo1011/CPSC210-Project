package model;

import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    public Game test;
    public Game testPlayed;

    @BeforeEach
    public void runBefore() {
        try {
            test = new Game("The Legend of Zelda: Breath of the Wild",
                    76.84, "Casual", -1);
        } catch (NegativePriceException e) {
            fail("Unexpected NegativePriceException");
        } catch (InvalidScoreException e) {
            fail("Unexpected InvalidScoreException");
        }
        try {
            testPlayed = new Game("Stardew Valley", 19.99, "Role-playing", 9);
        } catch (NegativePriceException e) {
            fail("Unexpected NegativePriceException");
        } catch (InvalidScoreException e) {
            fail("Unexpected InvalidScoreException");
        }
    }

    @Test
    void testConstructorExceptions(){
        Game game1 = null;
        Game game2 = null;
        Game game3 = null;

        try {
            game1 = new Game("Game1", 1.1, "Genre1", -1);
        } catch (NegativePriceException e) {
            fail("Unexpected NegativePriceException");
        } catch (InvalidScoreException e) {
            fail("Unexpected InvalidScoreException");
        }
        assertEquals("Game1", game1.getName());
        assertEquals(1.1,game1.getPrice());
        assertEquals("Genre1", game1.getGenre());
        assertEquals(-1,game1.getScore());

        try {
            game2 = new Game("Game2", -1000, "Genre2", -1);
            fail("Should have thrown exception");
        } catch (NegativePriceException e) {
            // Pass;
        } catch (InvalidScoreException e) {
            fail("Unexpected InvalidScoreException");
        }

        try{
            game3 = new Game("Game2", 1.2, "Genre2", 100);
            fail("Should have thrown exception");
        } catch (NegativePriceException e) {
            fail("Unexpected NegativePriceException");
        } catch (InvalidScoreException e) {
            // Pass
        }


    }


    @Test
    void testConstructor() {
        assertTrue("The Legend of Zelda: Breath of the Wild".equals(test.getName()));
        assertEquals(76.84, test.getPrice());
        assertTrue("Casual".equals(test.getGenre()));
        assertEquals(-1, test.getScore());
    }

    @Test
    void testHasPlayed() {
        assertFalse(test.hasPlayed());
        assertTrue(testPlayed.hasPlayed());
    }


    @Test
    void testToString() {

        assertTrue(("\tName: [The Legend of Zelda: Breath of the Wild]" +
                "\n\tPrice: $76.84\n\tGenre: [Casual]").equals(test.toString()));
        assertTrue(("\tName: [Stardew Valley]\n\t" + "Price: $19.99\n\tGenre: [Role-playing]"
                + "\n\tScore: 9/10").equals(testPlayed.toString()));
    }

    @Test
    void testSetName() {
        test.setName("Testing Set Name");
        assertTrue("Testing Set Name".equals(test.getName()));
        assertFalse("The Legend of Zelda: Breath of the wild".equals(test.getName()));
    }

    @Test
    void testSetPrice() {
        try {
            test.setPrice(100.20);
        } catch (NegativePriceException e) {
            fail("Unexpected NegativePriceException");
        }
        assertEquals(100.20, test.getPrice());
        try {
            test.setPrice(-12.34);
            fail("Should throw exception");
        } catch (NegativePriceException e) {
            //Pass
        }
        assertEquals(100.20, test.getPrice());
    }

    @Test
    void testSetGenre() {
        test.setGenre("Testing Set Genre");
        assertTrue("Testing Set Genre".equals(test.getGenre()));
        assertFalse("Casual".equals(test.getGenre()));
    }

    @Test
    void testSetScore() {
        try {
            test.setScore(10);
        } catch (InvalidScoreException e) {
            fail("Unexpected InvalidScoreException");
        }
        assertEquals(10, test.getScore());
        try {
            test.setScore(-4);
            fail("Should throw exception");
        } catch (InvalidScoreException e) {
            // Pass
        }
        assertEquals(10, test.getScore());
        try {
            test.setScore(100);
            fail("Should throw exception");
        } catch (InvalidScoreException e) {
            // Pass
        }
        assertEquals(10, test.getScore());
    }
}
