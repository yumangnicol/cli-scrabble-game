package pij.main;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        start();

        ScrabbleBoard board = null;
        String filepath = "./resources/defaultBoard.txt";

        try {
            BoardFile file = new BoardFile(filepath);
            board = file.toScrabbleBoard();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        board.print();

        System.out.print(board.isValidMove("Z6"));

//        LetterBag lb = new LetterBag();
//        System.out.println(lb.size());
//        lb.print();

//        System.out.println("SHUFFLE");
//        System.out.println("SHUFFLE");
//        System.out.println("SHUFFLE");

//        lb.shuffle();
//        ArrayList<Letter> rack = lb.drawLetters(7);
//        System.out.println(rack.size());
//
//        for(Letter l : rack){
//            System.out.println(l.getLetter());
//        }
//
//        System.out.println(lb.size());

    }

    private static void start(){
        Game newGame = new Game();
        newGame.initializeGameBoard();
    }
}

