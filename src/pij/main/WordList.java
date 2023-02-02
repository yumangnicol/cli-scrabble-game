package pij.main;

import java.io.*;
import java.util.HashSet;

public final class WordList {
    private static final HashSet<String> wordList;
    private static final String WORDLIST_FILE = "./resources/wordlist.txt";

    static {
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

    public static boolean contains(String word){
        return wordList.contains(word);
    }
}

