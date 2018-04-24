package Game.Service.GameLoop;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;
import Game.Model.InputValidatorResult;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.Coordinate.CoordinateConverter;

public class InputValidator {

    private static final String QUIT_KEY = "q";

    private CoordinateConverter coordinateConverter;

    public InputValidator(CoordinateConverter coordinateConverter) {
        this.coordinateConverter = coordinateConverter;
    }

    public InputValidatorResult validate(GameBoard gameBoard, String input){
        if (input.equals(QUIT_KEY)){
            return InputValidatorResult.EXIT;
        }
        else {
            return determineRunningOutcome(gameBoard, input);
        }
    }

    private InputValidatorResult determineRunningOutcome(GameBoard gameBoard, String input){

        if (coordinateConverter.isCorrectFormat(input)){
            Coordinate coordinate = coordinateConverter.convert(input);

            if (!gameBoard.contains(coordinate)){
                return InputValidatorResult.MOVE_OUT_OF_BOUND;
            }
            else {
                if (gameBoard.isEmptyAt(coordinate)){
                    return InputValidatorResult.VALID_MOVE;
                }
                else {
                    return InputValidatorResult.OCCUPIED_CELL;
                }
            }
        }
        else{
            return InputValidatorResult.INVALID_COORD_FORMAT;
        }
    }
}
