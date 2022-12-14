package pij.main;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start(){
        Gameplay newGame = new Gameplay();
        newGame.initializeBoard();
    }
}

