package pij.main.player;

import pij.main.TileRack;
import pij.main.player.Player;

public class HumanPlayer implements Player {
    private int score;
    private TileRack rack;

    public HumanPlayer() {
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
