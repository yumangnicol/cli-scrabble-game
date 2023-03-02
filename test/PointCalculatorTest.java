import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.BoardFileReader;
import pij.main.Move;
import pij.main.ScrabbleBoard;
import pij.main.utils.PointCalculator;

import static org.junit.jupiter.api.Assertions.*;

public class PointCalculatorTest {
    BoardFileReader reader;
    ScrabbleBoard board;
    
    @BeforeEach
    void setUp() {
        reader = new BoardFileReader();
        board = reader.toScrabbleBoard("./src/resources/defaultBoard.txt");
    }

    @AfterEach
    void tearDown() {
        reader = null;
        board = null;
    }

    @Test
    void calculatePointsWordPremium() {
        Move move = new Move("GIT,g8,r");
        assertEquals(8, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void calculatePointsLetterPremium() {
        Move move = new Move("FOO,i7,d");
        assertEquals(11, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void calculatePointsLetterAndWordPremium(){
        Move move = new Move("HELLO,h8,r");
        assertEquals(18, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void calculatePoints70bonus(){
        Move move = new Move("FUZZBOX,d8,r");
        assertEquals(152, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void calculatePointsOnBottomEdge() {
        Move move = new Move("KIN,k13,d");
        board.placeTiles(move);

        Move move2 = new Move("Soo,h15,r");
        assertEquals(24, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void calculatePointsOnTopEdge() {
        Move move = new Move("LO,h1,r");
        board.placeTiles(move);

        Move move2 = new Move("GO,j1,r");
        assertEquals(5, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void calculatePointsOnOnLeftEdge() {
        Move move = new Move("OLE,a3,r");
        board.placeTiles(move);

        Move move2 = new Move("LOK,a1,d");
        assertEquals(39, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void calculatePointsOnRightEdge() {
        Move move = new Move("BOO,m6,r");
        board.placeTiles(move);

        Move move2 = new Move("LOK,o4,d");
        assertEquals(9, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void calculatePointsNegativeWordPremium() {
        board = reader.toScrabbleBoard("./test/resources/negativePremium.txt");
        Move move = new Move("GIT,g8,r");
        assertEquals(-8, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void calculatePointsNegativeLetterPremium() {
        board = reader.toScrabbleBoard("./test/resources/negativePremium.txt");
        Move move = new Move("FOO,i7,d");
        assertEquals(-9, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void calculatePointsNegativeLetterAndWordPremium(){
        board = reader.toScrabbleBoard("./test/resources/negativePremium.txt");
        Move move = new Move("HELLO,h8,r");
        assertEquals(-10, PointCalculator.calculatePoints(move, board));
    }
}
