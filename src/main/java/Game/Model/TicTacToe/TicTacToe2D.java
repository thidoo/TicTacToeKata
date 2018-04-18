package Game.Model.TicTacToe;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;
import Game.Model.Player;
import Game.Model.TicTacToe.TicTacToeGame;

import java.util.Objects;

public class TicTacToe2D implements TicTacToeGame {

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    private boolean playing;
    private boolean finished;
    private Player currentPlayer;

    public TicTacToe2D(Player player1, Player player2, GameBoard gameBoard) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;

        this.currentPlayer = player1;
    }

    @Override
    public void switchPlayer() {
        if (this.currentPlayer.getPlayerOrder() == 1) {
            this.currentPlayer = player2;
        } else {
            this.currentPlayer = player1;
        }
    }

    @Override
    public void setIsPlaying(boolean isPlaying) {
        this.playing = isPlaying;
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public void setIsFinished(boolean isFinished) {
        this.finished = isFinished;
    }

    @Override
    public boolean isFinished() { return finished; }

    @Override
    public GameBoard getBoard() {
        return this.gameBoard;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public Player getPlayer1() {
        return player1;
    }

    @Override
    public Player getPlayer2() {
        return player2;
    }
}
