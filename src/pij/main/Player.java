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

    public void makeMove(String move){
        String[] moveStr = move.split(",");

        //0 Letters to use

        // Position

        // Down or Right
    }
}
