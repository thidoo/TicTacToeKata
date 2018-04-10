package GameService;

import GameComponent.Board.GameBoard;
import GameComponent.Coordinate.Coordinate;
import GameComponent.Player;
import GameService.MatchStatus.Draw;
import GameService.MatchStatus.NotFinished;
import GameService.MatchStatus.Status;
import GameService.MatchStatus.Win;

public class GameStatusChecker {

    public Status check(GameBoard gameBoard, Coordinate coordinate, Player currentPlayer){

        if (gameBoard.hasFilledLine(coordinate)){
            return new Win(currentPlayer);
        }

        if (gameBoard.isFull()){
            return new Draw();
        }

        return new NotFinished();
    }
}
