package Game.Model.Board;

import Game.Model.Cell.Cell2D;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate2D;

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

    private boolean hasLineFilledHorizontally(int x, int y){
        for (int col = 0; col < boardSize; col++){
            if (!(boardContent[x][col].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically(int x, int y){
        for (int row = 0; row < boardSize; row++){
            if (!(boardContent[row][y].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledDiagonally(int x, int y){
        return hasDiagonalLineFilled_NegativeSlope(x,y) || hasDiagonalLineFilled_PositiveSlope(x,y);
    }

    private boolean hasDiagonalLineFilled_NegativeSlope(int x, int y){
        if (x!=y){
            return false;
        }

        int i = 0;
        while (i<boardSize){
            if (!(boardContent[i][i].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean hasDiagonalLineFilled_PositiveSlope(int x, int y){
        if (x+y != boardSize-1){
            return false;
        }

        for (int i=0; i<boardSize; i++){
            if (!(boardContent[boardSize-1-i][i].getToken().equals(boardContent[x][y].getToken()))){
                return false;
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

    public Cell2D[][] getBoardContent() {
        return boardContent;
    }
}
