package Game.Service.SetUp;

import Game.Model.Board.GameBoard2D;
import Game.Model.IO.InputReader;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Model.TupleStructure.Triplet;

public class GameConfigurator {

    private InputReader inputReader;

    public GameConfigurator() {
        this.inputReader = new InputReader();
    }

    public TicTacToe configure(Triplet<String, String, Integer> configInput) {
        Player player1 = new Player(1, configInput.getFirst());
        Player player2 = new Player(2, configInput.getSecond());
        GameBoard2D gameBoard = new GameBoard2D(configInput.getThird());

        return new TicTacToe(player1, player2, gameBoard);
    }
}
