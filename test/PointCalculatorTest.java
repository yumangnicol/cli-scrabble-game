import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.BoardFileReader;
import pij.main.Move;
import pij.main.ScrabbleBoard;
import pij.main.PointCalculator;

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
    void testCalculatePointsWordPremium() {
        Move move = new Move("GIT,g8,r");
        int expected = 8;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void testCalculatePointsLetterPremium() {
        Move move = new Move("FOO,i7,d");
        int expected = 11;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void testCalculatePointsLetterAndWordPremium(){
        Move move = new Move("HELLO,h8,r");
        int expected = 18;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void testCalculatePoints70bonus(){
        Move move = new Move("FUZZBOX,d8,r");
        int expected = 152;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void testCalculatePointsOnBottomEdge() {
        Move move = new Move("KIN,k13,d");
        board.placeTiles(move);

        Move move2 = new Move("Soo,h15,r");
        int expected = 24;
        assertEquals(expected, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void testCalculatePointsOnTopEdge() {
        Move move = new Move("LO,h1,r");
        board.placeTiles(move);

        Move move2 = new Move("GO,j1,r");
        int expected = 5;
        assertEquals(expected, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void testCalculatePointsOnOnLeftEdge() {
        Move move = new Move("OLE,a3,r");
        board.placeTiles(move);

        Move move2 = new Move("LOK,a1,d");
        int expected = 39;
        assertEquals(expected, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void testCalculatePointsOnRightEdge() {
        Move move = new Move("BOO,m6,r");
        board.placeTiles(move);

        Move move2 = new Move("LOK,o4,d");
        int expected = 9;
        assertEquals(expected, PointCalculator.calculatePoints(move2, board));
    }

    @Test
    void testCalculatePointsNegativeWordPremium() {
        board = reader.toScrabbleBoard("./test/resources/negativePremium.txt");
        Move move = new Move("GIT,g8,r");
        int expected = -8;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void testCalculatePointsNegativeLetterPremium() {
        board = reader.toScrabbleBoard("./test/resources/negativePremium.txt");
        Move move = new Move("FOO,i7,d");
        int expected = -9;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }

    @Test
    void testCalculatePointsNegativeLetterAndWordPremium(){
        board = reader.toScrabbleBoard("./test/resources/negativePremium.txt");
        Move move = new Move("HELLO,h8,r");
        int expected = -10;
        assertEquals(expected, PointCalculator.calculatePoints(move, board));
    }
}
