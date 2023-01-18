package pij.main;

public class Game {
    private ScrabbleBoard gameBoard;
    private Player human;
    private Player computer;

    public Game() {
        this.human = new Player();
        this.computer = new Player();
    }

    public void initializeGameBoard() {
        String choice;
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");

        do {
            System.out.print("Please enter your choice (l/d): ");
            choice = System.console().readLine();
        } while(!(choice.equals("l") || choice.equals("d")));

        switch (choice){
            case("d"):
                final String DEFAULT_BOARD_FILENAME = "../../../resources/defaultBoard.txt";
                this.loadBoardFile(DEFAULT_BOARD_FILENAME);
                break;
            case("l"):
                System.out.print("Please enter the file name of the board: ");
                boolean fileAccepted = false;
                while(!fileAccepted) {
                    System.out.print("Please enter the file name of the board: ");
                    String filename = System.console().readLine();
                    this.loadBoardFile(filename);
                }
                break;
            default:
                break;
        }

        gameBoard.print();
    }

    private void loadBoardFile(String filename){
        try {
            BoardFileReader boardFileReader = new BoardFileReader();
            this.gameBoard = boardFileReader.toScrabbleBoard(filename);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
