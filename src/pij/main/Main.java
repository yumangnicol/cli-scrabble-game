package pij.main;

import javax.sound.midi.SysexMessage;

public class Main {
    private String DEFAULT_BOARD_FILENAME = "../../../resources/defaultBoard.txt";

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void start(){
        ScrabbleBoard gameBoard;
        gameBoard = initializeBoard();
        gameBoard.print();
    }

    private ScrabbleBoard initializeBoard(){
        ScrabbleBoard gameBoard;
        String choice;

        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        do {
            System.out.print("Please enter your choice (l/d): ");
            choice = System.console().readLine();
        } while(!(choice.equals("l") || choice.equals("d")));


        switch (choice){
            case "d":
                gameBoard = new ScrabbleBoard(DEFAULT_BOARD_FILENAME);
                break;
            case "l":
                System.out.print("Please enter the file name of the board: ");
                String filename = System.console().readLine();
                gameBoard = new ScrabbleBoard(filename);
                break;
            default:
                gameBoard = new ScrabbleBoard(DEFAULT_BOARD_FILENAME);
                break;
        }

        return gameBoard;
    }
}

