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
        this.setCenterSquare(boardMatrix.length - 1); // -1 because size is added 1 because of matrix headers
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

    public boolean isValidMove(char[] letters, int row, int col, char direction){

        if(row < 1 || row > this.boardMatrix.length - 1 || col < 1 || col > this.boardMatrix[0].length - 1) { // -1 because size is added 1 because of matrix headers
            return false;
        }

        if(!isSquareEmpty(row, col)){
            return false;
        }

        // Check dictionary

        return true;
    }

    public boolean placeTiles(char[] letters, int row, int col, char direction) {
        if(direction == 'r'){
            int counter  = 0;
            while(counter != letters.length){
                if(isSquareEmpty(row, col)){
                    this.boardMatrix[row][col] = "" + letters[counter];
                    counter++;
                    col++;
                }
            }
        } else if(direction == 'd'){
            int counter  = 0;
            while(counter != letters.length){
                if(isSquareEmpty(row, col)){
                    this.boardMatrix[row][col] = "" + letters[counter];
                    counter++;
                }
                row++;
            }
        }
        return true;
    }

    private boolean isSquareEmpty(int row, int col){
        String square = boardMatrix[row][col];
        if(square.equals(".") || square.contains("(") || square.contains("{") || square.contains("}") || square.contains(")")) {
            return true;
        }
        return false;
    }
}

