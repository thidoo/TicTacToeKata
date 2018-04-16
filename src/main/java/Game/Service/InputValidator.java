package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Coordinate.Coordinate;
import Game.Model.InputValidatorResult;
import Game.Service.Coordinate.Coordinate2DConverter;

public class InputValidator {

    private static final String QUIT_KEY = "q";

    private Coordinate2DConverter coordinate2DConverter;

    public InputValidator(Coordinate2DConverter coordinate2DConverter) {
        this.coordinate2DConverter = coordinate2DConverter;
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

        if (coordinate2DConverter.isCorrectFormat(input)){
            Coordinate coordinate = coordinate2DConverter.convert(input);

            if (!gameBoard.contains(coordinate)){
                return InputValidatorResult.MOVE_OUT_OF_BOUND;
            }
            else {
                if (gameBoard.isEmptyAtPosition(coordinate)){
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
