package pij.main;

import pij.main.utils.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a move in a scrabble game
 * @author Nicol Luis Yumang
 */
public class Move {

    /**
     * The list of tiles used in the move
     */
    private final List<Tile> tiles;

    /**
     * The starting column of the move
     */
    private final int col;

    /**
     * The starting row of the move
     */
    private final int row;

    /**
     * Determines the direction of the move
     * True if going right, false if going down
     */
    private final boolean goingRight;

    /**
     * Constructor: Creates a new move
     * The format and contents of the moveString is validated during construction
     * @param moveString the string to create a new move from
     */
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

    /**
     * Gets the starting column of the move
     * @return the starting column of the move
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Gets the starting Row of the move
     * @return the starting Row of the move
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the list of tiles used in the move
     * @return the list of tiles used in the move
     */
    public List<Tile> getTiles() {
        return this.tiles;
    }

    /**
     * Gets the direction of the move
     * @return the direction of the move
     */
    public boolean isGoingRight() {
        return this.goingRight;
    }

    /**
     * Create a prettified summary of the move
     * @return prettified summary of the move
     */
    @Override
    public String toString() {
        StringBuilder moveString = new StringBuilder();

        moveString.append("The move is:    Word: ");
        for (Tile t : this.tiles) {
            moveString.append(t.getLetter());
        }

        moveString.append(" at position: ");
        char letterCol = (char) (this.col + Constants.CHAR_INT_VALUE_BEFORE_SMALL_LETTER_A);
        moveString.append(letterCol);
        moveString.append(this.row);

        moveString.append(", direction: ");
        String direction = this.isGoingRight() ? "right" : "left";
        moveString.append(direction);

        return moveString.toString();
    }
}
