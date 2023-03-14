package pij.main;

/**
 * Represents a scrabble game java program
 * @author Nicol Luis Yumang
 */
public class Main {

    public static void main(String[] args) {
        start();
    }

    /**
     * Creates a new scrabble game and starts it
     */
    private static void start(){
        Game game = new Game();
        game.startGame();
    }
}

