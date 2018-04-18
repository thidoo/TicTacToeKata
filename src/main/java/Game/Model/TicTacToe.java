package Game.Model;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;

import java.util.Objects;

public class TicTacToe {

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    private boolean playing;
    private boolean finished;
    private Player currentPlayer;

    public TicTacToe(Player player1, Player player2, GameBoard gameBoard) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = gameBoard;

        this.currentPlayer = player1;
    }

    public void switchPlayer() {
        if (this.currentPlayer.getPlayerOrder() == 1) {
            this.currentPlayer = player2;
        } else {
            this.currentPlayer = player1;
        }
    }

    public void setIsPlaying(boolean isPlaying) {
        this.playing = isPlaying;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setIsFinished(boolean isFinished) {
        this.finished = isFinished;
    }

    public boolean isFinished() { return finished; }

    public GameBoard getBoard() {
        return this.gameBoard;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
