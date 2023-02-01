package pij.main;

public class Game {
    private ScrabbleBoard gameBoard;
    private ScrabblePlayer human;
    private ScrabblePlayer computer;

    private LetterBag bag;
    private boolean isFirstTurn = true;

    public Game() {
        this.human = new HumanScrabblePlayer();
        this.computer = new HumanScrabblePlayer();
        this.bag = new LetterBag();
        this.human.getRack().refill(this.bag, 7);
        this.computer.getRack().refill(this.bag, 7);
        initializeGameBoard();
        startGame();
    }
    private void initializeGameBoard() {
        String choice;
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");

        do {
            System.out.print("Please enter your choice (l/d): ");
            choice = System.console().readLine();
        } while(!(choice.equals("l") || choice.equals("d")));

        switch (choice){
            case("d"):
                final String DEFAULT_BOARD_FILENAME = "./resources/defaultBoard.txt";
                this.loadBoardFile(DEFAULT_BOARD_FILENAME);
                break;
            case("l"):
                boolean fileAccepted = false;
                while(!fileAccepted) {
                    System.out.print("Please enter the file name of the board: ");
                    String filename = System.console().readLine();
                    fileAccepted = this.loadBoardFile(filename);
                }
                break;
            default:
                break;
        }
        gameBoard.print();
    }

    private boolean loadBoardFile(String filename){
        try {
            BoardFileReader boardFileReader = new BoardFileReader();
            this.gameBoard = boardFileReader.toScrabbleBoard(filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void startGame(){
        boolean gameEnd = false;

        while(!gameEnd){
            humanTurn();
        }
    }

    public void humanTurn(){
        String moveString;
        Move move;
        boolean validMove = false;

        while(!validMove){
            System.out.println("It's your turn! Your tiles:");
            this.human.getRack().print();
            moveString = System.console().readLine();

            try{
                new Move(moveString);
                move = new Move(moveString);

                // Separate this later
                if(MoveValidator.validateMove(move, this.human, this.gameBoard, isFirstTurn)){
                    this.gameBoard.placeLetters(move);
                    this.gameBoard.print();
                    validMove = true;
                    this.isFirstTurn = false;
                };
            } catch (Exception e){
                System.out.println(e.getMessage());
            }


        }
    }
}
