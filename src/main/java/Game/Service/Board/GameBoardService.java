package Game.Service.Board;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;

public interface GameBoardService {

    boolean checkForWinner(GameBoard gameBoard, Coordinate coordinate);

    GameBoard convertStringToBoard(String boardString);

}

