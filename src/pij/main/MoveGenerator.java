package pij.main;

import pij.main.utils.Constants;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A generator that creates a move to play
 * @author Nicol Luis Yumang
 */
public class MoveGenerator {

    /**
     * Creates a new move from a constructed move string using the given parameters
     * @param word the word of the move
     * @param row the starting row of the move
     * @param col the starting column of the move
     * @param goingRight the direction of the move
     * @param firstMove true if the constructed move is the first move of the game, false otherwise
     * @return new move to be played
     */
    private static Move constructMove(String word, int row, int col, boolean goingRight, boolean firstMove){

        StringBuilder sb = new StringBuilder();
        String moveStr = firstMove ? word : word.substring(1);
        sb.append(moveStr);
        sb.append(',');
        char rowLetter = (char) (col + Constants.CHAR_INT_VALUE_BEFORE_SMALL_LETTER_A);
        sb.append(rowLetter);
        sb.append(row);
        sb.append(',');
        char direction = goingRight ? 'r' : 'd';
        sb.append(direction);

        return new Move(sb.toString());
    }

    /**
     * Counts the number of available space for a move going down
     * @param board the board the move is played
     * @param row the starting row of the move
     * @param col the starting column of the move
     * @return number of available space going down
     */
    private static int downMoveAvailableSpace(ScrabbleBoard board, int row, int col){
        int currRow = row;
        int space = 0;
        // Check: top of connecting square is not out of bounds and empty
        if((currRow - 1) > 1 && !board.isSquareEmpty(currRow - 1, col)){
            return 0;
        }
        // Check: available space downward
        while(currRow < board.length()
                && board.isSquareEmpty(currRow + 1, col)
                && !board.isSquareHorizontalAdjacent(currRow + 1, col)){
            space++;
            currRow++;
        }
        return space;
    }

    /**
     * Creates a move to be played as a first move of the game given the available tiles in the rack
     * @param rack the list of tiles to use for the move
     * @param board the board the move is played
     * @param wordList the list of words to check if move is valid
     * @return valid move to play as a first move of the game
     */
    public static Move generateFirstMove(TileRack rack, ScrabbleBoard board, WordList wordList){
        List<String> validMoves = WordGenerator.generateWords(rack.getTiles(), 7, "", wordList);
        Optional<String> optionalMove = validMoves.stream().max(Comparator.comparing(String::length));
        if(validMoves.size() > 0){
            String finalMove = optionalMove.get();

            int row = board.getCenterRow();
            int col = board.getCenterCol() - (finalMove.length()/2);

            return constructMove(finalMove, row, col, true, true);
        }
        return null;
    }

    /**
     * Creates a move given the available tiles in the rack and the current state of the board
     * @param rack the list of tiles to use for the move
     * @param board the board the move is played
     * @param wordList the list of words to check if move is valid
     * @return valid move to play
     */
    public static Move generateSucceedingMove(TileRack rack, ScrabbleBoard board, WordList wordList){
        for (int row = 1; row < board.length(); row++) {
            for (int col = 1; col < board.length(); col++) {

                // If board is not empty, check if move can be played using this hook
                if(!board.isSquareEmpty(row, col)){

                    int moveSpace = downMoveAvailableSpace(board, row, col);
                    if(moveSpace > 0){
                        List<String> validMoves = WordGenerator.generateWords(rack.getTiles(), moveSpace, board.getSquareValue(row, col),wordList);
                        Optional<String> optionalMove = validMoves.stream().max(Comparator.comparing(String::length));
                        if(validMoves.size() > 0){
                            String finalMove = optionalMove.get();
                            return constructMove(finalMove, row + 1, col, false, false);
                        }
                    }

                    moveSpace = rightMoveAvailableSpace(board, row, col);
                    if(rightMoveAvailableSpace(board, row, col) > 0){
                        List<String> validMoves = WordGenerator.generateWords(rack.getTiles(), moveSpace, board.getSquareValue(row, col),wordList);
                        Optional<String> optionalMove = validMoves.stream().max(Comparator.comparing(String::length));
                        if(validMoves.size() > 0){
                            String finalMove = optionalMove.get();
                            return constructMove(finalMove, row, col + 1, true, false);
                        }
                    }

                }

            }
        }
        return null;
    }

    /**
     * Counts the number of available space for a move going right
     * @param board the board the move is played
     * @param row the starting row of the move
     * @param col the starting column of the move
     * @return number of available space going right
     */
    private static int rightMoveAvailableSpace(ScrabbleBoard board, int row, int col){
        int currCol = col;
        int space = 0;
        // Check: left of connecting square is not out of bounds and empty
        if(currCol > 1 && !board.isSquareEmpty(row, currCol - 1)){
            return 0;
        }
        //Check: available space towards right
        while(currCol < board.length()
                && board.isSquareEmpty(row, currCol + 1)
                && !board.isSquareVerticalAdjacent(row, currCol + 1)){
            space++;
            currCol++;
        }
        return space;
    }
}
