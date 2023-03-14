package pij.main;

import java.io.*;
import java.util.HashSet;

/**
 * Represents the dictionary of valid words to play in a scrabble game
 * @author Nicol Luis Yumang
 */
public class WordList {

    /**
     * The list of valid words to play
     */
    private final HashSet<String> wordList;

    /**
     * Constructor: List of valid words
     * @param fileName the path to file
     */
    public WordList(String fileName) {
        wordList = new HashSet<>();
        File file = new File(fileName);
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Wordlist file not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if the word is found in the list
     * @param word the word to check in word list
     * @return true if found and false if not
     */
    public  boolean contains(String word){
        return this.wordList.contains(word);
    }
}

