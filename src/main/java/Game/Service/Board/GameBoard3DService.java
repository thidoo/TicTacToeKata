package Game.Service.Board;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard3D;
import Game.Model.Cell.Cell3D;
import Game.Model.Coordinate.Coordinate;

public class GameBoard3DService implements GameBoardService {

    @Override
    public boolean checkHasFilledLine(GameBoard gameBoard, Coordinate coordinate) {
        Cell3D[][][] boardContent = ((GameBoard3D)gameBoard).getBoardContent();
        int boardSize = gameBoard.getBoardSize();

        int x = coordinate.getX();
        int y = coordinate.getY();
        int z = coordinate.getZ();

        return hasLineFilledHorizontally(boardContent, boardSize, x, y, z)
                || hasLineFilledVertically_YDirection(boardContent, boardSize, x, y, z)
                || hasLineFilledVertically_ZDirection(boardContent, boardSize, x, y, z);
    }

    private boolean hasLineFilledHorizontally(Cell3D[][][] boardContent, int size, int x, int y, int z){
        for (int i=0; i < size; i++){
            if (!boardContent[z][x][i].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically_YDirection(Cell3D[][][] boardContent, int size, int x, int y, int z){
        for (int i=0; i < size; i++){
            if (!boardContent[z][i][y].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically_ZDirection(Cell3D[][][] boardContent, int size, int x, int y, int z){
        for (int i=0; i < size; i++){
            if (!boardContent[i][x][y].getToken().equals(boardContent[z][x][y].getToken())){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledDiagonally3D(Cell3D[][][] boardContent, int size, int x, int y, int z){
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
    public GameBoard convertFromBoardStringtoBoard(String boardString) {
        return null;
    }
}
