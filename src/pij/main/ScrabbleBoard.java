package pij.main;

import java.io.*;
import java.nio.file.Path;
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

    private ArrayList<String> readBoardFile(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        File defaultBoard = new File(filename);
        try(BufferedReader reader = new BufferedReader(new FileReader(defaultBoard))){
            StringBuilder premium = new StringBuilder();
            int value;

            setSize(Integer.parseInt(reader.readLine()));
            setBoardMatrix();

            while((value = reader.read()) != -1){
                if((char) value == '.'){
                    lines.add(".");
                } else if((char) value != '\n' && (char) value != '\r' && premium.length() < 2) {
                    premium.append((char) value);
                } else if((char) value != '\n' && (char) value != '\r' && premium.length() == 2) {
                    premium.append((char) value);
                    lines.add(premium.toString());
                    premium = new StringBuilder();
                }
            }
        } catch (FileNotFoundException ex){
            System.out.println("File does not exist"); //FIX THIS!❗️
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return lines;
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
}

