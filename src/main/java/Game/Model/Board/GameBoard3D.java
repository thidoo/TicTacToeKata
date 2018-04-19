package Game.Model.Board;

import Game.Model.Cell.Cell3D;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate3D;

public class GameBoard3D implements GameBoard {

    private Cell3D[][][] boardContent;
    private int boardSize;

    public GameBoard3D(int size) {
        this.boardSize = size;
        initialiseEmptyBoard(size);
    }

    private void initialiseEmptyBoard(int size) {
        this.boardContent = new Cell3D[size][size][size];
        for (int z = 0; z < size; z++){
            for (int x = 0; x < size; x++){
                for (int y = 0; y < size; y++){
                    boardContent[z][x][y] = new Cell3D(new Coordinate3D(x, y, z));
                }
            }
        }
    }

    @Override
    public boolean isEmptyAtPosition(Coordinate coordinate) {
        return boardContent[coordinate.getZ()][coordinate.getX()][coordinate.getY()].isEmpty();
    }

    @Override
    public boolean isFull() {
        for (int z = 0; z < boardSize; z++){
            for (int x = 0; x < boardSize; x++){
                for (int y = 0; y < boardSize; y++){
                    if (boardContent[z][x][y].isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void updateCell(String token, Coordinate coordinate) {
        boardContent[coordinate.getZ()][coordinate.getX()][coordinate.getY()].setToken(token);
        boardContent[coordinate.getZ()][coordinate.getX()][coordinate.getY()].setIsEmpty(false);
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return (coordinate.getX() >= 0 && coordinate.getX() < boardSize)
                && (coordinate.getY() >= 0 && coordinate.getY() < boardSize)
                && (coordinate.getZ() >=0 && coordinate.getZ() < boardSize);
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    public Cell3D[][][] getBoardContent() {
        return boardContent;
    }

    @Override
    public void printBoard() {
        for (int z = 0; z < boardSize; z++){
            for (int x = 0; x < boardSize; x++){
                for (int y = 0; y < boardSize; y++){
                    System.out.print(boardContent[z][x][y].getToken() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int z = 0; z < boardSize; z++){
            for (int x = 0; x < boardSize; x++){
                for (int y = 0; y < boardSize; y++){
                    stringBuilder.append(boardContent[z][x][y].getToken() + " ");
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
