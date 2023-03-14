package pij.main;

import pij.main.utils.Constants;
import pij.main.utils.TileSettings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the tile bag in a scrabble game
 * @author Nicol Luis Yumang
 */
public class TileBag {

    /**
     * The list of tiles inside the tile bag
     */
    private final List<Tile> tiles;

    /**
     * Constructor: Creates a new tile bag filled with tiles
     * The number of starting tiles in the bag are determined in the TileSettings
     */
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

    /**
     * Draws random tiles from the tile bag
     * @param count the amount of tiles to draw
     * @return list of tiles drawn
     */
    public ArrayList<Tile> drawTiles(int count){
        ArrayList<Tile> drawnLetters = new ArrayList<>();

        // Returns empty if bag is empty
        if(this.tiles.size() == 0){
            System.out.println("No more tiles in Tile bag!");
            return drawnLetters;
        }

        // Returns remaining tiles in bag if count is greater than bag size
        if(count > this.tiles.size()){
            int toDraw = this.size();
            for (int i = 0; i < toDraw; i++) {
                drawnLetters.add(this.tiles.remove(0));
            }
            return drawnLetters;
        }

        for(int i = 0; i < count; i++){
            // Generates random number from 0 to Length
            int rando = (int) (Math.random() * (this.size()-1));
            drawnLetters.add(this.tiles.remove(rando));
        }

        return drawnLetters;
    }

    /**
     * Shuffles the tile bag order
     */
    public void shuffle() {
        Collections.shuffle(this.tiles);
    }

    /**
     * Gets the size of the bag
     * @return number of tiles in the bag
     */
    public int size() {
        return this.tiles.size();
    }

}
