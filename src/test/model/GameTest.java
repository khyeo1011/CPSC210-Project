package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    public Game test;
    public Game testPlayed;

    @BeforeEach
    public void runBefore(){
        test = new Game("The Legend of Zelda: Breath of the Wild",
                76.84, "Casual");
        testPlayed = new Game("Stardew Valley", 19.99,"Role-playing", 9);
    }

    @Test
    void testConstructor(){
        assertTrue("The Legend of Zelda: Breath of the Wild".equals(test.getName()));
        assertEquals(76.84,test.getPrice());
        assertTrue("Casual".equals(test.getGenre()));
        assertEquals(-1, test.getScore());
    }

    @Test
    void testToString(){
        System.out.println(testPlayed);
        assertTrue(("Name: [The Legend of Zelda: Breath of the Wild]\n" +
                "Price: $76.84\nGenre: [Casual]").equals(test.toString()));
        assertTrue(("Name: [Stardew Valley]\n" + "Price: $19.99\nGenre: [Role-playing]"
                + "\nScore: 9/10").equals(testPlayed.toString()));
    }
}
