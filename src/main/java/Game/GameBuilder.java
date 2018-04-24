package Game;

import Game.Service.Board.Board2DService;
import Game.Service.Board.Board3DService;
import Game.Service.Board.GameBoardService;
import Game.Service.Board.PlaneSplitter;
import Game.Service.Converter.TicTacToeRepresentationConverter;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.Coordinate.Coordinate3DConverter;
import Game.Service.Coordinate.CoordinateConverter;
import Game.Service.GameLoop.*;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;

public class GameBuilder {

    private static String TWO_D = "2";
    private static String THREE_D = "3";

    private ConsoleWriter consoleWriter = new ConsoleWriter();
    private InputReader inputReader = new InputReader();

    public Game build(){
        consoleWriter.write(String.format("Would you like to play the 2D or 3D version of the game?[%s|%s]", TWO_D, THREE_D));
        String dimension = inputReader.read();

        return createGameByDimension(dimension);
    }

    public Game createGameByDimension(String dimension){
        GameBoardService gameBoardService;
        CoordinateConverter coordinateConverter;

        if (dimension.equals(TWO_D)){
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

        Configurator configurator = new Configurator(inputReader, consoleWriter, dimension);
        PreGameProcessor preGameProcessor = new PreGameProcessor(inputReader, consoleWriter, ticTacToeRepresentationConverter, configurator);
        GameProcessor gameProcessor = new GameProcessor(inputReader, consoleWriter, inputProcessor);
        PostGameProcessor postGameProcessor = new PostGameProcessor(inputReader, consoleWriter, ticTacToeRepresentationConverter);

        return new Game(gameProcessor, preGameProcessor, postGameProcessor);
    }
}
