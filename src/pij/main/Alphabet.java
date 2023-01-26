package pij.main;

import java.util.AbstractMap;
import java.util.Map;

public class Alphabet {
    private static Map<Character, Integer> letterValue = Map.ofEntries(
            new AbstractMap.SimpleEntry<Character, Integer>('A', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('B', 3),
            new AbstractMap.SimpleEntry<Character, Integer>('C', 3),
            new AbstractMap.SimpleEntry<Character, Integer>('D', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('E', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('F', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('G', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('H', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('I', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('J', 8),
            new AbstractMap.SimpleEntry<Character, Integer>('K', 5),
            new AbstractMap.SimpleEntry<Character, Integer>('L', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('M', 3),
            new AbstractMap.SimpleEntry<Character, Integer>('N', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('O', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('P', 3),
            new AbstractMap.SimpleEntry<Character, Integer>('Q', 10),
            new AbstractMap.SimpleEntry<Character, Integer>('R', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('S', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('T', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('U', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('V', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('W', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('X', 8),
            new AbstractMap.SimpleEntry<Character, Integer>('Y', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('Z', 10),
            new AbstractMap.SimpleEntry<Character, Integer>(' ', 3)
    );

    private static Map<Character, Integer> letterCount= Map.ofEntries(
            new AbstractMap.SimpleEntry<Character, Integer>('A', 9),
            new AbstractMap.SimpleEntry<Character, Integer>('B', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('C', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('D', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('E', 12),
            new AbstractMap.SimpleEntry<Character, Integer>('F', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('G', 3),
            new AbstractMap.SimpleEntry<Character, Integer>('H', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('I', 9),
            new AbstractMap.SimpleEntry<Character, Integer>('J', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('K', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('L', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('M', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('N', 6),
            new AbstractMap.SimpleEntry<Character, Integer>('O', 8),
            new AbstractMap.SimpleEntry<Character, Integer>('P', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('Q', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('R', 6),
            new AbstractMap.SimpleEntry<Character, Integer>('S', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('T', 6),
            new AbstractMap.SimpleEntry<Character, Integer>('U', 4),
            new AbstractMap.SimpleEntry<Character, Integer>('V', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('W', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('X', 1),
            new AbstractMap.SimpleEntry<Character, Integer>('Y', 2),
            new AbstractMap.SimpleEntry<Character, Integer>('Z', 1),
            new AbstractMap.SimpleEntry<Character, Integer>(' ', 2)
    );

    public static Map<Character, Integer> getLetterValue() {
        return letterValue;
    }

    public static Map<Character, Integer> getLetterCount() {
        return letterCount;
    }
}
