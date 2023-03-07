package pij.main;

public class Player {
    private int score;
    private final TileRack rack;
    private int consecutivePass;

    public Player() {
        this.score = 0;
        this.consecutivePass = 0;
        this.rack = new TileRack();
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public void decreaseScore(int points) { this.score -= points; }

    public TileRack getRack() {
        return rack;
    }

    public void resetConsecutivePass() {
        this.consecutivePass = 0;
    }

    public int getConsecutivePass() {
        return this.consecutivePass;
    }

    public void incrementConsecutivePass(){
        this.consecutivePass++;
    }
}
