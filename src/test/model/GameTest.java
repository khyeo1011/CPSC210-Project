package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    public Game test;
    public Game testPlayed;

    @BeforeEach
    public void runBefore() {
        test = new Game("The Legend of Zelda: Breath of the Wild",
                76.84, "Casual");
        testPlayed = new Game("Stardew Valley", 19.99, "Role-playing", 9);
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
        test.setPrice(100.20);
        assertEquals(100.20, test.getPrice());
        test.setPrice(12.34);
        assertEquals(12.34, test.getPrice());
    }

    @Test
    void testSetGenre() {
        test.setGenre("Testing Set Genre");
        assertTrue("Testing Set Genre".equals(test.getGenre()));
        assertFalse("Casual".equals(test.getGenre()));
    }

    @Test
    void testSetScore() {
        test.setScore(10);
        assertEquals(10, test.getScore());
        test.setScore(4);
        assertEquals(4, test.getScore());
    }
}
