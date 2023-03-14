package pij.main;

import pij.main.utils.Constants;
import pij.main.utils.TileSettings;

/**
 * A calculator that computes the total number of points of a move
 * @author Nicol Luis Yumang
 */
public class PointCalculator {

    /**
     * Calculates the points of a move on a scrabble board
     * @param move the move to calculate
     * @param board the board the move is played
     * @return number of points of a move
     */
    public static int calculatePoints(Move move, ScrabbleBoard board){
        int total = 0;
        int premiumWordMultiplier = 1;

        int moveLength = move.getTiles().size();
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

        // Move pointer back to check for prefixes
        while(currRow - rowDelta >= 1 && currCol - colDelta >= 1 && !board.isSquareEmpty(currRow - rowDelta, currCol - colDelta)){
            currRow -= rowDelta;
            currCol -= colDelta;
        }

        // Move pointer until all letters are used from the move
        while(currCount < moveLength){
            String square = board.getSquareValue(currRow, currCol);

            if (square.contains("(") || square.contains(")")){
                // Multiplies the value of the letter to the premium value
                total += (move.getTiles().get(currCount).getValue() * getPremiumValue(board, currRow, currCol));
                currCount++;
            } else if (square.contains("{") || square.contains("}")) {
                // Multiplies premiumWordMultiplier to the premium value
                premiumWordMultiplier *= getPremiumValue(board, currRow, currCol);
                total += move.getTiles().get(currCount).getValue();
                currCount++;
            } else if (square.equals(".")){
                total += move.getTiles().get(currCount).getValue();
                currCount++;
            } else {
                if(Character.isLowerCase(square.charAt(0))) {
                    // If letter is lowercase apply wildcard value
                    total += TileSettings.getLetterValue(' ');
                } else {
                    total += TileSettings.getLetterValue(square.charAt(0));
                }
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        // Move pointer to check for suffixes
        while(currCol <= board.length() && currRow <= board.length() && !board.isSquareEmpty(currRow, currCol)){
            String square = board.getSquareValue(currRow, currCol);
            total += Character.isLowerCase(square.charAt(0)) ? TileSettings.getLetterValue(' ') : TileSettings.getLetterValue(square.charAt(0));
            currRow += rowDelta;
            currCol += colDelta;
        }

        return moveLength == Constants.MAX_RACK_SIZE ? (total * premiumWordMultiplier) + Constants.SEVEN_LETTER_MOVE_BONUS : total * premiumWordMultiplier;
    }

    /**
     * Gets the numerical value of a premium word or square
     * @param board the board to check the value of a premium
     * @param row the row on the board
     * @param col the col on the board
     * @return the numerical value of a premium word or square
     */
    private static int getPremiumValue(ScrabbleBoard board, int row, int col){
        String square = board.getSquareValue(row, col);

        if (square.contains(")") || square.contains("}")){
            return Character.getNumericValue(square.charAt(1));
        } else {
            return Integer.parseInt(square.substring(1));
        }
    }
}
