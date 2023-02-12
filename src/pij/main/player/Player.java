package pij.main.player;

import pij.main.TileRack;

public interface Player {

    int getScore();

    void increaseScore(int points);

    TileRack getRack();
}
