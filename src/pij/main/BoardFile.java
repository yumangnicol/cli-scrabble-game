package pij.main;

import java.io.*;
import java.util.ArrayList;

public class BoardFile {

    /**
     * The File that contains the board configuration. Always non-null.
     */
    File boardFile;

    /**
     * Constructs a new BoardFile with given filePath.
     *
     * @param filePath the file path of a File that represents a board.
     *
     * @throws NullPointerException if filePath is null.
     * @throws FileNotFoundException if file is not found.
     *
     * @author Nicol Luis Yumang
     */
    public BoardFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        if(!file.exists()){
            throw new FileNotFoundException("File does not exist. ");
        } else {
            this.boardFile = new File(filePath);
        }
    }

    /**
     *
     * Returns the contents of this BoardFile as a ScrabbleBoard that represents
     * a ScrabbleBoard's Rows and Columns.
     * Each character in the BoardFile represents a square in the ScrabbleBoard.
     *
     * @return a ScrabbleBoard with the contents of this BoardFile represented
     * as contents in a ScrabbleBoard's squares.
     *
     * @author Nicol Luis Yumang
     */
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

    /**
     *
     * Returns a validated ArrayList of Strings for each character in the BoardFile
     * that represents a square on a ScrabbleBoard.
     * The BoardFile is read and each element on the list is validated to check if the contents of the File
     * has the correct format.
     *
     * @return an ArrayList of Strings. Where each element in the List represents a square on a ScrabbleBoard.
     * @throws IllegalArgumentException if the BoardFile contains illegal characters. (Symbols, Spaces, Letters)
     * @throws IllegalArgumentException if the BoardFile contains illegal formatting. (Double opening/closing
     * parenthesis/curly brackets, premium square contains a number that has more than 2-digits)
     * @throws IllegalArgumentException if the Boardfile's first line is not an integer from 12-26.
     *
     * @author Nicol Luis Yumang
     */
    private ArrayList<String> validateBoardFile() throws IllegalArgumentException {
        ArrayList<String> boardSquares = new ArrayList<>();
        StringBuilder premiumSquare = new StringBuilder();
        int fileChar;

        try(BufferedReader reader = new BufferedReader(new FileReader(this.boardFile))){
            // Checks if first line is a valid integer from 12-26
            String strBoardSize = reader.readLine();
            if(!isValidBoardSize(strBoardSize)){
                throw new IllegalArgumentException("File not accepted. First line of the file should be an integer from 12-26. ");
            }
            boardSquares.add(strBoardSize);

            // Goes through the boardFile line by to build the boardSquares ArrayList
            while((fileChar = reader.read()) != -1){
                // Checks each character if value is valid
                if (!this.isValidBoardSquare(fileChar)) {
                    throw new IllegalArgumentException("File not accepted. File contains illegal characters. ");
                } else {
                    if((char) fileChar == '.'){
                        boardSquares.add(".");
                    } else if (premiumSquare.length() < 2 && (char) fileChar != '\n'){
                        premiumSquare.append((char) fileChar);
                    } else if (premiumSquare.length() == 2 && (char) fileChar != '\n'){
                        // Checks format of premium squares
                        premiumSquare.append((char) fileChar);
                        if(!premiumSquare.toString().matches("\\([0-9]{2}|\\{[0-9]{2}|\\([0-9]\\)|\\{[0-9]\\}")){
                            throw new IllegalArgumentException("File not accepted. File contains illegal format. ");
                        }
                        boardSquares.add(premiumSquare.toString());
                        premiumSquare = new StringBuilder();
                    }
                }
            }
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return boardSquares;
    }

    /**
     *
     * Returns a boolean that checks if a given String value can be converted into an integer
     * with a value between 12-26.
     *
     * @param strSize a String value that should represent an integer from 12-26.
     * @return a boolean value that checks whether the strSize parameter is a valid integer
     * from 12-26.
     *
     * @author Nicol Luis Yumang
     */
    private boolean isValidBoardSize(String strSize){
        final int MIN_SIZE = 12, MAX_SIZE = 26;
        int size;

        if(strSize == null) {
            return false;
        }

        try {
            size = Integer.parseInt(strSize);
        } catch (NumberFormatException ex){
            return false;
        }

        return size >= MIN_SIZE && size <= MAX_SIZE;
    }

    /**
     *
     * Returns a boolean that checks if a given integer value's ASCII equivalent is a valid character.
     *
     * @param value an integer that represents an ASCII character.
     * @return a boolean value that checks whether the given integer value's ASCII equivalent
     * is a valid character.
     *
     * @author Nicol Luis Yumang
     */
    private boolean isValidBoardSquare(int value){
        final int LINE_FEED = 10;
        final int PERIOD = 46;
        final int OPEN_PARENTHESIS = 40;
        final int CLOSE_PARENTHESIS = 41;
        final int OPEN_CURLY_BRACE = 123;
        final int CLOSE_CURLY_BRACE = 125;
        final int ZERO_CHARACTER = 48;
        final int NINE_CHARACTER = 57;

        return value == LINE_FEED ||
                value == PERIOD ||
                value == OPEN_PARENTHESIS ||
                value == CLOSE_PARENTHESIS ||
                value == OPEN_CURLY_BRACE ||
                value == CLOSE_CURLY_BRACE ||
                (value >= ZERO_CHARACTER && value <= NINE_CHARACTER);
    }
}
