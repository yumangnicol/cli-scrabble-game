import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pij.main.Tile;
import pij.main.WordList;
import pij.main.WordGenerator;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class WordGeneratorTest {
    ArrayList<Tile> tiles;
    WordList wordList;

    @BeforeEach
    void setUp() {
        wordList = new WordList("./src/resources/wordlist.txt");
        tiles = new ArrayList<>();
        tiles.add(new Tile('N'));
        tiles.add(new Tile('A'));
        tiles.add(new Tile('I'));
        tiles.add(new Tile('S'));
        tiles.add(new Tile('U'));
        tiles.add(new Tile('O'));
        tiles.add(new Tile('I'));
    }

    @AfterEach
    void tearDown() {
        tiles = null;
        wordList = null;
    }

    @Test
    void testGenerateWordsGivenStart() {
        var list = WordGenerator.generateWords(tiles, 7, "Z", wordList);
        int expected = 8;
        assertEquals(expected, list.size());
    }

    @Test
    void testGenerateWordsBlankStart() {
        var list = WordGenerator.generateWords(tiles, 7, "", wordList);
        int expected = 55;
        assertEquals(expected, list.size());
    }

    @Test
    void testGenerateWordsWithWildCard() {
        tiles = new ArrayList<>();
        tiles.add(new Tile('D'));
        tiles.add(new Tile('A'));
        tiles.add(new Tile('B'));
        tiles.add(new Tile('R'));
        tiles.add(new Tile(' '));
        tiles.add(new Tile('Z'));
        tiles.add(new Tile('E'));

        var list = WordGenerator.generateWords(tiles, 7, "", wordList);
        int expected = 1247;
        assertEquals(expected, list.size());
    }
}
