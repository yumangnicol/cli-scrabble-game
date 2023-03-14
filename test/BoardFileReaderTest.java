import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.BoardFileReader;
import pij.main.ScrabbleBoard;

import static org.junit.jupiter.api.Assertions.*;

public class BoardFileReaderTest {
    BoardFileReader reader;

    @BeforeEach
    void setUp(){
        reader  = new BoardFileReader();
    }

    @Test
    public void testFileNotFound() {
        String filename = "board.txt";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.toScrabbleBoard(filename));

        String expectedMessage = "Board file not found!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testInvalidBoardSize() {
        String filename = "./test/resources/invalidBoardSize.txt";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.toScrabbleBoard(filename));

        String expectedMessage = "File not accepted. First line of the file should be an integer from 12-26.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testContainsIllegalCharacters() {
        String filename = "./test/resources/illegalCharacter.txt";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.toScrabbleBoard(filename));

        String expectedMessage = "File not accepted. File contains illegal characters. ";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testContainsIllegalPremiumFormat() {
        String filename = "./test/resources/illegalPremiumFormat.txt";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.toScrabbleBoard(filename));

        String expectedMessage = "File not accepted. File contains illegal premium format. ";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testContainsIllegalFormat() {
        String filename = "./test/resources/illegalFormat.txt";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.toScrabbleBoard(filename));

        String expectedMessage = "File not accepted. Board size and items do not match! ";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testEvenBoard() {
        String filename = "./test/resources/evenBoard.txt";
        ScrabbleBoard board = reader.toScrabbleBoard(filename);

        int expected = 16;
        assertEquals(expected, board.length());
    }

    @Test
    public void testOddBoard() {
        String filename = "./test/resources/oddBoard.txt";
        ScrabbleBoard board = reader.toScrabbleBoard(filename);

        int expected = 15;
        assertEquals(expected, board.length());
    }

    @Test
    public void testNegativePremiumBoard() {
        String filename = "./test/resources/negativePremium.txt";
        ScrabbleBoard board = reader.toScrabbleBoard(filename);

        int expected = 15;
        assertEquals(expected, board.length());
    }

}
