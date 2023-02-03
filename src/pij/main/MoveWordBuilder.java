package pij.main;

public class MoveWordBuilder {
    public static String getMoveWord(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
        int currRow = move.getRow();
        int currCol = move.getCol();
        StringBuilder word = new StringBuilder();
        int currCount = 0;
        int rowDelta = 0, colDelta = 0;

        // Determine direction
        if(move.towardsRight()){
            colDelta = 1;
        } else {
            rowDelta = 1;
        }

        // Go to the start of the word
        while(currRow - rowDelta > 1 && currCol - colDelta > 1 && !board.isSquareEmpty(currRow - rowDelta, currCol - colDelta)){
            currRow -= rowDelta;
            currCol -= colDelta;
        }

        // Build the word body
        while(currCount < moveLength){
            if(board.isSquareEmpty(currRow, currCol)){
                word.append(move.getTiles().get(currCount).getLetter());
                currCount++;
            } else {
                word.append(board.getSquareValue(currRow, currCol));
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        // Build the rest of the word with the trailing tiles
        while(currCol < board.length() && currRow < board.length() && !board.isSquareEmpty(currRow + rowDelta, currCol + colDelta)){
            word.append(board.getSquareValue(currRow + rowDelta, currCol + colDelta));
            currRow += rowDelta;
            currCol += colDelta;
        }

        return word.toString();
    }
}
