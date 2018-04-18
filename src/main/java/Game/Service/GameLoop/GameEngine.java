package Game.Service.GameLoop;

import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Model.TicTacToe.TicTacToe2D;
import Game.Model.TupleStructure.Pair;
import Game.Service.InputProcessor;

public class GameEngine {

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;
    private InputProcessor inputProcessor;

    private TicTacToe2D ticTacToe2D;

    public GameEngine(InputReader inputReader, ConsoleWriter consoleWriter, InputProcessor inputProcessor) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
        this.inputProcessor = inputProcessor;
    }

    public TicTacToe2D run(TicTacToe2D ticTacToe2D) {
        this.ticTacToe2D = ticTacToe2D;
        this.ticTacToe2D.setIsPlaying(true);

        consoleWriter.startGame(this.ticTacToe2D.getBoard());

        while (this.ticTacToe2D.isPlaying()) {
            consoleWriter.prompt(this.ticTacToe2D.getCurrentPlayer());

            Pair gameInput = readPlayerInputFromConsole(this.ticTacToe2D);
            Pair gameOutput = inputProcessor.process(gameInput);

            respondToPlayerInput(gameOutput);
        }

        return this.ticTacToe2D;
    }

    private Pair readPlayerInputFromConsole(TicTacToe2D ticTacToe2D){
        String consoleInput = inputReader.read();
        Pair gameInput = new Pair<>(consoleInput, ticTacToe2D);

        return gameInput;
    }

    private void respondToPlayerInput(Pair gameOutput){
        this.ticTacToe2D = (TicTacToe2D) gameOutput.getRight();
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
