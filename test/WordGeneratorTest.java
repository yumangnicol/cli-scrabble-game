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
        tiles.add(new Tile('J'));
        tiles.add(new Tile('A'));
        tiles.add(new Tile('V'));
        tiles.add(new Tile('A'));
        tiles.add(new Tile(' '));
        tiles.add(new Tile('T'));
        tiles.add(new Tile('P'));
    }

    @AfterEach
    void tearDown() {
        tiles = null;
        wordList = null;
    }

    @Test
    void sampleTest() {
        var list = WordGenerator.generateWords(tiles, 4, "J", wordList);
        int result = 4;
        assertEquals(result, list.size());
    }
}
