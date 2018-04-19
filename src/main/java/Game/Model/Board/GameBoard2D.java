package Game.Model.Board;

import Game.Model.Cell.Cell2D;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate2D;

import java.util.Arrays;
import java.util.Objects;

public class GameBoard2D implements GameBoard {

    private Cell2D[][] boardContent;
    private int boardSize;

    public GameBoard2D(int size) {
        this.boardSize = size;
        this.boardContent = new Cell2D[size][size];
        initialiseEmptyBoard(size);
    }

    @Override
    public void updateCell(String token, Coordinate coordinate) {
        boardContent[coordinate.getX()][coordinate.getY()].setToken(token);
        boardContent[coordinate.getX()][coordinate.getY()].setIsEmpty(false);
    }

    @Override
    public void printBoard(){
        for (int i=0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){
                System.out.print(boardContent[i][j].getToken() + ' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return (coordinate.getX() >= 0 && coordinate.getX() < boardSize)
                && (coordinate.getY() >= 0 && coordinate.getY() < boardSize);
    }

    @Override
    public boolean isEmptyAtPosition(Coordinate coordinate){
        return boardContent[coordinate.getX()][coordinate.getY()].isEmpty();
    }

    @Override
    public boolean isFull() {
        for (int i=0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){
                if (this.boardContent[i][j].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    private void initialiseEmptyBoard(int size){
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                boardContent[i][j] = new Cell2D(new Coordinate2D(i,j));
            }
        }
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    public Cell2D[][] getBoardContent() {
        return boardContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameBoard2D)) return false;
        GameBoard2D that = (GameBoard2D) o;
        return boardSize == that.boardSize &&
                Arrays.equals(getBoardContent(), that.getBoardContent());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                stringBuilder.append(boardContent[i][j].getToken() + " ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
