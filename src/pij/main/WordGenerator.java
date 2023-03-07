package pij.main;

import java.util.ArrayList;
import java.util.HashSet;

public class WordGenerator {
    public static ArrayList<String> generateWords(ArrayList<Tile> tiles, int maxLength, String start, WordList wordList) {
        ArrayList<String> words = new ArrayList<>();
        maxLength = Math.min(maxLength, 8);
        HashSet<Tile> usedTiles = new HashSet<>();
        if (start.isEmpty()) {
            for (Tile tile : tiles) {
                generateWordsHelper(String.valueOf(tile.getLetter()), tiles, maxLength, wordList, words, usedTiles);
            }
        } else {
            generateWordsHelper(start, tiles, maxLength, wordList, words, usedTiles);
        }
        return words;
    }

    private static void generateWordsHelper(String currentWord, ArrayList<Tile> tiles, int maxLength, WordList wordList, ArrayList<String> words, HashSet<Tile> usedTiles) {
        if (currentWord.length() > maxLength) {
            return;
        }
        if (wordList.contains(currentWord.toLowerCase()) && !words.contains(currentWord)) {
            words.add(currentWord);
        }
        for (Tile tile : tiles) {
            if (!usedTiles.contains(tile)) {
                usedTiles.add(tile);
                generateWordsHelper(currentWord + tile.getLetter(), tiles, maxLength, wordList, words, usedTiles);
                usedTiles.remove(tile);
            }
        }
    }

}
