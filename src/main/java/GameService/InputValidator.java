package GameService;

import GameComponent.Board.GameBoard;
import GameComponent.Coordinate.Coordinate;
import GameComponent.Coordinate.Coordinate2D;
import GameComponent.Coordinate.Coordinate2DService;
import GameService.ValidatorOutcome.Exit;
import GameService.ValidatorOutcome.InputValidatorOutcome;
import GameService.ValidatorOutcome.Running.InvalidCoordinateFormat;
import GameService.ValidatorOutcome.Running.MoveOutOfBound;
import GameService.ValidatorOutcome.Running.OccupiedCell;
import GameService.ValidatorOutcome.Running.ValidMove;

public class InputValidator {
    private static final String QUIT_KEY = "q";

    private Coordinate2DService coordinate2DService;

    public InputValidator(Coordinate2DService coordinate2DService) {
        this.coordinate2DService = coordinate2DService;
    }

    public InputValidatorOutcome validate(GameBoard gameBoard, String input){
        if (input.equals(QUIT_KEY)){
            return new Exit();
        }
        else {
            return determineRunningOutcome(gameBoard, input);
        }
    }

    private InputValidatorOutcome determineRunningOutcome(GameBoard gameBoard, String input){

        if (coordinate2DService.isCorrectFormat(input)){

            Coordinate coordinate = coordinate2DService.convertToCoordinate(input);

            if (!gameBoard.contains(coordinate)){
                return new MoveOutOfBound();
            }
            else {
                if (gameBoard.isEmptyAtPosition(coordinate)){
                    return new ValidMove();
                }
                else {
                    return new OccupiedCell();
                }
            }
        }
        else{
            return new InvalidCoordinateFormat();
        }
    }
}
