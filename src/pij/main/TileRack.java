package pij.main;

import java.util.ArrayList;

public class TileRack {
    private ArrayList<Tile> rack;

    public TileRack(){
        this.rack = new ArrayList<>();
    }

    public boolean refill(TileBag tileBag, int count){
        if(count == 0 || count > this.getRackSpace()) {
            return false;
        }
        this.rack.addAll(tileBag.drawLetters(count));
        return true;
    }

    public boolean contains(char letter){

        for(Tile t : this.rack){
            if(t.getLetter() == letter){
                return true;
            }
        }

        return false;
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
        for (Tile t : rack) {
            sb.append(prefix);
            prefix = ",";
            sb.append('[');
            sb.append(t.getLetter());
            sb.append((t.getValue()));
            sb.append(']');
        }
        System.out.println(sb);
    }
}
