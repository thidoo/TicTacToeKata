package Game.Service.GameLoop;

import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Model.TicTacToe.TicTacToe2Players;
import Game.Model.TupleStructure.Pair;
import Game.Service.InputProcessor;

public class GameEngine {

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;
    private InputProcessor inputProcessor;

    private TicTacToe2Players ticTacToe2Players;

    public GameEngine(InputReader inputReader, ConsoleWriter consoleWriter, InputProcessor inputProcessor) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
        this.inputProcessor = inputProcessor;
    }

    public TicTacToe2Players run(TicTacToe2Players ticTacToe2Players) {
        this.ticTacToe2Players = ticTacToe2Players;
        this.ticTacToe2Players.setIsPlaying(true);

        consoleWriter.startGame(this.ticTacToe2Players.getBoard());

        while (this.ticTacToe2Players.isPlaying()) {
            consoleWriter.prompt(this.ticTacToe2Players.getCurrentPlayer());

            Pair gameInput = readPlayerInputFromConsole(this.ticTacToe2Players);
            Pair gameOutput = inputProcessor.process(gameInput);

            respondToPlayerInput(gameOutput);
        }

        return this.ticTacToe2Players;
    }

    private Pair readPlayerInputFromConsole(TicTacToe2Players ticTacToe2Players){
        String consoleInput = inputReader.read();
        Pair gameInput = new Pair<>(consoleInput, ticTacToe2Players);

        return gameInput;
    }

    private void respondToPlayerInput(Pair gameOutput){
        this.ticTacToe2Players = (TicTacToe2Players) gameOutput.getRight();
        String outMessage = (String) gameOutput.getLeft();

        consoleWriter.write(outMessage);
    }

    public ConsoleWriter getConsoleWriter() {
        return this.consoleWriter;
    }

    public InputReader getInputReader() {
        return this.inputReader;
    }
}
