package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Player;
import Game.Model.State.Draw;
import Game.Model.State.NotFinished;
import Game.Model.State.GameState;
import Game.Model.State.Win;
import Game.Service.Board.GameBoard2DService;
import Game.Service.Board.GameBoardService;

public class StateDecider {

    public GameState check(GameBoardService gameBoardService, GameBoard gameBoard, Coordinate coordinate, Player currentPlayer){

        if (gameBoardService.checkHasFilledLine(gameBoard, coordinate)){
            return new Win(currentPlayer);
        }

        if (gameBoard.isFull()){
            return new Draw();
        }

        return new NotFinished();
    }
}
