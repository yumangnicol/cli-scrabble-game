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

    public boolean makeMove(String move){
        String[] moveStr = move.split(",");
        // ADD VALIDATION to move string ❗️

        //0 Letters to use
        char[] letters = moveStr[0].toCharArray();
        if (!this.rack.containsAll(letters)){
            return false;
        }

        // Position
        char[] position = moveStr[1].toCharArray();



        // Down or Right

        // Check Dictionary
        return true;
    }
}
