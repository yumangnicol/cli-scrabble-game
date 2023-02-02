package pij.main;

import java.util.ArrayList;
import java.util.Collections;

public class TileBag {
    private ArrayList<Tile> tiles;

    public TileBag(){
        final int ASCII_LETTER_A = 65, ASCII_LETTER_Z = 90;
        this.tiles = new ArrayList<>();
        for(int i = ASCII_LETTER_A; i <= ASCII_LETTER_Z; i++){
            for(int j = 0; j < LetterUtils.getLetterCount((char) i); j++){
                tiles.add((new Tile((char) i)));
            }
        }
        for(int i = 0; i < LetterUtils.getLetterCount(' '); i++){
            tiles.add(new Tile(' '));
        }
        this.shuffle();
    }
    public void shuffle() {
        Collections.shuffle(this.tiles);
    }
    public int size() {
        return this.tiles.size();
    }
    public void print() {
        for(Tile t : tiles){
            System.out.println("" + t.getLetter() + " " + t.getValue());
        }
    }
    public ArrayList<Tile> drawTiles(int count){
        ArrayList<Tile> drawnLetters = new ArrayList<>();

        // Returns empty if bag is empty
        if(this.tiles.size() == 0){
            return drawnLetters;
        }

        if(count > this.tiles.size()){
            for (int i = 0; i < this.tiles.size(); i++) {
                int rando = (int) (Math.random() * (this.size()-1)); // Generates random number from 0 to Length
                drawnLetters.add(this.tiles.remove(rando));
            }
        }

        for(int i = 0; i < count; i++){
            int rando = (int) (Math.random() * (this.size()-1)); // Generates random number from 0 to Length
            drawnLetters.add(this.tiles.remove(rando));
        }

        return drawnLetters;
    }
}
