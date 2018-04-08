package Board;

import Cell.Cell;
import Cell.Cell2D;
import Coordinate.Coordinate;
import Coordinate.Coordinate2D;

public class GameBoard2D implements GameBoard {

    private Cell2D[][] boardContent;
    private int boardSize;

    public GameBoard2D(int size) {
        this.boardSize = size;
        this.boardContent = new Cell2D[size][size];
        initialiseEmptyBoard(size);
    }

    @Override
    public void initialiseEmptyBoard(int size){
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                boardContent[i][j] = new Cell2D(new Coordinate2D(i,j));
            }
        }
    }

    @Override
    public boolean isEmptyAtPosition(Coordinate coordinate){
        return boardContent[coordinate.getX()][coordinate.getY()].isEmpty();
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
    public void updateCell(String token, Coordinate coordinate) {
        boardContent[coordinate.getX()][coordinate.getY()].setToken(token);
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return (coordinate.getX() >= 0 && coordinate.getX() < boardSize) && (coordinate.getY() >= 0 && coordinate.getY() < boardSize);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean hasFilledLine(Coordinate coordinate) {

        int x = coordinate.getX();
        int y = coordinate.getY();

        if (hasLineFilledHorizontally(x,y) || hasLineFilledVertically(x,y)
                || hasLineFilledDiagonally(x,y)){
            return true;
        }

        return false;
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    private boolean hasLineFilledHorizontally(int x, int y){
        for (int col = 0; col < boardSize; col++){
            if (boardContent[x][col] != boardContent[x][y]){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically(int x, int y){
        for (int row = 0; row < boardSize; row++){
            if (boardContent[row][y] != boardContent[x][y]){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledDiagonally(int x, int y){
        if (x!=y){
            return false;
        }

        int i = 0;
        while (i<boardSize){
            if (boardContent[i][i] != boardContent[x][y]){
                return false;
            }
            i++;
        }
        return true;
    }


}
