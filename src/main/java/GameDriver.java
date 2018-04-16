import Game.Model.IO.ConsoleWriter;
import Game.Model.IO.InputReader;
import Game.Model.TicTacToe;
import Game.Service.*;
import Game.Service.Coordinate.Coordinate2DConverter;

public class GameDriver {

    public static void main(String[] args){

        InputReader inputReader = new InputReader();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        Coordinate2DConverter coordinate2DConverter = new Coordinate2DConverter();
        InputValidator inputValidator = new InputValidator(coordinate2DConverter);
        StateDecider stateDecider = new StateDecider();

        InputProcessor inputProcessor = new InputProcessor(inputValidator, stateDecider, coordinate2DConverter);
        Configurator configurator = new Configurator(inputReader, consoleWriter);

        GameEngine gameEngine = new GameEngine(inputReader, consoleWriter, inputProcessor);

        // Configure and run the game
        TicTacToe ticTacToe = configurator.configure();
        gameEngine.run(ticTacToe);
    }
}
