package pij.main;

import java.io.*;
import java.util.HashSet;

public class WordList {
    private HashSet<String> wordList;

    public WordList() {
        final String WORDLIST_FILE = "./resources/wordlist.txt";
        wordList = new HashSet<>();
        File file = new File(WORDLIST_FILE);
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((line = reader.readLine()) != null) {
                wordList.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contains(String word){
        return this.wordList.contains(word);
    }

}
