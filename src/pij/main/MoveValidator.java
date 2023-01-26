package pij.main;

public class MoveValidator {

    public static boolean validateMove(Move move, ScrabblePlayer player, ScrabbleBoard board){

        if(!playerRackContainsLetters(move, player)){
            System.out.println("Player rack does not contain all the letters to play the move");
            return false;
        }

        if(!isMoveWithinBoardBounds(move, board)){
            System.out.println("Move will go outside the bounds of the board");
            return false;
        }

        if(!isMoveConnected(move, board)){
            System.out.println("Move should be connected to existing letters on the board");
        }

        String word = getMoveWord(move, board);
        if(!WordList.contains(word)){
            System.out.println(word + " is not a valid word to play");
            return false;
        }

        return true;
    }

    private static boolean playerRackContainsLetters(Move move, ScrabblePlayer player){
        return player.getRack().containsAll(move.getLetters());
    }

    public static boolean isMoveWithinBoardBounds(Move move, ScrabbleBoard board){
        int letterCount = move.getLetters().length;
        int currRow = move.getRow();
        int currCol = move.getCol();
        int currCount = 0;
        int rowDelta = 0, colDelta = 0;

        // Determine direction
        if(move.towardsRight()){
            colDelta = 1;
        } else {
            rowDelta = 1;
        }

        // Checks each square if it's out of bounds
        while(currCount < letterCount){
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

        // Go to the start of the word
        while(!board.isSquareEmpty(currRow + rowDelta, currCol + colDelta)){
            currRow += rowDelta;
            currCol += colDelta;
        }

        // Build the word body
        while(currCount < letterCount){
            if(board.isSquareEmpty(currRow, currCol)){
                word.append(move.getLetters()[currCount]);
                currCount++;
            } else {
                word.append(board.getSquareValue(currRow, currCol));
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        // Build the rest of the word with the trailing letters
        while(!board.isSquareEmpty(currRow += rowDelta, currCol + colDelta)){
            word.append(board.getSquareValue(currRow + rowDelta, currCol + colDelta));
            currRow += rowDelta;
            currCol += colDelta;
        }

        return word.toString();
    }

    public static boolean isMoveConnected(Move move, ScrabbleBoard board){
        int moveLength = move.getLetters().length;
        int col = move.getCol();
        int row = move.getRow();

        for(int i = 0; i < moveLength; i++){
            if(move.towardsRight()){
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

}
