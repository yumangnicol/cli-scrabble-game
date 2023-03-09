import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.*;
import pij.main.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveGeneratorTest {
    ScrabbleBoard board;
    WordList wordList;
    TileBag bag;
    BoardFileReader reader;
    Player computer;

    @BeforeEach
    void setUp() {
        wordList = new WordList("./src/resources/wordlist.txt");
        reader = new BoardFileReader();
        board = reader.toScrabbleBoard("./src/resources/defaultBoard.txt");
        bag = new TileBag();
        computer = new Player();
    }

    @Test
    void testGenerateDownMove() {
        Move setup = new Move("JAVA,g1,r");
        board.placeTiles(setup);

        computer.getRack().refill(bag, 7);
        Move move = MoveGenerator.generateSucceedingMove(this.computer.getRack(), board, wordList);
        boolean result = MoveValidator.validateMove(move, computer.getRack(), board, wordList, false);
        assertTrue(result);
    }

    @Test
    void testGenerateRightMove() {
        Move setup = new Move("JAVA,a8,d");
        board.placeTiles(setup);

        computer.getRack().refill(bag, 7);
        Move move = MoveGenerator.generateSucceedingMove(this.computer.getRack(), board, wordList);
        boolean result = MoveValidator.validateMove(move, computer.getRack(), board, wordList, false);
        assertTrue(result);
    }

    @Test
    void testGenerateFirstMove() {
        computer.getRack().refill(bag, 7);
        Move move = MoveGenerator.generateFirstMove(computer.getRack(), board, wordList);
        boolean result = MoveValidator.validateMove(move, computer.getRack(), board, wordList, true);
        assertTrue(result);
    }

    @Test
    void testGenerateFirstMoveWithWildCard() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile('D'));
        tiles.add(new Tile(' '));
        computer.getRack().setTiles(tiles);

        Move move = MoveGenerator.generateFirstMove(computer.getRack(), board, wordList);
        boolean result = MoveValidator.validateMove(move, computer.getRack(), board, wordList, true);
        assertTrue(result);
    }
}
