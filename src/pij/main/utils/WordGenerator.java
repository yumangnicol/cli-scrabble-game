package pij.main.utils;

import pij.main.Tile;
import pij.main.WordList;

import java.util.ArrayList;

public class WordGenerator {
    public static ArrayList<String> generateWords(ArrayList<Tile> tiles, int maxLength, String start, WordList wordList) {
        ArrayList<String> words = new ArrayList<>();
        maxLength = Math.min(maxLength, 8);
        for (int length = 1; length <= maxLength; length++) {
            generateWordsHelper(start, tiles, length, wordList, words);
        }
        return words;
    }

    private static void generateWordsHelper(String currentWord, ArrayList<Tile> tiles, int maxLength, WordList wordList, ArrayList<String> words) {
        if (currentWord.length() > maxLength) {
            return;
        }
        if (wordList.contains(currentWord.toLowerCase()) && !words.contains(currentWord)) {
            if(!currentWord.equals("")){
                words.add(currentWord);
            }
        }
        for (Tile tile : tiles) {
            generateWordsHelper(currentWord + tile.getLetter(), tiles, maxLength, wordList, words);
        }
    }
}
