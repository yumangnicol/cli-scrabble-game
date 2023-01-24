package pij.main;

import java.io.*;
import java.util.HashSet;

public final class WordList {
    private static WordList INSTANCE;
    private final HashSet<String> wordList;

    private WordList() {
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

    public static WordList getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WordList();
        }
        return INSTANCE;
    }
}
