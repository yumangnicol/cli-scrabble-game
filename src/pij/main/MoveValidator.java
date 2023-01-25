package pij.main;

public class MoveValidator {

    public static boolean validateMove(Move move, Player player, ScrabbleBoard board){

        if(playerRackContainsLetters(move, player)){
            System.out.print("Player rack does not contain all the letters for the move");
            return false;
        }

        if(!moveWithinBoardBounds(move, board)){
            System.out.print("Move will go outside the bounds of the board");
        }

        String word = getMoveWord(move, board);

        return true;
    }

    private static boolean playerRackContainsLetters(Move move, Player player){
        return player.getRack().containsAll(move.getLetters());
    }

    public static boolean moveWithinBoardBounds(Move move, ScrabbleBoard board){
        int letterCount = move.getLetters().length;
        int currRow = move.getRow();
        int currCol = move.getCol();
        int currCount = 0;
        int rowDelta = 0, colDelta = 0;

        if(move.towardsRight()){
            colDelta = 1;
        } else {
            rowDelta = 1;
        }

        while(currCount < letterCount){
            if(currRow < 1 || currRow > board.length() || currCol < 1 || currCol > board.length()){
                return false;
            }
            if(board.squareEmpty(currRow, currCol)){
                currCount++;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }
        return true;
    }

    public static String getMoveWord(Move move, ScrabbleBoard board){
        int letterCount = move.getLetters().length;
        int currRow = move.getRow();
        int currCol = move.getCol();
        StringBuilder word = new StringBuilder();
        int currCount = 0;
        int rowDelta = 0, colDelta = 0;

        // Determine direction
        if(move.towardsRight()){
            currCol--;
            colDelta = 1;
        } else {
            currRow--;
            rowDelta = 1;
        }

        // Go to the end of the word
        while(!board.squareEmpty(currRow + rowDelta, currCol + colDelta)){
            currRow += rowDelta;
            currCol += colDelta;
        }

        // Build the word
        while(currCount < letterCount){
            if(board.squareEmpty(currRow, currCol)){
                word.append(move.getLetters()[currCount]);
                currCount++;
            } else {
                word.append(board.squareValue(currRow, currCol));
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        // Build Rest of the word
        while(!board.squareEmpty(currRow += rowDelta, currCol + colDelta)){
            word.append(board.squareValue(currRow + rowDelta, currCol + colDelta));
            currRow += rowDelta;
            currCol += colDelta;
        }

        return word.toString();
    }




    // Word is in Dictionary

    // Validations of Move and Board
}
