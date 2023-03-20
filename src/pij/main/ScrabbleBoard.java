package pij.main;

import pij.main.utils.Constants;

/**
 * Represents the board in a scrabble game
 * @author Nicol Luis Yumang
 */
public class ScrabbleBoard {

    /**
     * The String matrix that represents the rows and columns of a ScrabbleBoard
     */
    private final String[][] boardMatrix;

    /**
     * The row and column value of the center of the scrabble board
     */
    private int centerRow, centerCol;

    /**
     * Constructor: Creates a new ScrabbleBoard with the given boardMatrix
     * @param boardMatrix the matrix that represents the rows and columns of a scrabble board
     */
    public ScrabbleBoard(String[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
        this.setCenterSquare(this.length());
    }

    /**
     * Gets the value of a square in the scrabble board
     * If the square is equivalent to a tile, the letter
     * is returned without the value
     * @param row the row of the square
     * @param col the column of the square
     * @return the value in the square
     */
    public String getSquareValue(int row, int col){
        String value = this.boardMatrix[row][col];
        if (value.matches(Constants.BOARD_TILE_REGEX_FORMAT)) {
            return value.substring(0, 1);
        }
        return value;
    }

    /**
     * Gets the center row in the board
     * @return the center row in the board
     */
    public int getCenterRow() {
        return this.centerRow;
    }

    /**
     * Gets the center column in the board
     * @return the center column in the board
     */
    public int getCenterCol() {
        return this.centerCol;
    }

    /**
     * Checks if the square is empty
     * A square is considered empty if it is equal to a period .
     * or if it contains any of the following characters (, ), {, }
     * @param row the row of the square
     * @param col the column of the square
     * @return true if the square is empty, false otherwise
     */
    public boolean isSquareEmpty(int row, int col){
        String square = this.getSquareValue(row, col);
        return square.equals(".") || square.contains("(") || square.contains("{") || square.contains("}") || square.contains(")");
    }

    /**
     * Checks if the adjacent squares on the left and right
     * of the specified square is empty
     * @param row the row of the square
     * @param col the column of the square
     * @return true if either of the adjacent squares is not empty, false if both are empty
     */
    public boolean isSquareHorizontalAdjacent(int row, int col){
        if(col >= this.length()){
            return !this.isSquareEmpty(row, col-1);
        } else if (col <= 1) {
            return !this.isSquareEmpty(row, col+1);
        } else {
            return (!this.isSquareEmpty(row, col-1) || !this.isSquareEmpty(row, col+1));
        }
    }

    /**
     * Checks if the adjacent squares on the top and bottom
     * of the specified square is empty
     * @param row the row of the square
     * @param col the column of the square
     * @return true if either of the adjacent squares is not empty, false if both are empty
     */
    public boolean isSquareVerticalAdjacent(int row, int col){
        if(row >= this.length()){
            return !this.isSquareEmpty(row-1, col);
        } else if (row <= 1) {
            return !this.isSquareEmpty(row+1, col);
        } else {
            return (!this.isSquareEmpty(row-1, col) || !this.isSquareEmpty(row+1, col));
        }
    }

    /**
     * Gets the length of the board matrix, not including the space for the row and column headers
     * @return the length of the board matrix
     */
    public int length(){
        return this.boardMatrix.length-1;
    }

    /**
     * Places tiles on the board based on the given move
     * @param move the move to be done on the board
     */
    public void placeTiles(Move move) {
        int letterCount = move.getTiles().size();
        int currRow = move.getRow();
        int currCol = move.getCol();
        int currCount = 0;
        int rowDelta = 0, colDelta = 0;

        // Determine direction
        if(move.isGoingRight()){
            colDelta = 1;
        } else {
            rowDelta = 1;
        }

        while (currCount < letterCount){
            if(this.isSquareEmpty(currRow, currCol)){
                this.boardMatrix[currRow][currCol] = "" + move.getTiles().get(currCount).getLetter() + move.getTiles().get(currCount).getValue();
                currCount++;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }
    }

    /**
     * Prints the prettified scrabble board in the console
     */
    public void print(){
        for(String[] row : this.boardMatrix){
            for(String s : row){
                if(s.length() == 1){
                    System.out.print(" " + s + " ");
                } else if (s.length() == 2) {
                    System.out.print(s + " ");
                } else {
                    System.out.print(s);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Sets the center row and column values depending on the size of the board
     * @param boardSize the size of the board
     */
    private void setCenterSquare(int boardSize){
        if (boardSize % 2 != 0) {
            this.centerRow = (boardSize / 2) + 1;
        } else {
            this.centerRow = (boardSize / 2);
        }
        this.centerCol = this.centerRow;
    }

}

