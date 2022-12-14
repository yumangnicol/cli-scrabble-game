package pij.main;

import java.io.FileNotFoundException;

public class Gameplay {
    private final String DEFAULT_BOARD_FILENAME = "../../../resources/defaultBoard.txt";
    private ScrabbleBoard gameBoard;
    public Gameplay() {

    }

    public void initializeBoard() {
        String choice;
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");

        do {
            System.out.print("Please enter your choice (l/d): ");
            choice = System.console().readLine();
        } while(!(choice.equals("l") || choice.equals("d")));

        if (choice.equals("d")) {
            try {
                BoardFile boardFile = new BoardFile(this.DEFAULT_BOARD_FILENAME);
                this.gameBoard = boardFile.toScrabbleBoard();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        } else if (choice.equals("l")) {
            System.out.print("Please enter the file name of the board: ");

            boolean fileAccepted = false;
            while(!fileAccepted) {
                System.out.print("Please enter the file name of the board: ");
                String filename = System.console().readLine();
                try {
                    BoardFile boardFile = new BoardFile(filename);
                    this.gameBoard = boardFile.toScrabbleBoard();
                    fileAccepted = true;
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        gameBoard.print();
    }

}
