package pij.main.player;

import pij.main.TileRack;

public interface  Player {
    int getScore();
    TileRack getRack();
    void increaseScore(int points);
}
