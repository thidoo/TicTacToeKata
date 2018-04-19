package Game.Service.Board;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;

public interface GameBoardService {

    boolean checkHasFilledLine(GameBoard gameBoard, Coordinate coordinate);
    GameBoard convertFromBoardStringtoBoard(String boardString);
}

