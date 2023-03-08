package pij.main;

import pij.main.utils.TileSettings;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && value == tile.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, value);
    }
}
