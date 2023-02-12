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

    @Test
    void moveOutOfBounds() {
        Move move = new Move("JAVA,h13,d");
        boolean result = MoveValidator.validateMove(move, rack, board, false);
        assertFalse(result);
    }

    @Test
    void moveNotCentered() {
        Move move = new Move("JAVA,g8,d");
        boolean result = MoveValidator.validateMove(move, rack, board, true);
        assertFalse(result);
    }

    @Test
    void moveNotConnected() {
        Move move = new Move("JAVA,h8,d");
        board.placeTiles(move);

        Move move2 = new Move("T,j9,r");
        boolean result = MoveValidator.validateMove(move2, rack, board, false);
        assertFalse(result);
    }

    @Test
    void moveInvalidWord() {
        Move move = new Move("JAA,h8,d");
        boolean result = MoveValidator.validateMove(move, rack, board, true);
        assertFalse(result);
    }
}
