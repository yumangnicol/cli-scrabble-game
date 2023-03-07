import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.*;
import pij.main.MoveValidator;
import java.util.ArrayList;

public class MoveValidatorTest {
    ScrabbleBoard board;
    WordList wordList;
    TileBag bag;
    BoardFileReader reader;
    TileRack rack;
    ArrayList<Tile> sampleRack;

    @BeforeEach
    void setUp() {
        wordList = new WordList("./src/resources/wordlist.txt");
        reader = new BoardFileReader();
        board = reader.toScrabbleBoard("./src/resources/defaultBoard.txt");
        bag = new TileBag();
        rack = new TileRack();

        sampleRack = new ArrayList<>();
        sampleRack.add(new Tile('J'));
        sampleRack.add(new Tile('A'));
        sampleRack.add(new Tile('V'));
        sampleRack.add(new Tile('A'));
        sampleRack.add(new Tile(' '));
        sampleRack.add(new Tile('T'));
        sampleRack.add(new Tile('P'));
        rack.setTiles(sampleRack);
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
        boolean result = MoveValidator.validateMove(move, rack, board, wordList, true);
        assertFalse(result);
    }

    @Test
    void moveOutOfBounds() {
        Move move = new Move("JAVA,h13,d");
        boolean result = MoveValidator.validateMove(move, rack, board, wordList, false);
        assertFalse(result);
    }

    @Test
    void moveNotCentered() {
        Move move = new Move("JAVA,g8,d");
        boolean result = MoveValidator.validateMove(move, rack, board, wordList, true);
        assertFalse(result);
    }

    @Test
    void moveNotConnected() {
        Move move = new Move("JAVA,h8,d");
        board.placeTiles(move);

        Move move2 = new Move("T,j9,r");
        boolean result = MoveValidator.validateMove(move2, rack, board, wordList, false);
        assertFalse(result);
    }

    @Test
    void moveInvalidWord() {
        Move move = new Move("JAA,h8,d");
        boolean result = MoveValidator.validateMove(move, rack, board, wordList, true);
        assertFalse(result);
    }

    @Test
    void moveCreatesTwoWords() {
        Move move = new Move("JAVA,h8,d");
        board.placeTiles(move);
        Move move2 = new Move("AR,i8,r");
        board.placeTiles(move2);

        Move move3 = new Move("T,i9,r");
        boolean result = MoveValidator.validateMove(move3, rack, board, wordList, false);
        assertFalse(result);
    }

    @Test
    void moveOnBottomEdge() {
        sampleRack = new ArrayList<>();
        sampleRack.add(new Tile('S'));
        sampleRack.add(new Tile(' '));
        sampleRack.add(new Tile(' '));
        rack.setTiles(sampleRack);

        Move move = new Move("KIN,k13,d");
        board.placeTiles(move);

        Move move2 = new Move("Soo,h15,r");
        boolean result = MoveValidator.validateMove(move2, rack, board, wordList, false);
        assertTrue(result);
    }

    @Test
    void moveOnTopEdge() {
        sampleRack = new ArrayList<>();
        sampleRack.add(new Tile('G'));
        sampleRack.add(new Tile('O'));
        rack.setTiles(sampleRack);

        Move move = new Move("LO,h1,r");
        board.placeTiles(move);

        Move move2 = new Move("GO,j1,r");
        boolean result = MoveValidator.validateMove(move2, rack, board, wordList, false);
        assertTrue(result);
    }

    @Test
    void moveOnLeftEdge() {
        sampleRack = new ArrayList<>();
        sampleRack.add(new Tile('L'));
        sampleRack.add(new Tile('O'));
        sampleRack.add(new Tile('K'));
        rack.setTiles(sampleRack);

        Move move = new Move("OLE,a3,r");
        board.placeTiles(move);

        Move move2 = new Move("LOK,a1,d");
        boolean result = MoveValidator.validateMove(move2, rack, board, wordList, false);
        board.placeTiles(move2);
        board.print();
        assertTrue(result);
    }

    @Test
    void moveOnRightEdge() {
        sampleRack = new ArrayList<>();
        sampleRack.add(new Tile('L'));
        sampleRack.add(new Tile('O'));
        sampleRack.add(new Tile('K'));
        rack.setTiles(sampleRack);

        Move move = new Move("BOO,m6,r");
        board.placeTiles(move);

        Move move2 = new Move("LOK,o4,d");
        boolean result = MoveValidator.validateMove(move2, rack, board, wordList, false);
        board.placeTiles(move2);
        board.print();
        assertTrue(result);
    }
}
