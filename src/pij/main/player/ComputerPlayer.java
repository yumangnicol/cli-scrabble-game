package pij.main.player;

import pij.main.ScrabbleBoard;
import pij.main.TileRack;
import pij.main.WordList;
import pij.main.utils.WordGenerator;

public class ComputerPlayer implements Player {
    private int score;
    private final TileRack rack;

    public ComputerPlayer() {
        this.score = 0;
        this.rack = new TileRack();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void increaseScore(int points) {
        this.score += points;
    }

    @Override
    public TileRack getRack() {
        return rack;
    }

    public void scanBoardForMove(ScrabbleBoard board, WordList wordList){
        for (int row = 1; row < board.length(); row++) {
            for (int col = 1; col < board.length(); col++) {

                // If board is not empty, check if move can be played
                if(!board.isSquareEmpty(row, col)){

                    int moveSpace = downMoveAvailableSpace(board, row, col);
                    if(moveSpace > 0){
                        WordGenerator.generateWords(this.rack.getTiles(), moveSpace, board.getSquareValue(row, col),wordList);
                    }

                    moveSpace = rightMoveAvailableSpace(board, row, col);
                    if(rightMoveAvailableSpace(board, row, col) > 0){


                    }

                }

            }
        }
    }

    public int downMoveAvailableSpace(ScrabbleBoard board, int row, int col){
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

    public int rightMoveAvailableSpace(ScrabbleBoard board, int row, int col){
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