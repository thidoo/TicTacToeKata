package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;
import Game.Model.InputValidatorOutcome;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Model.ValidatorResult.Exit;
import Game.Model.ValidatorResult.InputValidatorResult;
import Game.Model.ValidatorResult.InvalidCoordinateFormat;
import Game.Model.ValidatorResult.MoveOutOfBound;
import Game.Model.ValidatorResult.OccupiedCell;
import Game.Model.ValidatorResult.ValidMove;

public class InputValidator {

    private static final String QUIT_KEY = "q";

    private Coordinate2DConverter coordinate2DConverter;

    public InputValidator(Coordinate2DConverter coordinate2DConverter) {
        this.coordinate2DConverter = coordinate2DConverter;
    }

    public InputValidatorOutcome validate(GameBoard gameBoard, String input){
        if (input.equals(QUIT_KEY)){
            return InputValidatorOutcome.EXIT;
        }
        else {
            return determineRunningOutcome(gameBoard, input);
        }
    }

    private InputValidatorOutcome determineRunningOutcome(GameBoard gameBoard, String input){

        if (coordinate2DConverter.isCorrectFormat(input)){
            Coordinate coordinate = coordinate2DConverter.convertToCoordinate(input);

            if (!gameBoard.contains(coordinate)){
                return InputValidatorOutcome.MOVE_OUT_OF_BOUND;
            }
            else {
                if (gameBoard.isEmptyAtPosition(coordinate)){
                    return InputValidatorOutcome.VALID_MOVE;
                }
                else {
                    return InputValidatorOutcome.OCCUPIED_CELL;
                }
            }
        }
        else{
            return InputValidatorOutcome.INVALID_COORD_FORMAT;
        }
    }
}
