package pij.main;

import java.util.ArrayList;

public class LetterRack {
    ArrayList<Letter> rack;
    final private int MAX_RACK_SIZE = 7;

    public LetterRack(LetterBag letterBag){
        this.rack = letterBag.drawLetters(this.MAX_RACK_SIZE);
    }

    public void refill(LetterBag letterBag, int count){
        this.rack.addAll(letterBag.drawLetters(count));
    }
}
