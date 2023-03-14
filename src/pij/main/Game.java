package pij.main;

import pij.main.utils.Constants;

/**
 * Represents a game in scrabble
 * @author Nicol Luis Yumang
 */
public class Game {

    /**
     * The board in a scrabble game
     */
    private ScrabbleBoard gameBoard;

    /**
     * The valid word list in a scrabble game
     */
    private final WordList wordList;

    /**
     * The tile bag in a scrabble game
     */
    private final TileBag bag;

    /**
     * A human player playing the scrabble game
     */
    private final Player human;

    /**
     * A computer playing the scrabble game
     */
    private final Player computer;

    /**
     * The state of the game
     * True if the first move has yet to be played, false otherwise
     */
    private boolean isFirstTurn = true;

    /**
     * Constructor: Creates a new game
     */
    public Game() {
        this.wordList = new WordList(Constants.DEFAULT_WORD_LIST_FILE);
        this.bag = new TileBag();
        this.human = new Player();
        this.computer = new Player();
        this.human.getRack().refill(this.bag, Constants.MAX_RACK_SIZE);
        this.computer.getRack().refill(this.bag, Constants.MAX_RACK_SIZE);
    }

    /**
     * The computer plays a move on the game
     */
    private void computerTurn(){
        System.out.println("Computer Turn!");

        Move move = this.isFirstTurn ?
                MoveGenerator.generateFirstMove(this.computer.getRack(), this.gameBoard, this.wordList) :
                MoveGenerator.generateSucceedingMove(this.computer.getRack(), this.gameBoard, this.wordList);

        if(move == null){
            this.computer.incrementConsecutivePass();
            this.printPassSummary();
            return;
        }

        this.computer.increaseScore(PointCalculator.calculatePoints(move, this.gameBoard));
        this.printTurnSummary(move);

        this.gameBoard.placeTiles(move);
        this.gameBoard.print();

        this.playerRemoveAndRefillTiles(this.computer, move);
        this.computer.resetConsecutivePass();
        this.isFirstTurn = false;
    }

    /**
     * The human player is asked to play a move on the game
     */
    private void humanTurn(){
        String moveString;
        Move move;
        boolean validMove = false;

        while(!validMove){
            System.out.println("It's your turn! Your tiles:");
            this.human.getRack().print();

            System.out.println("\nPlease enter your move with letter sequence, position, and direction");
            System.out.println("(d for down, r for right) separated by commas. Entering just two commas passes.");

            moveString = System.console().readLine();

            // Check if player move is a Pass
            if(moveString.equals(",,")){
                this.human.incrementConsecutivePass();
                this.printPassSummary();
                return;
            }

            try{
                new Move(moveString);
                move = new Move(moveString);

                if(MoveValidator.validateMove(move, this.human.getRack(), this.gameBoard, this.wordList, this.isFirstTurn)){

                    this.human.increaseScore(PointCalculator.calculatePoints(move, this.gameBoard));
                    this.printTurnSummary(move);

                    this.gameBoard.placeTiles(move);
                    this.gameBoard.print();

                    this.playerRemoveAndRefillTiles(this.human, move);

                    this.human.resetConsecutivePass();
                    validMove = true;
                    this.isFirstTurn = false;
                }
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Initialize game board by asking the player which board file to use for the game
     */
    private void initializeGameBoard() {
        String choice;
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");

        do {
            System.out.print("Please enter your choice (l/d): ");
            choice = System.console().readLine();
        } while(!(choice.equals("l") || choice.equals("d")));

        switch (choice) {
            case ("d") -> this.loadBoardFile(Constants.DEFAULT_BOARD_FILE);
            case ("l") -> {
                boolean fileAccepted = false;
                while (!fileAccepted) {
                    System.out.print("Please enter the file name of the board: ");
                    String filename = System.console().readLine();
                    fileAccepted = this.loadBoardFile(filename);
                }
            }
        }
        gameBoard.print();
    }

    /**
     * Checks if the game should end because it satisfies an end-game condition
     * @return true if game should end, false otherwise
     */
    private boolean isGameEnd(){
        return (this.human.getConsecutivePass() >= 2 && this.computer.getConsecutivePass() >= 2) ||
                (this.bag.size() == 0 && (this.human.getRack().size() == 0 || this.computer.getRack().size() == 0));
    }

    /**
     * Loads a board into the game from a given file
     * @param filePath the path to the file to be read
     * @return true if load is successful, false otherwise
     */
    private boolean loadBoardFile(String filePath){
        try {
            BoardFileReader boardFileReader = new BoardFileReader();
            this.gameBoard = boardFileReader.toScrabbleBoard(filePath);
        } catch (Exception e) {
            System.out.print("This is not a valid file. ");
            return false;
        }
        return true;
    }

    /**
     * Starts the game and is played until game ends
     */
    public void startGame(){
        initializeGameBoard();

        while(true){
            this.humanTurn();
            if(this.isGameEnd()) {
                break;
            }
            this.computerTurn();
            if(this.isGameEnd()) {
                break;
            }
        }

        this.printResults();
    }

    /**
     * Removes the used tiles from a players rack based on the move and refills it with new tiles from the bag
     * @param player the player who made the move
     * @param move the move made by a player
     */
    private void playerRemoveAndRefillTiles(Player player, Move move){
        player.getRack().removeAll(move.getTiles());
        player.getRack().refill(this.bag, move.getTiles().size());
    }

    /**
     * Prints a summary of player's turn when the move is a pass
     */
    private void printPassSummary(){
        System.out.println(Constants.DIVIDER);
        System.out.println("Player Passed!");
        this.printScores();
        System.out.println(Constants.DIVIDER + "\n");
    }

    /**
     * Prints the end game results of the scrabble game
     */
    private void printResults(){
        this.subtractRemainingTiles();
        System.out.println("Remaining tiles have been deducted");

        System.out.println("\nGame Over!\n");

        System.out.println("The human player scored: " + this.human.getScore() + " points.");
        System.out.println("The computer player scored: " + this.computer.getScore() + " points.");

        if (this.human.getScore() > this.computer.getScore()){
            System.out.println("\nThe human player wins!");
        } else if (this.human.getScore() < this.computer.getScore()){
            System.out.println("\nThe computer player wins!");
        } else if (this.human.getScore() == this.computer.getScore()) {
            System.out.println("\nIt's a draw!");
        }
    }

    /**
     * Prints the scores of both human and computer
     */
    private void printScores(){
        System.out.println("The result is:");
        System.out.println("Human player score: " + human.getScore());
        System.out.println("Computer player score: " + computer.getScore());
    }

    /**
     * Prints the summary of a player's turn including the move and the scores after the turn
     * @param move the move made by a player
     */
    private void printTurnSummary(Move move){
        System.out.println(Constants.DIVIDER);
        System.out.println(move);
        this.printScores();
        System.out.println(Constants.DIVIDER + "\n");
    }

    /**
     * Subtracts the value of the remaining tiles on each player's rack to their score
     */
    private void subtractRemainingTiles() {
        for (Tile t : this.human.getRack().getTiles()) {
            this.human.decreaseScore(t.getValue());
        }

        for (Tile t : this.computer.getRack().getTiles()) {
            this.computer.decreaseScore(t.getValue());
        }
    }
}
