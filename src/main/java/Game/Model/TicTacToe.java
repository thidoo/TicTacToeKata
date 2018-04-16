package Game.Model;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;

import java.util.Objects;

public class TicTacToe {

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    private boolean isPlaying;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicTacToe)) return false;
        TicTacToe that = (TicTacToe) o;
        return Objects.equals(player1, that.player1) &&
                Objects.equals(player2, that.player2) &&
                Objects.equals(gameBoard, that.gameBoard);
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public GameBoard getBoard() {
        return this.gameBoard;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
