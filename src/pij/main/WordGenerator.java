package pij.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordGenerator {
    public static List<String> generateWords(ArrayList<Tile> tiles, int maxLength, String start, WordList wordList) {
        ArrayList<String> words = new ArrayList<>();
        maxLength = Math.min(maxLength, 8);
        HashMap<Tile, Integer> usedTiles = new HashMap<>();
        generateWordsHelper(start, tiles, maxLength, wordList, words, usedTiles);
        return words;
    }

    private static void generateWordsHelper(String currentWord, ArrayList<Tile> tiles, int maxLength, WordList wordList, ArrayList<String> words, HashMap<Tile, Integer> usedTiles) {
        if (currentWord.length() > maxLength) {
            return;
        }
        if (wordList.contains(currentWord.toLowerCase()) && !words.contains(currentWord) && !currentWord.isEmpty()) {
            words.add(currentWord);
        }
        for (Tile tile : tiles) {
            Integer count = usedTiles.get(tile);
            if (count == null || count < getCount(tile, tiles)) { // Check if we can use this tile
                usedTiles.put(tile, count == null ? 1 : count + 1); // Add the tile to the usedTiles map
                char newLetter = tile.getLetter();
                if (newLetter == ' ') {
                    for (char c = 'a'; c <= 'z'; c++) {
                        generateWordsHelper(currentWord + c, tiles, maxLength, wordList, words, usedTiles);
                    }
                } else {
                    generateWordsHelper(currentWord + newLetter, tiles, maxLength, wordList, words, usedTiles);
                }
                usedTiles.put(tile, count);
            }
        }
    }

    private static int getCount(Tile tile, ArrayList<Tile> tiles){
        int count = 0;
        for (Tile t : tiles) {
            if (t.equals(tile)){
                count++;
            }
        }
        return count;
    }
}
