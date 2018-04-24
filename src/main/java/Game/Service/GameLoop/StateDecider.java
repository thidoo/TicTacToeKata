package Game.Service.GameLoop;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Player;
import Game.Model.GameResult.State.Draw;
import Game.Model.GameResult.State.NotFinished;
import Game.Model.GameResult.State.GameState;
import Game.Model.GameResult.State.Win;
import Game.Service.Board.GameBoardService;

public class StateDecider {

    public GameState check(GameBoardService gameBoardService, GameBoard gameBoard, Coordinate coordinate, Player currentPlayer){

        if (gameBoardService.checkForWinner(gameBoard, coordinate)){
            return new Win(currentPlayer);
        }

        if (gameBoard.isFull()){
            return new Draw();
        }

        return new NotFinished();
    }
}
