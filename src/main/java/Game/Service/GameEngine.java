package Game.Service;

import Game.Model.IO.ConsoleWriter;
import Game.Model.IO.InputReader;
import Game.Model.TicTacToe;
import Game.Model.TupleStructure.Pair;

public class GameEngine {

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;
    private InputProcessor inputProcessor;

    private TicTacToe ticTacToe;

    public GameEngine(InputReader inputReader, ConsoleWriter consoleWriter, InputProcessor inputProcessor) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
        this.inputProcessor = inputProcessor;
    }

    public TicTacToe run(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        this.ticTacToe.setIsPlaying(true);

        consoleWriter.startGame(this.ticTacToe.getBoard());

        while (this.ticTacToe.isPlaying()) {
            consoleWriter.prompt(this.ticTacToe.getCurrentPlayer());

            Pair gameInput = readPlayerInputFromConsole(this.ticTacToe);
            Pair gameOutput = inputProcessor.process(gameInput);

            respondToPlayerInput(gameOutput);
        }

        return this.ticTacToe;
    }

    private Pair readPlayerInputFromConsole(TicTacToe ticTacToe){
        String consoleInput = inputReader.readFromConsole();
        Pair gameInput = new Pair<>(consoleInput, ticTacToe);

        return gameInput;
    }

    private void respondToPlayerInput(Pair gameOutput){
        this.ticTacToe = (TicTacToe) gameOutput.getRight();
        String outMessage = (String) gameOutput.getLeft();

        consoleWriter.write(outMessage);
    }
}
