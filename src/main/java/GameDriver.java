import Game.Model.CannotConvertToTicTacToeException;
import Game.Service.Board.GameBoard2DService;
import Game.Service.GameLooper;
import Game.Service.GameEngine;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Service.*;
import Game.Service.Coordinate.Coordinate2DConverter;

import java.io.IOException;

public class GameDriver {

    public static void main(String[] args) throws IOException {

        InputReader inputReader = new InputReader();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        Coordinate2DConverter coordinate2DConverter = new Coordinate2DConverter();
        GameBoard2DService gameBoard2DService = new GameBoard2DService();

        //JSONConverter jsonConverter = new JSONConverter(gameBoard2DService);
        StringTTTConverter stringTTTConverter = new StringTTTConverter(gameBoard2DService);

        InputValidator inputValidator = new InputValidator(coordinate2DConverter);
        StateDecider stateDecider = new StateDecider();
        Configurator configurator = new Configurator(inputReader, consoleWriter);

        InputProcessor inputProcessor = new InputProcessor(inputValidator, stateDecider, coordinate2DConverter);
        PreGameProcessor preGameProcessor = new PreGameProcessor(inputReader, consoleWriter, stringTTTConverter, configurator);
        PostGameProcessor postGameProcessor = new PostGameProcessor(inputReader, consoleWriter, stringTTTConverter);

        GameEngine gameEngine = new GameEngine(inputReader, consoleWriter, inputProcessor);

        GameLooper gameLooper = new GameLooper(gameEngine, preGameProcessor, postGameProcessor);
        try {
            gameLooper.loop();
        }
        catch (CannotConvertToTicTacToeException e){
            e.getCause();
        }
    }
}
