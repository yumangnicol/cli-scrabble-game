package pij.main;

import pij.main.player.HumanPlayer;
import pij.main.player.Player;
import pij.main.utils.Constants;
import pij.main.utils.MoveValidator;

public class Game {
    private ScrabbleBoard gameBoard;
    private final WordList wordList;
    private final TileBag bag;
    private final Player human;
    private final Player computer;
    private boolean isFirstTurn = true;

    public Game() {
        this.wordList = new WordList(Constants.DEFAULT_WORD_LIST_FILE);
        this.bag = new TileBag();

        this.human = new HumanPlayer();
        this.computer = new HumanPlayer();

        initializePlayerRacks();
        initializeGameBoard();
        startGame();
    }

    private void initializePlayerRacks(){
        this.human.getRack().refill(this.bag, 7);
        this.computer.getRack().refill(this.bag, 7);
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
                this.loadBoardFile(Constants.DEFAULT_BOARD_FILE);
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
                if(MoveValidator.validateMove(move, this.human.getRack(), this.gameBoard, this.wordList, isFirstTurn)){
                    System.out.println("POINTS: " + PointCalculator.calculatePoints(move, this.gameBoard));
                    this.gameBoard.placeTiles(move);
                    this.gameBoard.print();

                    this.human.getRack().removeTiles(move.getTiles());
                    if(!this.human.getRack().refill(this.bag, move.getTiles().size())){
                        System.out.println("Bag empty!");
                    }

                    validMove = true;
                    this.isFirstTurn = false;

                }
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }
    }
}
