package pij.main;

import java.util.ArrayList;
import java.util.Collections;

public class LetterBag {
    private ArrayList<Character> letters;

    public LetterBag(){
        final int ASCII_LETTER_A = 65, ASCII_LETTER_Z = 90;
        this.letters = new ArrayList<>();
        for(int i = ASCII_LETTER_A; i <= ASCII_LETTER_Z; i++){
            for(int j = 0; j < LetterUtils.getLetterCount((char) i); j++){
                letters.add((char) i);
            }
        }
        for(int i = 0; i < LetterUtils.getLetterCount(' '); i++){
            letters.add(' ');
        }
//        this.shuffle();
    }
    public void shuffle() {
        Collections.shuffle(this.letters);
    }
    public int size() {
        return this.letters.size();
    }
    public void print() {
        for(char l : letters){
            System.out.println("" + l + " " + LetterUtils.getLetterValue(l));
        }
    }

    public ArrayList<Character> drawLetters(int count){
        ArrayList<Character> drawnLetters = new ArrayList<>();

        // Returns empty if bag is empty
        if(this.letters.size() == 0){
            return drawnLetters;
        }

        // Create logic here if count > letters.count

        for(int i = 0; i < count; i++){
            int rando = (int) (Math.random() * (this.size()-1)); // Generates random number from 0 to Length
            drawnLetters.add(this.letters.remove(rando));
        }

        return drawnLetters;
    }
}
