package Game.Service.GameLoop;

import Game.Model.Board.GameBoard2D;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Model.Player;
import Game.Model.TicTacToe.TicTacToe2D;
import Game.Model.TupleStructure.Triplet;

public class Configurator {

    private ConsoleWriter consoleWriter;
    private InputReader inputReader;

    public Configurator(InputReader inputReader, ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.inputReader = inputReader;
    }

    public TicTacToe2D configure(){
        consoleWriter.write("\nPlayer to go first, please enter a letter to represent your token: ");
        String player1Token = inputReader.read();

        consoleWriter.write("\nPlayer to go second, please enter a letter to represent your token: ");
        String player2Token = inputReader.read();

        consoleWriter.write("\nPlease enter a preferred board size: ");
        String boardSize = inputReader.read();

        Triplet configurationInput = new Triplet<>(player1Token, player2Token, boardSize);

        return createTicTacToe(configurationInput);
    }

    public TicTacToe2D createTicTacToe(Triplet input) {
        Player player1 = new Player(1, (String) input.getFirst());
        Player player2 = new Player(2, (String) input.getSecond());
        GameBoard2D gameBoard = new GameBoard2D(Integer.parseInt((String) input.getThird()));

        return new TicTacToe2D(player1, player2, gameBoard);
    }
}
