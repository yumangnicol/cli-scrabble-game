package pij.main.utils;

import pij.main.Tile;
import pij.main.WordList;

import java.util.ArrayList;

public class WordGenerator {
    public static void generateWords(ArrayList<Tile> tiles, int length, String start, WordList wordList) {
        generateWordsHelper(start, tiles, length, wordList);
    }

    // Limitation: Does not utilize wildcards
    private static void generateWordsHelper(String currentWord, ArrayList<Tile> tiles, int length, WordList wordlist) {
        if (currentWord.length() == length) {
            if(wordlist.contains(currentWord.toLowerCase())){
                System.out.println(currentWord);
            }
            return;
        }
        for (Tile tile : tiles) {
            generateWordsHelper(currentWord + tile.getLetter(), tiles, length, wordlist);
        }
    }
}
