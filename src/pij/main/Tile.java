package pij.main;

import java.util.Objects;

public class Tile {
    private char letter;
    private int value;

    public Tile(char letter) {
        this.letter = letter;
        this.value = LetterUtils.getLetterValue(letter);
    }

    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }

}
