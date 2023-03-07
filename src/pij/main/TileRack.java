package pij.main;

import pij.main.utils.Constants;

import java.util.ArrayList;

public class TileRack {
    private ArrayList<Tile> tiles;

    public TileRack(){
        this.tiles = new ArrayList<>();
    }

    public boolean refill(TileBag tileBag, int count){
        if(count == 0 || count > this.getRackSpace()) {
            return false;
        }

        ArrayList<Tile> drawn = tileBag.drawTiles(count);
        if(drawn.size() == 0){
            return false;
        }

        this.tiles.addAll(drawn);
        return true;
    }

    public void removeTiles(ArrayList<Tile> tiles){
        for (Tile t : tiles) {
            removeTile(t);
        }
    }

    private void removeTile(Tile tile){
        for (int i = 0; i < this.tiles.size(); i++) {
            if (tile.getLetter() == this.tiles.get(i).getLetter() || (Character.isLowerCase(tile.getLetter()) && this.tiles.get(i).getLetter() == ' ')) {
                this.tiles.remove(i);
                return; // returns so that it only removes the first instance of the tile
            }
        }
    }

    private void removeTile(Tile tile, ArrayList<Tile> tiles){
        // Note: Changes the state of the ArrayList passed
        for (int j = 0; j < tiles.size(); j++) {
            if (tile.getLetter() == tiles.get(j).getLetter() || (Character.isLowerCase(tile.getLetter()) && tiles.get(j).getLetter() == ' ')){
                tiles.remove(j);
                return; // returns so that it only removes the first instance of the tile
            }
        }
    }

    public boolean containsAll(ArrayList<Tile> tiles){
        ArrayList<Tile> temp = (ArrayList<Tile>) this.tiles.clone();

        for (Tile tile : tiles) {
            this.removeTile(tile, temp);
        }

        return temp.size() == (this.tiles.size() - tiles.size());
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
