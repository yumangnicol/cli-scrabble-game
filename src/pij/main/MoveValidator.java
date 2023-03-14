package pij.main;

/**
 * A validator that checks if a move legal or not
 * @author Nicol Luis Yumang
 */
public class MoveValidator {

    /**
     * Obtains the full word of the move
     * @param move the move to validate
     * @param board the board the move is played
     * @return full word of the move
     */
    public static String constructMoveWord(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
        int currRow = move.getRow();
        int currCol = move.getCol();
        StringBuilder word = new StringBuilder();
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
        while(currCol <= board.length() && currRow <= board.length() && !board.isSquareEmpty(currRow, currCol)){
            word.append(board.getSquareValue(currRow, currCol));
            currRow += rowDelta;
            currCol += colDelta;
        }

        return word.toString();
    }

    /**
     * Checks if the rack contains all the tiles to play the move
     * @param move the move to validate
     * @param rack the rack to check if move has all tiles needed
     * @return return true if rack contains all the tiles it needs, false otherwise
     */
    private static boolean rackContainsTiles(Move move, TileRack rack){
        return rack.containsAll(move.getTiles());
    }

    /**
     * Checks if the move is connected to existing tiles on the board
     * @param move the move to validate
     * @param board the board the move is played
     * @return true if the move is connected to existing tiles on the board, false otherwise
     */
    public static boolean isMoveConnected(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
        int col = move.getCol();
        int row = move.getRow();

        for(int i = 0; i < moveLength; i++){
            if(move.isGoingRight()){
                if(board.isSquareHorizontalAdjacent(row, col + i)){
                    return true;
                }
            } else {
                if(board.isSquareVerticalAdjacent(row + i, col)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the move is in the center of the board
     * @param move the move to validate
     * @param board the board the move is played
     * @return true if move hits the center of the board, false otherwise
     */
    public static boolean isMoveInCenter(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
        int currCol = move.getCol();
        int currRow = move.getRow();
        int centerRow = board.getCenterRow();
        int centerCol = board.getCenterCol();
        int rowDelta = 0, colDelta = 0;

        // Determine direction
        if(move.isGoingRight()){
            currCol--;
            colDelta = 1;
        } else {
            currRow--;
            rowDelta = 1;
        }

        // Checks each square if it will hit the center square of the board
        for(int i = 0; i <= moveLength; i++){
            if(centerRow == currRow && centerCol == currCol){
                return true;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        return false;
    }

    /**
     * Checks if the move creates only 1 word in the board
     * @param move the move to validate
     * @param board the board the move is played
     * @return true if the move does not create more than 1 word in the board, false otherwise
     */
    private static boolean isMoveOneWord(Move move, ScrabbleBoard board){
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

        // Checks each square if it has adjacent tiles that are not empty
        while (currCount < letterCount){
            if(board.isSquareEmpty(currRow, currCol)){
                if(move.isGoingRight() && board.isSquareVerticalAdjacent(currRow, currCol)){
                    return false;
                } else if(!move.isGoingRight() && board.isSquareHorizontalAdjacent(currRow, currCol)) {
                    return false;
                }
                currCount++;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        return true;
    }

    /**
     * Checks if the move goes beyond the bounds of the board
     * @param move the move to validate
     * @param board the board the move is played
     * @return true if the move does not go outside the board, false otherwise
     */
    public static boolean isMoveWithinBoardBounds(Move move, ScrabbleBoard board){
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

        // Checks each square if it is out of bounds
        while(currCount < moveLength){
            if(currRow < 1 || currRow > board.length() || currCol < 1 || currCol > board.length()){
                return false;
            }
            if(board.isSquareEmpty(currRow, currCol)){
                currCount++;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }
        return true;
    }

    /**
     * Validates a move if it is valid to play in the game
     * @param move the move to validate
     * @param rack the rack to check if move has all tiles needed
     * @param board the board the move is played
     * @param wordList the list of words to check if move is valid
     * @param isFirstMove true if it is the first move of the game, false otherwise
     * @return true if move is valid, false otherwise
     */
    public static boolean validateMove(Move move, TileRack rack, ScrabbleBoard board, WordList wordList, boolean isFirstMove) {

        if(!rackContainsTiles(move, rack)){
            System.out.println("Player rack does not contain all the tiles to play the move");
            return false;
        }

        if(!isMoveWithinBoardBounds(move, board)){
            System.out.println("Move will go outside the bounds of the board");
            return false;
        }

        if(isFirstMove && !isMoveInCenter(move, board)){
            System.out.println("First move should be within the center of the board");
            return false;
        }

        if(!isFirstMove && !isMoveConnected(move, board)){
            System.out.println("Move should be connected to existing tiles on the board");
            return false;
        }

        String word = constructMoveWord(move, board);
        if(!wordList.contains(word.toLowerCase())){
            System.out.println(word + " is not a valid word to play");
            return false;
        }

        if(!isMoveOneWord(move, board)){
            System.out.println("Move should not lead to more than one word created on the board");
            return false;
        }

        return true;
    }
}
