package pij.main;

import pij.main.utils.TileSettings;

public class Tile {
    private final char letter;
    private final int value;

    public Tile(char letter) {
        this.letter = letter;
        this.value = Character.isLowerCase(letter) ? 3 : TileSettings.getLetterValue(letter);
    }

    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }

}
