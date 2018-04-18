package Game.Service.GameLoop;

import Game.Model.Board.GameBoard2D;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Model.TupleStructure.Triplet;

public class Configurator {

    private ConsoleWriter consoleWriter;
    private InputReader inputReader;

    public Configurator(InputReader inputReader, ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        this.inputReader = inputReader;
    }

    public TicTacToe configure(){
        consoleWriter.write("Player to go first, please enter a letter to represent your token: ");
        String player1Token = inputReader.read();

        consoleWriter.write("Player to go second, please enter a letter to represent your token: ");
        String player2Token = inputReader.read();

        consoleWriter.write("Please enter a preferred board size: ");
        String boardSize = inputReader.read();

        Triplet configurationInput = new Triplet<>(player1Token, player2Token, boardSize);

        return createTicTacToe(configurationInput);
    }

    public TicTacToe createTicTacToe(Triplet input) {
        Player player1 = new Player(1, (String) input.getFirst());
        Player player2 = new Player(2, (String) input.getSecond());
        GameBoard2D gameBoard = new GameBoard2D(Integer.parseInt((String) input.getThird()));

        return new TicTacToe(player1, player2, gameBoard);
    }
}
