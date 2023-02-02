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

        ArrayList<Tile> drawn = tileBag.drawTiles(count);
        if(drawn.size() == 0){
            return false;
        }

        this.rack.addAll(drawn);
        return true;
    }

    public void removeTiles(ArrayList<Tile> tiles){
        for (Tile t : tiles) {
            removeTile(t);
        }
    }

    private void removeTile(Tile tile){
        for (int i = 0; i < this.rack.size(); i++) {
            if (tile.getLetter() == this.rack.get(i).getLetter()) {
                this.rack.remove(i);
                return; // returns so that it only removes the first instance of the tile
            }
        }
    }

    public boolean containsAll(ArrayList<Tile> tiles){
        ArrayList<Tile> temp = (ArrayList<Tile>) this.rack.clone();

        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < temp.size(); j++) {
                if (tiles.get(i).getLetter() == temp.get(j).getLetter()){
                    temp.remove(j);
                }
            }
        }
        return temp.size() == this.rack.size() - tiles.size();
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
