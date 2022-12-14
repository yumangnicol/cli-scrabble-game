package pij.main;

public class Main {
    private String DEFAULT_BOARD_FILENAME = "../../../resources/defaultBoard.txt";
    private String DEFAULT_BOARD_FILENAME2 = "./resources/defaultBoard.txt";

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void start(){
        ScrabbleBoard gameBoard;
        gameBoard = initializeBoard();
        gameBoard.print();


        // MANUAL TESTING
//        ScrabbleBoard gameBoard;
//        BoardFile boardFile;
//        boardFile = new BoardFile(DEFAULT_BOARD_FILENAME2);
//        gameBoard = boardFile.toScrabbleBoard();
//        gameBoard.print();
    }

    private ScrabbleBoard initializeBoard(){
        ScrabbleBoard gameBoard;
        BoardFile boardFile;
        String choice;

        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        do {
            System.out.print("Please enter your choice (l/d): ");
            choice = System.console().readLine();
        } while(!(choice.equals("l") || choice.equals("d")));

        switch (choice){
            case "d":
                boardFile = new BoardFile(DEFAULT_BOARD_FILENAME);
                gameBoard = boardFile.toScrabbleBoard();
                break;
            case "l":
                System.out.print("Please enter the file name of the board: ");
                String filename = System.console().readLine();
                boardFile = new BoardFile(filename);
                gameBoard = boardFile.toScrabbleBoard();
                break;
            default:
                boardFile = new BoardFile(DEFAULT_BOARD_FILENAME);
                gameBoard = boardFile.toScrabbleBoard();
                break;
        }

        return gameBoard;
    }
}

