package pij.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BoardFileReader {
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
    private boolean isValidChar(int value){
        char c = (char) value;
        final int ZERO_CHARACTER = 48, NINE_CHARACTER = 57;

        return c == '\n' || c == '.' || c == '(' || c == ')' || c == '{' || c == '}' ||
                (value >= ZERO_CHARACTER && value <= NINE_CHARACTER);
    }
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
                    if(!premiumSquare.toString().matches("\\([0-9]{2}|\\{[0-9]{2}|\\([0-9]\\)|\\{[0-9]\\}")){
                        throw new IllegalArgumentException("File not accepted. File contains illegal premium format. ");
                    }
                    boardSquares.add(premiumSquare.toString());
                    premiumSquare = new StringBuilder();
                }
            }
            // Checks if boardSize matches number of items in List
            if(!boardSquares.get(0).equals("" + boardSquares.size())){
                throw new IllegalArgumentException("File not accepted. Board size and items do not match! ");
            }
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File not found!");
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return boardSquares;
    }
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
}
