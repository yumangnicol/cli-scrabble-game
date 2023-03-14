package pij.main;

import pij.main.utils.TileSettings;
import java.util.Objects;

/**
 * Represents a tile in a scrabble game
 * @author Nicol Luis Yumang
 */
public class Tile {
    /**
     * The letter representation of a tile
     */
    private final char letter;

    /**
     * The value of a tile
     */
    private final int value;

    /**
     * Constructor: creates a tile from a given letter or wildcard
     * The point value of a tile is set based on the TileSettings
     * @param letter the letter character to create a tile
     */
    public Tile(char letter) {
        this.letter = letter;
        this.value = Character.isLowerCase(letter) ? 3 : TileSettings.getLetterValue(letter);
    }

    /**
     * Checks the equality of a tile with another object
     * @return true if value object is equal to tile
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && value == tile.value;
    }

    /**
     * Gets the letter of a tile
     * @return letter of tile
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Gets the point value of a tile
     * @return value of tile
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a hashcode for a tile object
     * @return hashcode for tile object
     */
    @Override
    public int hashCode() {
        return Objects.hash(letter, value);
    }
}
