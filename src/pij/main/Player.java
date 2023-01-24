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

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
}
