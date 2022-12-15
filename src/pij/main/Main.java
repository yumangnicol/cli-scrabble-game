package pij.main;

public class Main {
    public static void main(String[] args) {
//        start();
        LetterBag lb = new LetterBag();
        System.out.println(lb.size());
        lb.print();

        System.out.println("SHUFFLE");
        System.out.println("SHUFFLE");
        System.out.println("SHUFFLE");

        lb.shuffle();
        lb.print();
    }

    private static void start(){
        Game newGame = new Game();
        newGame.initializeGameBoard();
    }
}

