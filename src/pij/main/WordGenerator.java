package pij.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A generator that produces valid words to play
 * @author Nicol Luis Yumang
 */
public class WordGenerator {

    /**
     * Generates a list of valid words to play
     * @param tiles list of tiles to use
     * @param maxLength words can be generated up to this max length
     * @param start starting letter of the word, can be empty
     * @param wordList list to check if generated word is valid
     * @return list of generated words
     */
    public static List<String> generateWords(List<Tile> tiles, int maxLength, String start, WordList wordList) {
        ArrayList<String> words = new ArrayList<>();
        maxLength = Math.min(maxLength, 8);
        HashMap<Tile, Integer> usedTiles = new HashMap<>();
        generateWordsHelper(start, tiles, maxLength, wordList, words, usedTiles);
        return words;
    }

    /**
     * Recursive method that builds all possible and valid words
     * from the given parameters
     * @param currentWord current state of the word to be built
     * @param tiles list of tiles to use
     * @param maxLength words can be generated up to this max length
     * @param wordList list to check if generated word is valid
     * @param words list of words generated
     * @param usedTiles map of tiles already used in the currentWord
     */
    private static void generateWordsHelper(String currentWord, List<Tile> tiles, int maxLength, WordList wordList, ArrayList<String> words, HashMap<Tile, Integer> usedTiles) {
        if (currentWord.length() > maxLength) {
            return;
        }
        if (wordList.contains(currentWord.toLowerCase()) && !words.contains(currentWord) && !currentWord.isEmpty()) {
            words.add(currentWord);
        }
        for (Tile tile : tiles) {
            Integer count = usedTiles.get(tile);
            // Check if tile can be used
            if (count == null || count < getCount(tile, tiles)) {
                // Add tile to used
                usedTiles.put(tile, count == null ? 1 : count + 1);
                char newLetter = tile.getLetter();
                if (newLetter == ' ') {
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Tries to add every letter from a - z if tile is wild card
                        generateWordsHelper(currentWord + c, tiles, maxLength, wordList, words, usedTiles);
                    }
                } else {
                    generateWordsHelper(currentWord + newLetter, tiles, maxLength, wordList, words, usedTiles);
                }
                // Remove tile from used after recursive call
                usedTiles.put(tile, count);
            }
        }
    }

    /**
     * Returns the count of a tile in the list
     * @param tile tile to be counted
     * @param tiles list of tiles
     * @return frequency of tile in the list
     */
    private static int getCount(Tile tile, List<Tile> tiles){
        int count = 0;
        for (Tile t : tiles) {
            if (t.equals(tile)){
                count++;
            }
        }
        return count;
    }
}
