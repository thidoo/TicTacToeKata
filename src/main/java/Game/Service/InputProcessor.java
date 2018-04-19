package Game.Service;

import Game.Model.Coordinate.Coordinate;
import Game.Model.InputValidatorResult;
import Game.Model.State.GameState;
import Game.Model.State.NotFinished;
import Game.Model.TicTacToe.TicTacToe2D;
import Game.Model.TupleStructure.Pair;
import Game.Service.Board.GameBoardService;
import Game.Service.Coordinate.CoordinateConverter;

public class InputProcessor {

    private InputValidator inputValidator;
    private StateDecider stateDecider;
    private GameBoardService gameBoardService;
    private CoordinateConverter coordinateConverter;

    public InputProcessor(InputValidator inputValidator, StateDecider stateDecider, GameBoardService gameBoardService, CoordinateConverter coordinateConverter) {
        this.inputValidator = inputValidator;
        this.stateDecider = stateDecider;
        this.gameBoardService = gameBoardService;
        this.coordinateConverter = coordinateConverter;
    }

    public Pair process(Pair processorInput) {
        String playerInput = (String) processorInput.getLeft();
        TicTacToe2D inGame = (TicTacToe2D) processorInput.getRight();

        InputValidatorResult inputValidatorResult = inputValidator.validate(inGame.getBoard(), playerInput);

        return computeOutput(playerInput, inGame, inputValidatorResult);
    }

    private Pair computeOutput(String playerInput, TicTacToe2D inGame, InputValidatorResult inputValidatorResult){
        if (inputValidatorResult == InputValidatorResult.VALID_MOVE) {
            return computeValidMoveOutput(playerInput, inGame, inputValidatorResult);
        }
        else if (inputValidatorResult == InputValidatorResult.EXIT){
            inGame.setIsPlaying(false);
        }

        return new Pair<>(inputValidatorResult.message(), inGame);
    }

    private Pair computeValidMoveOutput(String playerInput, TicTacToe2D inGame, InputValidatorResult inputValidatorResult){
        Coordinate coordinate = coordinateConverter.convert(playerInput);
        inGame.getBoard().updateCell(inGame.getCurrentPlayer().getToken(), coordinate);

        return determineGameState(inGame, coordinate, inputValidatorResult);
    }

    private Pair determineGameState(TicTacToe2D game, Coordinate coordinate, InputValidatorResult inputValidatorResult){
        GameState state = stateDecider.check(gameBoardService, game.getBoard(), coordinate, game.getCurrentPlayer());

        String outMessage;

        if (state instanceof NotFinished){
            outMessage = inputValidatorResult.message() + "\n" + game.getBoard().toString();
            game.switchPlayer();
        }
        else {
            outMessage = inputValidatorResult.message() + "\n" + game.getBoard().toString() + "\n" + state.getMessage();
            game.setIsPlaying(false);
            game.setIsFinished(true);
        }

        return new Pair<>(outMessage, game);
    }
}
