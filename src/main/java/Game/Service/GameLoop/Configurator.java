package Game.Service.GameLoop;

import Game.Model.Board.Board2D;
import Game.Model.Board.Board3D;
import Game.Model.Board.GameBoard;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Model.Player;
import Game.Model.TicTacToe;

public class Configurator {

    private ConsoleWriter consoleWriter;
    private InputReader inputReader;
    private String gameVersion;

    public Configurator(InputReader inputReader, ConsoleWriter consoleWriter, String gameVersion) {
        this.consoleWriter = consoleWriter;
        this.inputReader = inputReader;
        this.gameVersion = gameVersion;
    }

    public TicTacToe configure(){
        consoleWriter.write("\nPlayer to go first, please enter a letter to represent your token: ");
        String player1Token = inputReader.read();

        consoleWriter.write("\nPlayer to go second, please enter a letter to represent your token: ");
        String player2Token = inputReader.read();

        consoleWriter.write("\nPlease enter a preferred board size: ");
        String boardSize = inputReader.read();

        return createTicTacToe(player1Token, player2Token, boardSize);
    }

    private TicTacToe createTicTacToe(String p1, String p2, String boardSize) {
        Player player1 = new Player(1, p1);
        Player player2 = new Player(2, p2);
        GameBoard gameBoard = createBoardOfCorrectDimension(boardSize);

        return new TicTacToe(player1, player2, gameBoard);
    }

    private GameBoard createBoardOfCorrectDimension(String boardSize){
        if (gameVersion.equals("2")){
            return new Board2D(Integer.parseInt(boardSize));
        }
        else {
            return new Board3D(Integer.parseInt(boardSize));
        }
    }
}
