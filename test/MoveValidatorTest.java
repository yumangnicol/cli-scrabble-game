import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.*;

import java.util.ArrayList;

public class MoveValidatorTest {
    ScrabbleBoard board;
    TileBag bag;
    BoardFileReader reader;
    TileRack rack;
    ArrayList<Tile> sampleRack;

    @BeforeEach
    void setUp() {
        sampleRack = new ArrayList<>();
        reader = new BoardFileReader();
        board = reader.toScrabbleBoard("./src/resources/defaultBoard.txt");
        bag = new TileBag();
        rack = new TileRack();
        sampleRack.add(new Tile('J'));
        sampleRack.add(new Tile('A'));
        sampleRack.add(new Tile('V'));
        sampleRack.add(new Tile('A'));
        sampleRack.add(new Tile(' '));
        sampleRack.add(new Tile('T'));
        sampleRack.add(new Tile('P'));
        rack.setRack(sampleRack);
    }

    @AfterEach
    void tearDown() {
        sampleRack = null;
        reader = null;
        board = null;
        bag = null;
        rack = null;
    }

    @Test
    void rackIncompleteTiles() {
        Move move = new Move("JAVB,h8,d");
        boolean result = MoveValidator.validateMove(move, rack, board, true);
        assertFalse(result);
    }
}
