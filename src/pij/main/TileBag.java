package pij.main;

import pij.main.utils.Constants;
import pij.main.utils.TileSettings;

import java.util.ArrayList;
import java.util.Collections;

public class TileBag {
    private final ArrayList<Tile> tiles;
    public TileBag(){
        this.tiles = new ArrayList<>();
        for(int i = Constants.CHAR_CAPITAL_LETTER_A_INT_VALUE; i <= Constants.CHAR_CAPITAL_LETTER_Z_INT_VALUE; i++){
            for(int j = 0; j < TileSettings.getLetterCount((char) i); j++){
                tiles.add((new Tile((char) i)));
            }
        }
        for(int i = 0; i < TileSettings.getLetterCount(' '); i++){
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
