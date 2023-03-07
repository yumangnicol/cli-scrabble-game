package pij.main;

import pij.main.utils.Constants;

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
        this.human = new Player();
        this.computer = new Player();

        this.human.getRack().refill(this.bag, Constants.MAX_RACK_SIZE);
        this.computer.getRack().refill(this.bag, Constants.MAX_RACK_SIZE);
    }

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

    private boolean loadBoardFile(String filename){
        try {
            BoardFileReader boardFileReader = new BoardFileReader();
            this.gameBoard = boardFileReader.toScrabbleBoard(filename);
        } catch (Exception e) {
            System.out.print("This is not a valid file. ");
            return false;
        }
        return true;
    }

    public void startGame(){
        initializeGameBoard();

        while(true){
            humanTurn();
            if(isGameEnd()) {
                break;
            }
            computerTurn();
            if(isGameEnd()) {
                break;
            }
        }

        printResults();
    }

    private boolean isGameEnd(){
        return (human.getConsecutivePass() >= 2 && computer.getConsecutivePass() >= 2) || (bag.size() == 0 && (human.getRack().size() == 0 || computer.getRack().size() == 0));
    }

    private void printResults(){
        subtractRemainingTiles();
        System.out.println("Remaining tiles have been deducted");

        System.out.println("\nGame Over!\n");

        System.out.println("The human player scored: " + human.getScore() + " points.");
        System.out.println("The computer player scored: " + computer.getScore() + " points.");

        if (human.getScore() > computer.getScore()){
            System.out.println("\nThe human player wins!");
        } else if (human.getScore() < computer.getScore()){
            System.out.println("\nThe computer player wins!");
        } else if (human.getScore() == computer.getScore()) {
            System.out.println("\nIt's a draw!");
        }
    }

    public void humanTurn(){
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
                human.incrementConsecutivePass();
                printPassSummary();
                return;
            }

            try{
                new Move(moveString);
                move = new Move(moveString);

                if(MoveValidator.validateMove(move, this.human.getRack(), this.gameBoard, this.wordList, isFirstTurn)){

                    human.increaseScore(PointCalculator.calculatePoints(move, this.gameBoard));
                    printTurnSummary(move);

                    this.gameBoard.placeTiles(move);
                    this.gameBoard.print();

                    playerRemoveAndRefillTiles(this.human, move);

                    human.resetConsecutivePass();
                    validMove = true;
                    this.isFirstTurn = false;
                }
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void computerTurn(){
        System.out.println("Computer Turn!");

        Move move = isFirstTurn ?
                MoveGenerator.makeFirstMove(computer.getRack(), gameBoard, wordList) :
                MoveGenerator.scanBoardForMove(computer.getRack(), gameBoard, wordList);

        if(move == null){
            computer.incrementConsecutivePass();
            printPassSummary();
            return;
        }

        computer.increaseScore(PointCalculator.calculatePoints(move, this.gameBoard));
        printTurnSummary(move);

        gameBoard.placeTiles(move);
        gameBoard.print();

        playerRemoveAndRefillTiles(this.computer, move);
        computer.resetConsecutivePass();
        this.isFirstTurn = false;
    }

    private void printScores(){
        System.out.println("The result is:");
        System.out.println("Human player score: " + human.getScore());
        System.out.println("Computer player score: " + computer.getScore());
    }

    private void playerRemoveAndRefillTiles(Player player, Move move){
        player.getRack().removeTiles(move.getTiles());
        if(!player.getRack().refill(this.bag, move.getTiles().size())){
            System.out.println("No more tiles to refill. The bag is empty");
        }
    }

    private void printPassSummary(){
        System.out.println(Constants.DIVIDER);
        System.out.println("Player Passed!");
        printScores();
        System.out.println(Constants.DIVIDER + "\n");
    }

    private void printTurnSummary(Move move){
        System.out.println(Constants.DIVIDER);
        System.out.println(move);
        printScores();
        System.out.println(Constants.DIVIDER + "\n");
    }

    private void subtractRemainingTiles() {
        for (Tile t : human.getRack().getTiles()) {
            human.decreaseScore(t.getValue());
        }

        for (Tile t : computer.getRack().getTiles()) {
            computer.decreaseScore(t.getValue());
        }
    }
}
