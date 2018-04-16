package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;
import Game.Model.InputValidatorResult;
import Game.Service.Coordinate.Coordinate2DConverter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputValidatorTest {

    private InputValidator inputValidator;
    private GameBoard gameBoard;

    @Before
    public void setUp(){
        inputValidator = new InputValidator(new Coordinate2DConverter());
        gameBoard = new GameBoard2D(3);
    }

    @Test
    public void qShouldReturnExitResult() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "q");;
        assertThat(inputValidatorResult, equalTo(InputValidatorResult.EXIT));
    }

    @Test
    public void validMove_ShouldReturn_ValidMoveSuccess() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "2,2");
        assertThat(inputValidatorResult, equalTo(InputValidatorResult.VALID_MOVE));

    }

    @Test
    public void invalidMove_ShouldReturn_InvalidMoveSuccess() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "4,4");
        assertThat(inputValidatorResult, equalTo(InputValidatorResult.MOVE_OUT_OF_BOUND));
    }

    @Test
    public void invalidCoordinateFormat_ShouldReturn_InvalidCoordinateFormatSuccess() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "44");
        assertThat(inputValidatorResult, equalTo(InputValidatorResult.INVALID_COORD_FORMAT));
    }
}