package pij.main;

public class ScrabbleBoard {

    /**
     * The String matrix that represents the rows and columns of a ScrabbleBoard.
     */
    private String[][] boardMatrix;

    /**
     * Constructs a new ScrabbleBoard with the given boardMatrix.
     * @param boardMatrix the matrix that represents the rows and columns of a ScrabbleBoard.
     */
    public ScrabbleBoard(String[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    /**
     * Prints the ScrabbleBoard to the console.
     */
    public void print(){
        for(String[] row : boardMatrix){
            for(String s : row){
                if(s.length() == 1){
                    System.out.print(" " + s + " ");
                } else {
                    System.out.print(s);
                }
            }
            System.out.println("");
        }
    }
}

