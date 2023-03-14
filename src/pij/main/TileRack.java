package pij.main;

import pij.main.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the rack of tiles in a scrabble game
 * @author Nicol Luis Yumang
 */
public class TileRack {
    /**
     * The list of tiles in the rack
     */
    private List<Tile> tiles;

    /**
     * Constructor: Creates a new empty rack
     */
    public TileRack(){
        this.tiles = new ArrayList<>();
    }

    /**
     * Checks if given tiles are present in the rack
     * @param tiles the list of tiles to check if in rack
     * @return true if all tiles are present, false if otherwise
     */
    public boolean containsAll(ArrayList<Tile> tiles){
        List<Tile> modifiedTiles = replaceLowerCaseWithWildCard(tiles);
        for (Tile tile : modifiedTiles) {
            // Check if rack has the same amount each tile
            if (Collections.frequency(this.tiles, tile) < Collections.frequency(modifiedTiles, tile)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the available space in the rack.
     * The rack can contain a max of 7 tiles
     * @return available space
     */
    private int getRackSpace() {
        return Constants.MAX_RACK_SIZE - this.tiles.size();
    }

    /**
     * Gets the list of tiles in the rack
     * @return list of tiles
     */
    public List<Tile> getTiles() {
        return this.tiles;
    }

    /**
     * Prints a prettified list of tiles in the rack
     */
    public void print(){
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (Tile t : this.tiles) {
            sb.append(prefix);
            prefix = ",";
            sb.append('[');
            sb.append(t.getLetter());
            sb.append((t.getValue()));
            sb.append(']');
        }
        System.out.println(sb);
    }

    /**
     * Replenishes the rack with new tiles from a tile bag
     * @param tileBag the tile bag to draw new tiles
     * @param count the number of tiles to draw
     */
    public void refill(TileBag tileBag, int count){
        if(count > this.getRackSpace()) {
            System.out.println("Cannot refill beyond 7 tiles");
            return;
        }

        ArrayList<Tile> drawn = tileBag.drawTiles(count);
        if(drawn.isEmpty()){
            return;
        }

        this.tiles.addAll(drawn);
    }

    /**
     * Removes tiles from rack
     * @param tiles the list of tiles to remove
     */
    public void removeAll(List<Tile> tiles){
        List<Tile> modifiedTiles = replaceLowerCaseWithWildCard(tiles);

        for (Tile tile : modifiedTiles) {

            // Check if rack has the same amount each tile in both lists
            int countToRemove = Collections.frequency(modifiedTiles, tile);
            int countOnRack = Collections.frequency(this.tiles, tile);

            for (int i = 0; i < countToRemove && i < countOnRack; i++) {
                this.tiles.remove(tile);
            }
        }
    }

    /**
     * Modifies a list of tiles.
     * Lowercase letter tiles are modified as wild cards ' '
     * @param tiles the list of tiles to modify
     * @return a list of tiles that contain wild cards
     */
    private List<Tile> replaceLowerCaseWithWildCard(List<Tile> tiles){
        ArrayList<Tile> modifiedTiles = new ArrayList<>();
        for (Tile tile : tiles) {
            if (Character.isLowerCase(tile.getLetter())) {
                modifiedTiles.add(new Tile(' '));
            } else {
                modifiedTiles.add(tile);
            }
        }
        return modifiedTiles;
    }

    /**
     * Sets tiles in the rack
     * @param tiles the list of tiles to set
     */
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    /**
     * Gets the current number of tiles in the rack
     * @return number of tiles in the rack
     */
    public int size() {
        return this.tiles.size();
    }
}
