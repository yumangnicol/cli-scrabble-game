package pij.main;

import java.util.ArrayList;

public class LetterRack {
    private ArrayList<Character> rack;

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

    public boolean contains(char letter){
        return this.rack.contains(letter);
    }

    public boolean containsAll(char[] letters){
        for (char c : letters){
            if(!this.contains(c)){
                return false;
            }
        }
        return true;
    }

    private int getRackSpace() {
        final int MAX_RACK_SIZE = 7;
        return MAX_RACK_SIZE - this.rack.size();
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (char l : rack) {
            sb.append(prefix);
            prefix = ",";
            sb.append('[');
            sb.append(l);
            sb.append((LetterUtils.getLetterValue(l)));
            sb.append(']');
        }
        System.out.println(sb);
    }
}
