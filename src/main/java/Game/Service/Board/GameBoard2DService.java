package Game.Service.Board;

import Game.Model.Board.GameBoard2D;
import Game.Model.Coordinate.Coordinate2D;

public class GameBoard2DService {

    public GameBoard2D convertFromBoardStringtoBoard(String boardString){
        String[] rows = boardString.split("\\n");
        GameBoard2D board = new GameBoard2D(rows.length);

        for (int row=0; row < rows.length; row++){
            String[] columns = rows[row].trim().split(" ");
            for (int column=0; column < columns.length; column++){
                if (!columns[column].equals(".")){
                    board.updateCell(columns[column], new Coordinate2D(row, column));
                }
            }
        }

        return board;
    }
}
