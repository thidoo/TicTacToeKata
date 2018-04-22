import Game.GameEngine;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Service.Board.Board2DService;
import Game.Service.Converter.StringTTTConverter;
import Game.Service.GameLoop.*;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Service.*;
import Game.Service.Coordinate.Coordinate2DConverter;

import java.io.IOException;

public class GameDriver {

    public static void main(String[] args) throws IOException, CannotConvertToTicTacToeException {

        InputReader inputReader = new InputReader();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        Coordinate2DConverter coordinate2DConverter = new Coordinate2DConverter();
        Board2DService board2DService = new Board2DService();

        StringTTTConverter stringTTTConverter = new StringTTTConverter(board2DService);

        InputValidator inputValidator = new InputValidator(coordinate2DConverter);
        StateDecider stateDecider = new StateDecider();
        InputProcessor inputProcessor = new InputProcessor(inputValidator, stateDecider, board2DService, coordinate2DConverter);

        Configurator configurator = new Configurator(inputReader, consoleWriter);
        PreGameProcessor preGameProcessor = new PreGameProcessor(inputReader, consoleWriter, stringTTTConverter, configurator);
        GameProcessor gameProcessor = new GameProcessor(inputReader, consoleWriter, inputProcessor);
        PostGameProcessor postGameProcessor = new PostGameProcessor(inputReader, consoleWriter, stringTTTConverter);

        GameEngine gameEngine = new GameEngine(gameProcessor, preGameProcessor, postGameProcessor);

        gameEngine.run();
    }
}
