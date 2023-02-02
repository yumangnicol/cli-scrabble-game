package pij.main;

public class HumanScrabblePlayer implements ScrabblePlayer {
    private int score;
    private TileRack rack;

    public HumanScrabblePlayer() {
        this.score = 0;
        this.rack = new TileRack();
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
    public TileRack getRack() {
        return rack;
    }
}
