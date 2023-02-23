package pij.main;

import pij.main.utils.Constants;

import java.util.ArrayList;

public class Move {
    private final ArrayList<Tile> tiles;
    private final int row;
    private final int col;
    private final boolean goingRight;

    public Move(String moveString) {
        final int ASCII_SUBTRAHEND = 96;
        String[] move = moveString.split(",");

        if(move.length != 3){
            throw new IllegalArgumentException("Move not accepted! Invalid string format");
        }

        if(!move[0].matches(Constants.MOVE_STRING_REGEX_FORMAT)) {
            throw new IllegalArgumentException("Move not accepted! Played word should not contain special characters");
        }

        this.tiles = new ArrayList<>();
        for(char c : move[0].toCharArray()){
            this.tiles.add(new Tile(c));
        }

        if(!Character.isLowerCase(move[1].charAt(0))){
            throw new IllegalArgumentException("Move not accepted! Column should be in lower-case");
        }
        this.col = move[1].charAt(0) - ASCII_SUBTRAHEND;

        try{
            this.row = Integer.parseInt(move[1].substring(1));
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Move not accepted! Row should be in correct int format");
        }

        if(move[2].charAt(0) != 'r' && move[2].charAt(0) != 'd'){
            throw new IllegalArgumentException("Move not accepted! Direction should be in either r - right or d - down only");
        }
        this.goingRight = move[2].charAt(0) == 'r';
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    @Override
    public String toString() {
        StringBuilder moveString = new StringBuilder();

        moveString.append("The move is:    Word: ");
        for (Tile t : tiles) {
            moveString.append(t.getLetter());
        }

        moveString.append(" at position: ");
        char letterCol = (char) (this.col + Constants.CHAR_INT_VALUE_BEFORE_SMALL_LETTER_A);
        moveString.append(letterCol);
        moveString.append(row);

        moveString.append(", direction: ");
        String direction = isGoingRight() ? "right" : "left";
        moveString.append(direction);

        return moveString.toString();
    }
}
