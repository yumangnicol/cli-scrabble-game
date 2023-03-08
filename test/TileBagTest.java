import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.TileBag;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TileBagTest {
    TileBag bag;
    PrintStream standardOut = System.out;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        bag = new TileBag();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        bag = null;
        System.setOut(standardOut);
    }

    @Test
    void testDrawTilesCountLessThanSize() {
        bag.drawTiles(95);
        int expected = 5;
        assertEquals(expected, bag.size());
    }

    @Test
    void testDrawTilesCountGreaterThanSize() {
        bag.drawTiles(95);
        int expected = 5;
        assertEquals(expected, bag.drawTiles(10).size());
    }

    @Test
    void testDrawEmptyBag() {
        bag.drawTiles(100);
        bag.drawTiles(1);
        String expected = "No more tiles in Tile bag!";
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
