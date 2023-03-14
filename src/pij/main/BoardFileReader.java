package pij.main;

import pij.main.utils.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A reader that reads and translates files to ScrabbleBoard objects
 * @author Nicol Luis Yumang
 */
public class BoardFileReader {
    /**
     * Checks if number provided in the file is between 12 and 26
     * @param strSize the string provided in the file
     * @return true if valid, false otherwise
     */
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
        return size >= Constants.MIN_BOARD_SIZE && size <= Constants.MAX_BOARD_SIZE;
    }

    /**
     * Checks if character is a valid to be translated in the board
     * Valid characters are spaces, ., (, ), {, }, -, 0-9
     * @param value integer value of a character in the ASCII table
     * @return true if valid, false otherwise
     */
    private boolean isValidChar(int value){
        char c = (char) value;
        return c == '\n' || c == '.' || c == '(' || c == ')' || c == '{' || c == '}' || c == '-' ||
                (value >= Constants.CHAR_NUMBER_0_INT_VALUE && value <= Constants.CHAR_NUMBER_9_INT_VALUE);
    }

    /**
     * Reads a file and validates its format
     * @param filePath the path to the file to be read
     * @return a list where each element represents a valid square in a scrabble board
     * @throws IllegalArgumentException if the format is not valid
     */
    private List<String> readAndValidate(String filePath) throws IllegalArgumentException {
        File file = new File(filePath);
        List<String> boardSquares = new ArrayList<>();
        StringBuilder premiumSquare = new StringBuilder();
        int fileChar;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            // Checks if first line is a valid integer from 12-26
            String strBoardSize = reader.readLine();
            if(!isValidBoardSize(strBoardSize)){
                throw new IllegalArgumentException("File not accepted. First line of the file should be an integer from 12-26.");
            }
            boardSquares.add(strBoardSize);

            // Goes through each character in the file
            while((fileChar = reader.read()) != -1){

                // Checks each character if value is valid
                if (!this.isValidChar(fileChar)) {
                    throw new IllegalArgumentException("File not accepted. File contains illegal characters. ");
                }

                if((char) fileChar == '.'){
                    boardSquares.add(".");
                } else if (premiumSquare.length() < 2 && (char) fileChar != '\n'){
                    premiumSquare.append((char) fileChar);
                } else if (premiumSquare.length() == 2 && (char) fileChar != '\n'){
                    // Checks format of premium squares
                    premiumSquare.append((char) fileChar);
                    if(!premiumSquare.toString().matches(Constants.PREMIUM_SQUARE_REGEX_FORMAT)){
                        throw new IllegalArgumentException("File not accepted. File contains illegal premium format. ");
                    }
                    boardSquares.add(premiumSquare.toString());
                    premiumSquare = new StringBuilder();
                }
            }
            // Checks if boardSize matches number of items in List
            if(Integer.parseInt(boardSquares.get(0)) * Integer.parseInt(boardSquares.get(0)) != boardSquares.size()-1){
                throw new IllegalArgumentException("File not accepted. Board size and items do not match! ");
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Board file not found!");
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return boardSquares;
    }

    /**
     * Reads a file and validates its format, then translates this to a scrabbleBoard object
     * @param filePath the path to the file to be read
     * @return new ScrabbleBoard object
     */
    public ScrabbleBoard toScrabbleBoard(String filePath) {
        List<String> boardElements = readAndValidate(filePath);

        int boardSize = Integer.parseInt(boardElements.get(0));
        String[][] boardMatrix = new String[boardSize + 1][boardSize + 1];

        int iterator = 0;

        // Adds the letters on the first row of the board
        for(int i = 0; i <= boardSize; i++){
            if(i == 0){
                boardMatrix[0][i] = " ";
            } else {
                char c = (char) (i + Constants.CHAR_INT_VALUE_BEFORE_SMALL_LETTER_A);
                boardMatrix[0][i] = "" + c;
            }
        }

        // Inputs boardElements to the boardMatrix
        for(int i = 1; i <= boardSize; i++){
            // Adds the row numbers on the 0 position of the rows
            boardMatrix[i][0] = "" + i;
            for(int j = 1; j <= boardSize; j++){
                boardMatrix[i][j] = boardElements.get(iterator + 1);
                iterator++;
            }
        }
        return new ScrabbleBoard(boardMatrix);
    }
}
