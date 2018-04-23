package Game.Service.Board;

import Game.Model.Board.Board2D;
import Game.Model.Board.Board3D;
import Game.Model.Cell;
import Game.Model.Coordinate.Coordinate2D;

public class PlaneSplitter {

    public Board2D[] splitXY(Board3D board){
        int size = board.getSize();

        Board2D[] XYPlanes = new Board2D[size];
        for (int height = 0; height < size; height++){
            XYPlanes[height] = convert2DCellArrayToBoard2D(board.getContent()[height]);
        }

        return XYPlanes;
    }

    public Board2D[] splitXZ(Board3D board) {
        int size = board.getSize();

        Board2D[] XZPlanes = new Board2D[size];
        for (int z = 0; z < size; z++){
            XZPlanes[z] = new Board2D(size);

            for (int x = 0; x < size; x++){
                for (int y = 0; y < size; y++){
                    String token = board.getContent()[y][x][z].getToken();
                    if (!(token.equals(Cell.getDefaultToken()))){
                        XZPlanes[z].updateCell(token, new Coordinate2D(x, y));
                    }
                }
            }
        }

        return XZPlanes;
    }

    public Board2D[] splitYZ(Board3D board) {
        int size = board.getSize();

        Board2D[] XZPlanes = new Board2D[size];
        for (int z = 0; z < size; z++){
            XZPlanes[z] = new Board2D(size);

            for (int x = 0; x < size; x++){
                for (int y = 0; y < size; y++){
                    String token = board.getContent()[x][z][y].getToken();
                    if (!(token.equals(Cell.getDefaultToken()))){
                        XZPlanes[z].updateCell(token, new Coordinate2D(x, y));
                    }
                }
            }
        }

        return XZPlanes;
    }

    private Board2D convert2DCellArrayToBoard2D(Cell[][] array){
        int size = array.length;
        Board2D board = new Board2D(size);
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                if (!array[x][y].getToken().equals(Cell.getDefaultToken())){
                    board.updateCell(array[x][y].getToken(), new Coordinate2D(x, y));
                }
            }
        }
        return board;
    }
}
