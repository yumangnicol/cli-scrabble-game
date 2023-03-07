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
    void sampleTest() {
        var list = WordGenerator.generateWords(tiles, 7, "", wordList);
        System.out.println(list);
        int result = 4;
        assertEquals(result, list.size());
    }
}
