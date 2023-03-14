package pij.main;

/**
 * Represents a player in the scrabble game
 * @author Nicol Luis Yumang
 */
public class Player {
    /**
     * The TileRack of a player
     * containing all the tiles available to the player
     */
    private final TileRack rack;

    /**
     * The number of consecutive times a player has passed
     */
    private int consecutivePass;

    /**
     * The score of the player
     */
    private int score;

    /**
     * Constructor: Creates a new player
     */
    public Player() {
        this.score = 0;
        this.consecutivePass = 0;
        this.rack = new TileRack();
    }

    /**
     * Subtracts a point value from the score
     * @param points the value to subtract
     */
    public void decreaseScore(int points) { this.score -= points; }

    /**
     * Gets the consecutivePass value
     * @return the consecutivePass value
     */
    public int getConsecutivePass() {
        return this.consecutivePass;
    }

    /**
     * Gets the tile rack of the player
     * @return the tile rack of the player
     */
    public TileRack getRack() {
        return this.rack;
    }

    /**
     * Gets the score of the player
     * @return the score of the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Adds a point value to the score
     * @param points the value to add
     */
    public void increaseScore(int points) {
        this.score += points;
    }

    /**
     * Increments the consecutivePass field by 1
     */
    public void incrementConsecutivePass(){
        this.consecutivePass++;
    }

    /**
     * Resets the consectuive pass field to 0
     */
    public void resetConsecutivePass() {
        this.consecutivePass = 0;
    }
}
