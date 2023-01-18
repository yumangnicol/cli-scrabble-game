package pij.main;

public class Main {
    public static void main(String[] args) {
        start();

//        ScrabbleBoard board = null;
//        String filepath = "./resources/defaultBoard.txt";
//
//        try {
//            BoardFileReader boardFileReader = new BoardFileReader();
//            board = boardFileReader.toScrabbleBoard(filepath);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        board.print();

//        System.out.print(board.isValidMove("Z6"));

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

