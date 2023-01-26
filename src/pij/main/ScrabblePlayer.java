package pij.main;

public interface ScrabblePlayer {

    int getScore();

    void increaseScore(int points);

    LetterRack getRack();
}
