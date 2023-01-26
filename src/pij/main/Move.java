package pij.main;

public class Move {
    private final char[] letters;
    private final int row;
    private final int col;
    private final boolean isGoingRight;

    public Move(String moveString) {
        final int ASCII_SUBTRAHEND = 96;
        String[] move = moveString.split(",");

        if(move.length != 3){
            throw new IllegalArgumentException("Move not accepted! Invalid string format");
        }

        if(!move[0].matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Move not accepted! Played word should not contain special characters");
        }
        this.letters = move[0].toCharArray();

        if(!Character.isLowerCase(move[1].charAt(0))){
            throw new IllegalArgumentException("Move not accepted! Column should be in lower-case");
        }
        this.col = move[1].charAt(0) - ASCII_SUBTRAHEND;

        try{
            this.row = Integer.parseInt(move[1].substring(1));
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Move not accepted! Row should be in correct int format");
        }

        if(move[2].charAt(0) != 'r' || move[2].charAt(0) == 'd'){
            throw new IllegalArgumentException("Move not accepted! Direction should be in either r - right or d - down only");
        }
        this.isGoingRight = move[2].charAt(0) == 'r';
    }

    public char[] getLetters() {
        return letters;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean towardsRight() {
        return isGoingRight;
    }
}
