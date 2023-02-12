package pij.main;

import java.io.*;
import java.util.HashSet;

public class WordList {
    private final HashSet<String> wordList;

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

    public  boolean contains(String word){
        return this.wordList.contains(word);
    }
}

