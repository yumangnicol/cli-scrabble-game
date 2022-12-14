package pij.main;

public class ScrabbleBoard {
    private String[][] boardMatrix;

    public ScrabbleBoard(String[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

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

