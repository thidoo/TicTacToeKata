package Game.Model.TicTacToe;

import Game.Model.Board.GameBoard;
import Game.Model.Player;

public interface TicTacToeGame {

    void switchPlayer();
    void setIsPlaying(boolean isPlaying);
    boolean isPlaying();
    void setIsFinished(boolean isFinished);
    boolean isFinished();
    GameBoard getBoard();
    Player getCurrentPlayer();
    Player getPlayer1();
    Player getPlayer2();
}
