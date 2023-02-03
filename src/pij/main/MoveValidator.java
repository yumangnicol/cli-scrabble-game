package pij.main;

public class MoveValidator {

    public static boolean validateMove(Move move, ScrabblePlayer player, ScrabbleBoard board, boolean isFirstMove) {

        if(!playerRackContainsTiles(move, player)){
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

        String word = MoveWordBuilder.getMoveWord(move, board);
        if(!WordList.contains(word.toLowerCase())){
            System.out.println(word + " is not a valid word to play");
            return false;
        }

        return true;
    }

    private static boolean playerRackContainsTiles(Move move, ScrabblePlayer player){
        return player.getRack().containsAll(move.getTiles());
    }

    public static boolean isMoveWithinBoardBounds(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
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

    public static boolean isMoveConnected(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
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

    public static boolean isMoveInCenter(Move move, ScrabbleBoard board){
        int moveLength = move.getTiles().size();
        int currCol = move.getCol();
        int currRow = move.getRow();
        int centerRow = board.getCenterRow();
        int centerCol = board.getCenterCol();
        int rowDelta = 0, colDelta = 0;

        // Determine direction
        if(move.towardsRight()){
            currCol--;
            colDelta = 1;
        } else {
            currRow--;
            rowDelta = 1;
        }

        System.out.println(centerRow);
        System.out.println(centerCol);

        for(int i = 0; i <= moveLength; i++){
            if(centerRow == currRow && centerCol == currCol){
                return true;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

        return false;
    }
}
