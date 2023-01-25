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



    public boolean isValidMove(char[] letters, int row, int col, char direction){

        // Checks if row and col values are within the bounds of the matrix
        if(row < 1 || row > this.boardMatrix.length - 1 || col < 1 || col > this.boardMatrix[0].length - 1) { // -1 because size is added 1 because of matrix headers
            return false;
        }

        if(!squareEmpty(row, col)){
            return false;
        }

        // Check dictionary


        //Check if word is connected


        return true;
    }

    public boolean connectedMove(char [] letters, int row, int col, char direction){
      for(int i = col; i < col+letters.length; i++){
          if(horizontalAdjacent(row, i)){
              return true;
          }
      }
        return false;
    }

    public String moveWord(char[] letters, int row, int col, char direction){
        int lettersCount = letters.length;
        int currRow = row;
        int currCol = col;

        StringBuilder word = new StringBuilder();
        int currCount = 0;

        if(direction == 'r'){

            // Goes all the way to the left
            while(!squareEmpty(currRow, currCol-1)){
                currCol --;
            }

            // Builds word to check
            while(currCount < lettersCount){
                if(squareEmpty(currRow, currCol)){
                    word.append(letters[currCount]);
                    currCount++;
                } else {
                    word.append(this.boardMatrix[currRow][currCol]);
                }
                currCol++;
            }
        }
        return word.toString();
    }


    private boolean horizontalAdjacent(int row, int col){
        return (!squareEmpty(row, col-1) || !squareEmpty(row, col+1));
    }

    private boolean verticalAdjacent(int row, int col){
        return (squareEmpty(row-1, col) || squareEmpty(row+1, col));
    }


    public boolean placeLetters(char[] letters, int row, int col, char direction) {
        if(direction == 'r'){
            int counter  = 0;
            while(counter != letters.length){
                if(squareEmpty(row, col)){
                    this.boardMatrix[row][col] = "" + letters[counter];
                    counter++;
                }
                col++;
            }
        } else if(direction == 'd'){
            int counter  = 0;
            while(counter != letters.length){
                if(squareEmpty(row, col)){
                    this.boardMatrix[row][col] = "" + letters[counter];
                    counter++;
                }
                row++;
            }
        }
        return true;
    }

    public boolean squareEmpty(int row, int col){
        String square = boardMatrix[row][col];
        if(square.equals(".") || square.contains("(") || square.contains("{") || square.contains("}") || square.contains(")")) {
            return true;
        }
        return false;
    }

    public String squareValue(int row, int col){
        return boardMatrix[row][col];
    }

    public int length(){
        return this.boardMatrix.length-1;
    }
}

