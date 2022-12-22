package pij.main;

import pij.alphabet.Alphabet;

import java.util.ArrayList;
import java.util.Collections;

public class LetterBag {
    private ArrayList<Letter> letters;

    public LetterBag(){
        final int ASCII_LETTER_A = 65, ASCII_LETTER_Z = 90;
        letters = new ArrayList<>();
        for(int i = ASCII_LETTER_A; i <= ASCII_LETTER_Z; i++){
            for(int j = 0; j < Alphabet.getLetterCount().get((char) i); j++){
                letters.add(new Letter((char) i));
            }
        }
        for(int i = 0; i < Alphabet.getLetterCount().get(' '); i++){
            letters.add(new Letter(' '));
        }
    }

    public void shuffle() {
        Collections.shuffle(this.letters);
    }
    public int size() {
        return this.letters.size();
    }

    public void print() {
        for(Letter l : letters){
            System.out.println(l.getLetter() + " " + l.getValue());
        }
    }

    public ArrayList<Letter> drawLetters(int count){
        ArrayList<Letter> drawnLetters = new ArrayList<>();

        for(int i = 0; i < count; i++){
            int rando = (int) (Math.random() * (this.letters.size()-1)); // Generates random number from 0 to Length
            drawnLetters.add(this.letters.remove(rando));
        }

        return drawnLetters;
    }


}
