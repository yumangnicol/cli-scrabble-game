package pij.main;

public class ScrabbleBoard {

    /**
     * The String matrix that represents the rows and columns of a ScrabbleBoard.
     */
    private String[][] boardMatrix;
    private int centerRow, centerCol;

    /**
     * Constructs a new ScrabbleBoard with the given boardMatrix.
     * @param boardMatrix the matrix that represents the rows and columns of a ScrabbleBoard.
     */
    public ScrabbleBoard(String[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
        this.setCenterSquare(this.length());
    }

    /**
     * Prints the ScrabbleBoard to the console.
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
            System.out.println("");
        }
    }

    private void setCenterSquare(int boardSize){
        if (boardSize % 2 != 0) {
            this.centerRow = (boardSize / 2) + 1;
        } else {
            this.centerRow = (boardSize / 2);
        }
        this.centerCol = this.centerRow;
    }

    public int getCenterRow() {
        return centerRow;
    }

    public int getCenterCol() {
        return centerCol;
    }

    public boolean isSquareHorizontalAdjacent(int row, int col){
        if(col >= this.length()){
            return !isSquareEmpty(row, col-1);
        } else if (col <= 1) {
            return !isSquareEmpty(row, col+1);
        } else {
            return (!isSquareEmpty(row, col-1) || !isSquareEmpty(row, col+1));
        }
    }

    public boolean isSquareVerticalAdjacent(int row, int col){
        if(row >= this.length()){
            return !isSquareEmpty(row-1, col);
        } else if (row <= 1) {
            return !isSquareEmpty(row+1, col);
        } else {
            return (!isSquareEmpty(row-1, col) || !isSquareEmpty(row+1, col));
        }

    }

    public boolean placeLetters(Move move) {
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

        while (currCount < letterCount){
            if(isSquareEmpty(currRow, currCol)){
                this.boardMatrix[currRow][currCol] = "" + move.getLetters()[currCount];
                currCount++;
            }
            currRow += rowDelta;
            currCol += colDelta;
        }

//        if(move.towardsRight()){
//            int counter  = 0;
//            while(counter != letters.length){
//                if(isSquareEmpty(row, col)){
//                    this.boardMatrix[row][col] = "" + letters[counter];
//                    counter++;
//                }
//                col++;
//            }
//        } else if(direction == 'd'){
//            int counter  = 0;
//            while(counter != letters.length){
//                if(isSquareEmpty(row, col)){
//                    this.boardMatrix[row][col] = "" + letters[counter];
//                    counter++;
//                }
//                row++;
//            }
//        }
        return true;
    }

    public boolean isSquareEmpty(int row, int col){
        String square = boardMatrix[row][col];
        if(square.equals(".") || square.contains("(") || square.contains("{") || square.contains("}") || square.contains(")")) {
            return true;
        }
        return false;
    }

    public String getSquareValue(int row, int col){
        return boardMatrix[row][col];
    }

    public int length(){
        return this.boardMatrix.length-1;
    }
}

