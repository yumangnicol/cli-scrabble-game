package pij.main.utils;

import java.util.AbstractMap;
import java.util.Map;

/**
 * A helper class that defines all the tiles in the scrabble game
 * including a tile's count and its numerical value
 */
public class TileSettings {

    /**
     * A map that defines the value of each tile
     */
    private static final Map<Character, Integer> letterValue = Map.ofEntries(
            new AbstractMap.SimpleEntry<>('A', 1),
            new AbstractMap.SimpleEntry<>('B', 3),
            new AbstractMap.SimpleEntry<>('C', 3),
            new AbstractMap.SimpleEntry<>('D', 2),
            new AbstractMap.SimpleEntry<>('E', 1),
            new AbstractMap.SimpleEntry<>('F', 4),
            new AbstractMap.SimpleEntry<>('G', 2),
            new AbstractMap.SimpleEntry<>('H', 4),
            new AbstractMap.SimpleEntry<>('I', 1),
            new AbstractMap.SimpleEntry<>('J', 8),
            new AbstractMap.SimpleEntry<>('K', 5),
            new AbstractMap.SimpleEntry<>('L', 1),
            new AbstractMap.SimpleEntry<>('M', 3),
            new AbstractMap.SimpleEntry<>('N', 1),
            new AbstractMap.SimpleEntry<>('O', 1),
            new AbstractMap.SimpleEntry<>('P', 3),
            new AbstractMap.SimpleEntry<>('Q', 10),
            new AbstractMap.SimpleEntry<>('R', 1),
            new AbstractMap.SimpleEntry<>('S', 1),
            new AbstractMap.SimpleEntry<>('T', 1),
            new AbstractMap.SimpleEntry<>('U', 1),
            new AbstractMap.SimpleEntry<>('V', 4),
            new AbstractMap.SimpleEntry<>('W', 4),
            new AbstractMap.SimpleEntry<>('X', 8),
            new AbstractMap.SimpleEntry<>('Y', 4),
            new AbstractMap.SimpleEntry<>('Z', 10),
            new AbstractMap.SimpleEntry<>(' ', 3)
    );

    /**
     * A map that defines frequency of each tile
     */
    private static final Map<Character, Integer> letterCount= Map.ofEntries(
            new AbstractMap.SimpleEntry<>('A', 9),
            new AbstractMap.SimpleEntry<>('B', 2),
            new AbstractMap.SimpleEntry<>('C', 2),
            new AbstractMap.SimpleEntry<>('D', 4),
            new AbstractMap.SimpleEntry<>('E', 12),
            new AbstractMap.SimpleEntry<>('F', 2),
            new AbstractMap.SimpleEntry<>('G', 3),
            new AbstractMap.SimpleEntry<>('H', 2),
            new AbstractMap.SimpleEntry<>('I', 9),
            new AbstractMap.SimpleEntry<>('J', 1),
            new AbstractMap.SimpleEntry<>('K', 1),
            new AbstractMap.SimpleEntry<>('L', 4),
            new AbstractMap.SimpleEntry<>('M', 2),
            new AbstractMap.SimpleEntry<>('N', 6),
            new AbstractMap.SimpleEntry<>('O', 8),
            new AbstractMap.SimpleEntry<>('P', 2),
            new AbstractMap.SimpleEntry<>('Q', 1),
            new AbstractMap.SimpleEntry<>('R', 6),
            new AbstractMap.SimpleEntry<>('S', 4),
            new AbstractMap.SimpleEntry<>('T', 6),
            new AbstractMap.SimpleEntry<>('U', 4),
            new AbstractMap.SimpleEntry<>('V', 2),
            new AbstractMap.SimpleEntry<>('W', 2),
            new AbstractMap.SimpleEntry<>('X', 1),
            new AbstractMap.SimpleEntry<>('Y', 2),
            new AbstractMap.SimpleEntry<>('Z', 1),
            new AbstractMap.SimpleEntry<>(' ', 2)
    );

    public static int getLetterValue(char letter) {
        return letterValue.get(letter);
    }

    public static int getLetterCount(char letter) {
        return letterCount.get(letter);
    }
}
