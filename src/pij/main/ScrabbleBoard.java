package pij.main;

import java.io.*;
import java.util.ArrayList;

public class ScrabbleBoard {

    private String boardFile;
    private String[][] boardMatrix;
    private int size;

    public ScrabbleBoard(String filename) {
        this.createBoard(filename);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBoardMatrix() {
        this.boardMatrix = new String[size+1][size+1];
    }
    private void createBoard(String filename){
        ArrayList<String> boardFile = this.readBoardFile(filename);
        System.out.println(boardFile.toString());
        int iterator = 0;

        System.out.println(this.size);
        System.out.println(boardFile.size());

        for(int i = 0; i <= this.size; i++){
            if(i == 0){
                this.boardMatrix[0][i] = " ";
            } else {
                char c = (char) (i + 96);
                this.boardMatrix[0][i] = "" + c;
            }
        }

        for(int i = 1; i <= this.size; i++){
            this.boardMatrix[i][0] = "" + i;
            for(int j = 1; j <= this.size; j++){
                this.boardMatrix[i][j] = boardFile.get(iterator);
                iterator++;
            }
        }
    }

    public void print(){
        for(String[] row : boardMatrix){
            for(String s : row){
                if(s.length() == 1){
                    System.out.print(" " + s + " ");
                } else {
                    System.out.print(s);
                }
            }
            System.out.println("");
        }
    }

    private ArrayList<String> readBoardFile(String filename) throws IllegalArgumentException {
        ArrayList<String> boardSquares = new ArrayList<>();
        StringBuilder premiumSquare = new StringBuilder();

        int fileChar;

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            setSize(Integer.parseInt(reader.readLine()));
            setBoardMatrix();

            while((fileChar = reader.read()) != -1){
                if (!this.isValidChar(fileChar)) {
                    throw new IllegalArgumentException("File not accepted. File contains illegal characters.");
                } else {
                    if((char) fileChar == '.'){
                        boardSquares.add(".");
                    } else if (premiumSquare.length() < 2 && (char) fileChar != '\n'){
                        premiumSquare.append((char) fileChar);
                    } else if (premiumSquare.length() == 2 && (char) fileChar != '\n'){
                        premiumSquare.append((char) fileChar);
                        if(!premiumSquare.toString().matches("\\([0-9]{2}|\\{[0-9]{2}|\\([0-9]\\)|\\{[0-9]\\}")){
                            throw new IllegalArgumentException("File not accepted. File contains illegal format");
                        } else {
                            boardSquares.add(premiumSquare.toString());
                            premiumSquare = new StringBuilder();
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex){
            System.out.println("File does not exist");
        } catch (NumberFormatException ex){
            System.out.println("File should contain a valid integer on the first line");
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return boardSquares;
    }

    private boolean isValidChar(int value){
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

