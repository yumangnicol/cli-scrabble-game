package pij.main.utils;

import pij.main.Move;
import pij.main.ScrabbleBoard;

public class PointCalculator {
    public static int calculatePoints(Move move, ScrabbleBoard board){
        int total = 0;
        int premiumWordMultiplier = 1;

        int moveLength = move.getTiles().size();
        int currRow = move.getRow();
        int currCol = move.getCol();
        int currCount = 0;
        int rowDelta = 0, colDelta = 0;

        if(move.isGoingRight()){
            colDelta = 1;
        } else {
            rowDelta = 1;
        }

        // Go to the start of the word
        while(currRow - rowDelta > 1 && currCol - colDelta > 1 && !board.isSquareEmpty(currRow - rowDelta, currCol - colDelta)){
            currRow -= rowDelta;
            currCol -= colDelta;
        }

        while(currCount < moveLength){
            String square = board.getSquareValue(currRow, currCol);

            if (square.contains("(") || square.contains(")")){
                total += (move.getTiles().get(currCount).getValue() * getPremiumValue(board, currRow, currCol));
                currCount++;
            } else if (square.contains("{") || square.contains("}")) {
                premiumWordMultiplier *= getPremiumValue(board, currRow, currCol);
                total += move.getTiles().get(currCount).getValue();
                currCount++;
            } else if (square.equals(".")){
                total += move.getTiles().get(currCount).getValue();
                currCount++;
            } else {
                if(Character.isLowerCase(square.charAt(0))) {
                    total += 3;
                } else {
                    total += TileSettings.getLetterValue(square.charAt(0));
                }
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        while(currCol < board.length() && currRow < board.length() && !board.isSquareEmpty(currRow + rowDelta, currCol + colDelta)){
            String square = board.getSquareValue(currRow + rowDelta, currCol + colDelta);
            total += Character.isLowerCase(square.charAt(0)) ? 3: TileSettings.getLetterValue(square.charAt(0));
            currRow += rowDelta;
            currCol += colDelta;
        }

        return total * premiumWordMultiplier;
    }
    private static int getPremiumValue(ScrabbleBoard board, int row, int col){
        String square = board.getSquareValue(row, col);

        if (square.contains(")") || square.contains("}")){
            return Character.getNumericValue(square.charAt(1));
        } else {
            return Integer.parseInt(square.substring(1));
        }
    }
}
