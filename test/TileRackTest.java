import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.Tile;
import pij.main.TileBag;
import pij.main.TileRack;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TileRackTest {
    TileRack rack;
    TileBag bag;

    @BeforeEach
    void setUp() {
        rack = new TileRack();
        bag = new TileBag();
    }

    @AfterEach
    void tearDown() {
        rack = null;
        bag = null;
    }

    @Test
    void testContainsAllTrue() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('A'));
        tiles.add(new Tile('B'));
        tiles.add(new Tile('C'));
        tiles.add(new Tile('D'));
        tiles.add(new Tile('E'));
        tiles.add(new Tile('F'));
        tiles.add(new Tile('A'));
        rack.setTiles(tiles);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile('B'));
        tiles2.add(new Tile('A'));
        tiles2.add(new Tile('A'));

        assertTrue(rack.containsAll(tiles2));
    }

    @Test
    void testContainsAllFalse() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('A'));
        tiles.add(new Tile('B'));
        tiles.add(new Tile('C'));
        tiles.add(new Tile('D'));
        tiles.add(new Tile('E'));
        tiles.add(new Tile('F'));
        tiles.add(new Tile('A'));
        rack.setTiles(tiles);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile('A'));
        tiles2.add(new Tile('A'));
        tiles2.add(new Tile('A'));

        assertFalse(rack.containsAll(tiles2));
    }

    @Test
    void testRemoveAll() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('A'));
        tiles.add(new Tile('B'));
        tiles.add(new Tile('C'));
        tiles.add(new Tile('D'));
        tiles.add(new Tile('E'));
        tiles.add(new Tile('F'));
        tiles.add(new Tile('A'));
        rack.setTiles(tiles);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile('A'));
        tiles2.add(new Tile('E'));
        tiles2.add(new Tile('F'));
        rack.removeAll(tiles2);

        int expected = 4;
        assertEquals(expected, tiles.size());
    }
}
