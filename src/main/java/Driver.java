import Game.GameEngine;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Service.Board.Board2DService;
import Game.Service.Board.Board3DService;
import Game.Service.Board.PlaneSplitter;
import Game.Service.Converter.TicTacToeRepresentationConverter;
import Game.Service.Coordinate.Coordinate3DConverter;
import Game.Service.GameLoop.*;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Service.*;
import Game.Service.Coordinate.Coordinate2DConverter;

import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException, CannotConvertToTicTacToeException {

        InputReader inputReader = new InputReader();
        ConsoleWriter consoleWriter = new ConsoleWriter();

        Board2DService board2DService = new Board2DService();

        PlaneSplitter planeSplitter = new PlaneSplitter();
        Board3DService board3DService = new Board3DService(board2DService, planeSplitter);
        Coordinate3DConverter coordinate3DConverter = new Coordinate3DConverter();

        TicTacToeRepresentationConverter ticTacToeRepresentationConverter = new TicTacToeRepresentationConverter(board3DService);

        InputValidator inputValidator = new InputValidator(coordinate3DConverter);
        StateDecider stateDecider = new StateDecider();
        InputProcessor inputProcessor = new InputProcessor(inputValidator, stateDecider, board3DService, coordinate3DConverter);

        Configurator configurator = new Configurator(inputReader, consoleWriter);
        PreGameProcessor preGameProcessor = new PreGameProcessor(inputReader, consoleWriter, ticTacToeRepresentationConverter, configurator);
        GameProcessor gameProcessor = new GameProcessor(inputReader, consoleWriter, inputProcessor);
        PostGameProcessor postGameProcessor = new PostGameProcessor(inputReader, consoleWriter, ticTacToeRepresentationConverter);

        GameEngine gameEngine = new GameEngine(gameProcessor, preGameProcessor, postGameProcessor);

        gameEngine.run();
    }
}
