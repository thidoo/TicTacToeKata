package GameService;

import GameComponent.Board.GameBoard;
import GameComponent.Board.GameBoard2D;
import GameComponent.Coordinate.Coordinate2DService;
import GameService.InputValidator;
import GameService.ValidatorOutcome.Exit;
import GameService.ValidatorOutcome.InputValidatorOutcome;
import GameService.ValidatorOutcome.Running.InvalidCoordinateFormat;
import GameService.ValidatorOutcome.Running.OccupiedCell;
import GameService.ValidatorOutcome.Running.ValidMove;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputValidatorTest {

    private InputValidator inputValidator;
    private GameBoard gameBoard;

    @Before
    public void setUp(){
        inputValidator = new InputValidator(new Coordinate2DService());
        gameBoard = new GameBoard2D(3);
    }

    @Test
    public void qShouldReturnExitResult() {
        InputValidatorOutcome inputValidatorOutcome = inputValidator.validate(gameBoard, "q");
        boolean expectedMatch = inputValidatorOutcome instanceof Exit;
        assertThat(expectedMatch, equalTo(true));
    }

    @Test
    public void validMove_ShouldReturn_ValidMoveSuccess() {
        InputValidatorOutcome inputValidatorOutcome = inputValidator.validate(gameBoard, "2,2");
        boolean expectedMatch = inputValidatorOutcome instanceof ValidMove;
        assertThat(expectedMatch, equalTo(true));
    }

    @Test
    public void invalidMove_ShouldReturn_InvalidMoveSuccess() {
        InputValidatorOutcome inputValidatorOutcome = inputValidator.validate(gameBoard, "4,4");
        boolean expectedMatch = inputValidatorOutcome instanceof OccupiedCell;
        assertThat(expectedMatch, equalTo(true));
    }

    @Test
    public void invalidCoordinateFormat_ShouldReturn_InvalidCoordinateFormatSuccess() {
        InputValidatorOutcome inputValidatorOutcome = inputValidator.validate(gameBoard, "44");
        boolean expectedMatch = inputValidatorOutcome instanceof InvalidCoordinateFormat;
        assertThat(expectedMatch, equalTo(true));
    }
}