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
        Board2D[] XZPlanes = planeSplitter.splitXZ((Board3D)board);
        Board2D[] YZPlanes = planeSplitter.splitYZ((Board3D)board);

        return true;
    }

    private boolean checkXYPlanes(GameBoard board, Coordinate coordinate){
        Board2D[] XYPlanes = planeSplitter.splitXY((Board3D)board);
        int x = ((Coordinate3D)coordinate).getX();
        int y = ((Coordinate3D)coordinate).getY();

        boolean hasWinner = false;
        return true;
    }

    //    @Override
//    public boolean checkForWinner(GameBoard gameBoard, Coordinate coordinate) {
//        Cell[][][] boardContent = ((Board3D)gameBoard).getContent();
//        int boardSize = gameBoard.getSize();
//
//        int x = coordinate.getX();
//        int y = coordinate.getY();
//        int z = coordinate.getZ();
//
//        return hasLineFilledHorizontally(boardContent, boardSize, x, y, z)
//                || hasLineFilledVertically_YDirection(boardContent, boardSize, x, y, z)
//                || hasLineFilledVertically_ZDirection(boardContent, boardSize, x, y, z);
//    }
//
//    private boolean hasLineFilledHorizontally(Cell[][][] boardContent, int size, int x, int y, int z){
//        for (int i=0; i < size; i++){
//            if (!boardContent[z][x][i].getToken().equals(boardContent[z][x][y].getToken())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean hasLineFilledVertically_YDirection(Cell[][][] boardContent, int size, int x, int y, int z){
//        for (int i=0; i < size; i++){
//            if (!boardContent[z][i][y].getToken().equals(boardContent[z][x][y].getToken())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean hasLineFilledVertically_ZDirection(Cell[][][] boardContent, int size, int x, int y, int z){
//        for (int i=0; i < size; i++){
//            if (!boardContent[i][x][y].getToken().equals(boardContent[z][x][y].getToken())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean hasLineFilledDiagonally3D(Cell[][][] boardContent, int size, int x, int y, int z){
//        return true;
//    }

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
