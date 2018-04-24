import Game.GameEngine;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Service.Board.Board2DService;
import Game.Service.Board.Board3DService;
import Game.Service.Board.GameBoardService;
import Game.Service.Board.PlaneSplitter;
import Game.Service.Converter.TicTacToeRepresentationConverter;
import Game.Service.Coordinate.Coordinate3DConverter;
import Game.Service.Coordinate.CoordinateConverter;
import Game.Service.GameLoop.*;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Service.Coordinate.Coordinate2DConverter;

import java.io.IOException;

public class Driver {



    public static void main(String[] args) throws IOException, CannotConvertToTicTacToeException {

        InputReader inputReader = new InputReader();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        consoleWriter.write("Would you like to play the 2D or 3D version of the game?[2|3]");
        String gameVersion = inputReader.read();

        GameBoardService gameBoardService;
        CoordinateConverter coordinateConverter;

        if (gameVersion.equals("2")){
            gameBoardService = new Board2DService();
            coordinateConverter = new Coordinate2DConverter();
        }
        else {
            gameBoardService = new Board3DService(new Board2DService(), new PlaneSplitter());
            coordinateConverter = new Coordinate3DConverter();
        }

        TicTacToeRepresentationConverter ticTacToeRepresentationConverter = new TicTacToeRepresentationConverter(gameBoardService);

        InputValidator inputValidator = new InputValidator(coordinateConverter);
        StateDecider stateDecider = new StateDecider();
        InputProcessor inputProcessor = new InputProcessor(inputValidator, stateDecider, gameBoardService, coordinateConverter);

        Configurator configurator = new Configurator(inputReader, consoleWriter, gameVersion);
        PreGameProcessor preGameProcessor = new PreGameProcessor(inputReader, consoleWriter, ticTacToeRepresentationConverter, configurator);
        GameProcessor gameProcessor = new GameProcessor(inputReader, consoleWriter, inputProcessor);
        PostGameProcessor postGameProcessor = new PostGameProcessor(inputReader, consoleWriter, ticTacToeRepresentationConverter);

        GameEngine gameEngine = new GameEngine(gameProcessor, preGameProcessor, postGameProcessor);

        gameEngine.run();
    }
}
