package pij.main;

import java.util.ArrayList;

public class LetterRack {
    private ArrayList<Letter> rack;
    final private int MAX_RACK_SIZE = 7;

    public LetterRack(){
        this.rack = new ArrayList<>();
    }

    public boolean refill(LetterBag letterBag, int count){
        if(count == 0 || count > this.getRackSpace()) {
            return false;
        }
        this.rack.addAll(letterBag.drawLetters(count));
        return true;
    }

    private int getRackSpace() {
        return this.MAX_RACK_SIZE - this.rack.size();
    }
}
