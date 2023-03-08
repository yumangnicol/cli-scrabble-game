package pij.main;

import pij.main.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;

public class TileRack {
    private ArrayList<Tile> tiles;

    public TileRack(){
        this.tiles = new ArrayList<>();
    }

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

    public void removeAll(ArrayList<Tile> tiles){
        for (Tile tile : tiles) {
            int countToRemove = Collections.frequency(tiles, tile);
            int countOnRack = Collections.frequency(this.tiles, tile);

            for (int i = 0; i < countToRemove && i < countOnRack; i++) {
                this.tiles.remove(tile);
            }
        }
    }

    public boolean containsAll(ArrayList<Tile> tiles){
        for (Tile tile : tiles) {
            if (Collections.frequency(this.tiles, tile) < Collections.frequency(tiles, tile)) {
                return false;
            }
        }
        return true;
    }

    private int getRackSpace() {
        return Constants.MAX_RACK_SIZE - this.tiles.size();
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public int size() {
        return tiles.size();
    }

    public void print(){
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (Tile t : tiles) {
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
