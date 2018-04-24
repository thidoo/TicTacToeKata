package Game.Service.Board;

import Game.Model.Board.Board2D;
import Game.Model.Board.GameBoard;
import Game.Model.Board.Board3D;
import Game.Model.Cell;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate2D;
import Game.Model.Coordinate.Coordinate3D;

public class Board3DService implements GameBoardService {

    Board2DService board2DService;
    PlaneSplitter planeSplitter;

    public Board3DService(Board2DService board2DService, PlaneSplitter planeSplitter) {
        this.board2DService = board2DService;
        this.planeSplitter = planeSplitter;
    }

    @Override
    public boolean checkForWinner(GameBoard board, Coordinate coordinate) {
        return hasLineFilledOnXYPlane(board, coordinate) ||
                hasLineFilledOnXZPlane(board, coordinate) ||
                hasLineFilledOnYZPlane(board, coordinate) ||
                hasDiagonalLineFilledAcrossPlanes(board);
    }

    private boolean hasLineFilledOnXYPlane(GameBoard board, Coordinate coordinate){
        Board2D[] XYPlanes = planeSplitter.splitXY((Board3D)board);
        return board2DService.checkForWinner(XYPlanes[coordinate.getZ()],
                new Coordinate2D(coordinate.getX(),coordinate.getY()));
    }

    private boolean hasLineFilledOnXZPlane(GameBoard board, Coordinate coordinate){
        Board2D[] XZPlanes = planeSplitter.splitXZ((Board3D)board);
        return board2DService.checkForWinner(XZPlanes[coordinate.getY()],
                new Coordinate2D(coordinate.getX(), coordinate.getZ()));
    }

    private boolean hasLineFilledOnYZPlane(GameBoard board, Coordinate coordinate){
        Board2D[] XZPlanes = planeSplitter.splitYZ((Board3D)board);
        return board2DService.checkForWinner(XZPlanes[coordinate.getX()],
                new Coordinate2D(coordinate.getZ(), coordinate.getY()));
    }

    private boolean hasDiagonalLineFilledAcrossPlanes(GameBoard board) {
        return hasDiagonalLineFilledFromTopLeftCornerOfTopXYPlaneDown(((Board3D)board).getContent())
                || hasDiagonalLineFilledFromTopRightCornerOfTopXYPlaneDown(((Board3D)board).getContent())
                || hasDiagonalLineFilledFromBottomLeftCornerOfTopXYPlaneDown(((Board3D)board).getContent())
                || hasDiagonalLineFilledFromBottomRightCornerOfTopXYPlaneDown(((Board3D)board).getContent());
    }

    private boolean hasDiagonalLineFilledFromTopLeftCornerOfTopXYPlaneDown(Cell[][][] boardContent){
        int x = 0;
        int y = 0;
        int z = 0;

        for (int i=0; i<boardContent.length-1; i++){
            if ((boardContent[z][x][y].getToken().equals(Cell.getDefaultToken())
                    || (!(boardContent[z+i][x+i][y+i].getToken()
                    .equals(boardContent[z][x][y].getToken()))))){
                return false;
            }
            x++; y++; z++;
        }

        return true;
    }

    private boolean hasDiagonalLineFilledFromTopRightCornerOfTopXYPlaneDown(Cell[][][] boardContent){
        int x = 0;
        int y = boardContent.length-1;
        int z = 0;

        for (int i=0; i<boardContent.length-1; i++){
            if ((boardContent[z][x][y].getToken().equals(Cell.getDefaultToken())
                    || (!(boardContent[z+i][x+i][y-i].getToken()
                    .equals(boardContent[z][x][y].getToken()))))){
                return false;
            }
            x++; y--; z++;
        }

        return true;
    }

    private boolean hasDiagonalLineFilledFromBottomLeftCornerOfTopXYPlaneDown(Cell[][][] boardContent){
        int x = boardContent.length-1;
        int y = 0;
        int z = 0;

        for (int i=0; i<boardContent.length-1; i++){
            if ((boardContent[z][x][y].getToken().equals(Cell.getDefaultToken())
                    || (!(boardContent[z+i][x-i][y+i].getToken()
                    .equals(boardContent[z][x][y].getToken()))))){
                return false;
            }
            x--; y++; z++;
        }

        return true;
    }

    private boolean hasDiagonalLineFilledFromBottomRightCornerOfTopXYPlaneDown(Cell[][][] boardContent){
        int x = boardContent.length-1;
        int y = boardContent.length-1;
        int z = 0;

        for (int i=0; i<boardContent.length-1; i++){
            if ((boardContent[z][x][y].getToken().equals(Cell.getDefaultToken())
                    || (!(boardContent[z+i][x-i][y-i].getToken()
                    .equals(boardContent[z][x][y].getToken()))))){
                return false;
            }
            x--; y--; z++;
        }

        return true;
    }

    @Override
    public GameBoard convertStringToBoard(String boardString) {
        String[] xyPlanes = boardString.split("\\n\\n");
        int boardSize = xyPlanes.length;
        Board3D board3D = new Board3D(boardSize);

        for (int z = 0; z < boardSize; z++){
            Board2D flatBoard = (Board2D) board2DService.convertStringToBoard(xyPlanes[z]);
            board3D = updatePlaneForBoard(board3D, flatBoard.getContent(), z, boardSize);
        }

        return board3D;
    }

    private Board3D updatePlaneForBoard(Board3D board3D, Cell[][] xyPlane, int height, int size){
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                if (!(xyPlane[x][y].getToken().equals(Cell.getDefaultToken()))){
                    board3D.updateCell(xyPlane[x][y].getToken(), new Coordinate3D(x, y, height));
                }
            }
        }
        return board3D;
    }
}
