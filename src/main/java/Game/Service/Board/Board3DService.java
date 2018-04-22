package Game.Service.Board;

import Game.Model.Board.GameBoard;
import Game.Model.Board.Board3D;
import Game.Model.Cell;
import Game.Model.Coordinate.Coordinate;

public class Board3DService implements GameBoardService {

    @Override
    public boolean checkForWinner(GameBoard gameBoard, Coordinate coordinate) {
        Cell[][][] boardContent = ((Board3D)gameBoard).getContent();
        int boardSize = gameBoard.getSize();

        int x = coordinate.getX();
        int y = coordinate.getY();
        int z = coordinate.getZ();

        return hasLineFilledHorizontally(boardContent, boardSize, x, y, z)
                || hasLineFilledVertically_YDirection(boardContent, boardSize, x, y, z)
                || hasLineFilledVertically_ZDirection(boardContent, boardSize, x, y, z);
    }

    private boolean hasLineFilledHorizontally(Cell[][][] boardContent, int size, int x, int y, int z){
        for (int i=0; i < size; i++){
            if (!boardContent[z][x][i].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically_YDirection(Cell[][][] boardContent, int size, int x, int y, int z){
        for (int i=0; i < size; i++){
            if (!boardContent[z][i][y].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically_ZDirection(Cell[][][] boardContent, int size, int x, int y, int z){
        for (int i=0; i < size; i++){
            if (!boardContent[i][x][y].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledDiagonally3D(Cell[][][] boardContent, int size, int x, int y, int z){
        if (x==y && y==z){

        }
        for (int i=0; i < size; i++){
            if (!boardContent[i][x][y].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    @Override
    public GameBoard convertStringToBoard(String boardString) {
        return null;
    }
}
