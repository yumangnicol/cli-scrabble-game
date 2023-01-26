package pij.main;

public class HumanScrabblePlayer implements ScrabblePlayer {
    private int score;
    private LetterRack rack;

    public HumanScrabblePlayer() {
        this.score = 0;
        this.rack = new LetterRack();
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
    public LetterRack getRack() {
        return rack;
    }
}
