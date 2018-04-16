package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Player;
import Game.Model.State.Draw;
import Game.Model.State.NotFinished;
import Game.Model.State.GameState;
import Game.Model.State.Win;

public class StateDecider {

    public GameState check(GameBoard gameBoard, Coordinate coordinate, Player currentPlayer){

        if (gameBoard.hasFilledLine(coordinate)){
            return new Win(currentPlayer);
        }

        if (gameBoard.isFull()){
            return new Draw();
        }

        return new NotFinished();
    }
}