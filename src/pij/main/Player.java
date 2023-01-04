package pij.main;

public class Player {
    int score;
    LetterRack rack;

    public Player() {
        this.score = 0;
    }

    public Player(LetterBag letterBag){
        this.score = 0;
        this.rack = new LetterRack();
    }

    public boolean makeMove(String move, ScrabbleBoard board){
        String[] moveStr = move.split(",");
        // ADD VALIDATION to move string ❗️

        //0 Letters to use
        char[] letters = moveStr[0].toCharArray();
        if (!this.rack.containsAll(letters)){
            return false;
        }

        // Position
        final int ASCII_SUBTRACTOR = 96; // 96 because the decimal value of char a starts at 97
        String position = moveStr[1];
        int row = Integer.parseInt(position.substring(1));
        int col = position.charAt(0) - ASCII_SUBTRACTOR ;
        if (!board.isValidMove(row, col)){
            return false;
        }

        // Down or Right

        // Check Dictionary
        return true;
    }
}
