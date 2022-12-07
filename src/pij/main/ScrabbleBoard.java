package pij.main;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ScrabbleBoard {

    private int size;
    private String[][] board;

    public ScrabbleBoard() {
        createBoard();
    }

    private void setBoardSize(int size){
        this.size = size;
        this.board = new String[size+1][size+1];
    }

    private ArrayList<String> readBoardFile(){
        ArrayList<String> lines = new ArrayList<>();

        File defaultBoard = new File("./resources/defaultBoard.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(defaultBoard))){
            StringBuilder premium = new StringBuilder();
            int value;

            setBoardSize(Integer.parseInt(reader.readLine()));

            while((value = reader.read()) != -1){
                if((char) value == '.'){
                    lines.add(".");
                } else if((char) value == '}' || (char) value == ')'){
                    premium.append((char) value);
                    lines.add(premium.toString());
                    premium = new StringBuilder();
                } else if((char) value != '\n' && (char) value != '\r') {
                    premium.append((char) value);
                }
            }
        } catch (FileNotFoundException ex){
            System.out.println("File does not exist"); //FIX THIS!❗️
        } catch (IOException ex){
            ex.printStackTrace();
        }

        return lines;
    }

    private void createBoard(){
        ArrayList<String> boardFile = this.readBoardFile();
        System.out.print(boardFile.toString());
        int iterator = 0;

        System.out.println(this.size);
        System.out.println(boardFile.size());

        for(int i = 0; i <= this.size; i++){
            if(i == 0){
                this.board[0][i] = " ";
            } else {
                char c = (char) (i + 96);
                this.board[0][i] = "" + c;
            }
        }

        for(int i = 1; i <= this.size; i++){
            this.board[i][0] = "" + i;
            for(int j = 1; j <= this.size; j++){
                this.board[i][j] = boardFile.get(iterator);
                iterator++;
            }
        }
    }


}

