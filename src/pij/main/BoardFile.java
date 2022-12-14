package pij.main;

import java.io.*;
import java.util.ArrayList;

public class BoardFile {
    File boardFile;

    public BoardFile(String filename) {
        this.boardFile = new File(filename);
    }
    public ScrabbleBoard toScrabbleBoard() {
        String[][] boardMatrix;
        ArrayList<String> boardElements = validateBoardFile();

        int boardSize = Integer.parseInt(boardElements.get(0));
        boardMatrix = new String[boardSize + 1][boardSize + 1];

        int iterator = 0;

        // Adds the letters on the first row of the board
        for(int i = 0; i <= boardSize; i++){
            if(i == 0){
                boardMatrix[0][i] = " ";
            } else {
                char c = (char) (i + 96);
                boardMatrix[0][i] = "" + c;
            }
        }

        // Inputs boardElements to the boardMatrix
        for(int i = 1; i <= boardSize; i++){
            boardMatrix[i][0] = "" + i;
            for(int j = 1; j <= boardSize; j++){
                boardMatrix[i][j] = boardElements.get(iterator + 1);
                iterator++;
            }
        }
        return new ScrabbleBoard(boardMatrix);
    }
    private ArrayList<String> validateBoardFile() throws IllegalArgumentException {
        ArrayList<String> boardSquares = new ArrayList<>();
        StringBuilder premiumSquare = new StringBuilder();
        int fileChar;

        try(BufferedReader reader = new BufferedReader(new FileReader(this.boardFile))){
            // Checks if first line is a valid integer from 12-26
            String strBoardSize = reader.readLine();
            if(!isValidBoardSize(strBoardSize)){
                throw new IllegalArgumentException("File not accepted. First line of the file should be an integer from 12-26");
            }
            boardSquares.add(strBoardSize);

            // Goes through the boardFile line by to build the boardSquares ArrayList
            while((fileChar = reader.read()) != -1){
                // Checks each character if value is valid
                if (!this.isValidBoardSquare(fileChar)) {
                    throw new IllegalArgumentException("File not accepted. File contains illegal characters.");
                } else {
                    if((char) fileChar == '.'){
                        boardSquares.add(".");
                    } else if (premiumSquare.length() < 2 && (char) fileChar != '\n'){
                        premiumSquare.append((char) fileChar);
                    } else if (premiumSquare.length() == 2 && (char) fileChar != '\n'){
                        // Checks format of premium squares
                        premiumSquare.append((char) fileChar);
                        if(!premiumSquare.toString().matches("\\([0-9]{2}|\\{[0-9]{2}|\\([0-9]\\)|\\{[0-9]\\}")){
                            throw new IllegalArgumentException("File not accepted. File contains illegal format");
                        }
                        boardSquares.add(premiumSquare.toString());
                        premiumSquare = new StringBuilder();
                    }
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return boardSquares;
    }
    private boolean isValidBoardSize(String strSize){
        int size;

        if(strSize == null) {
            return false;
        }

        try {
            size = Integer.parseInt(strSize);
        } catch (NumberFormatException ex){
            return false;
        }

        if(size < 12 || size > 26){
            return false;
        }

        return true;
    }
    private boolean isValidBoardSquare(int value){
        int LINE_FEED = 10;
        int PERIOD = 46;
        int OPEN_PARENTHESIS = 40;
        int CLOSE_PARENTHESIS = 41;
        int OPEN_CURLY_BRACE = 123;
        int CLOSE_CURLY_BRACE = 125;
        int ZERO_CHARACTER = 48;
        int NINE_CHARACTER = 57;

        boolean validChar = (
                value == LINE_FEED ||
                        value == PERIOD ||
                        value == OPEN_PARENTHESIS ||
                        value == CLOSE_PARENTHESIS ||
                        value == OPEN_CURLY_BRACE ||
                        value == CLOSE_CURLY_BRACE ||
                        (value >= ZERO_CHARACTER && value <= NINE_CHARACTER));
        return validChar;
    }
}
