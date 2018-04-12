package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Model.ValidatorResult.Exit;
import Game.Model.ValidatorResult.InputValidatorResult;
import Game.Model.ValidatorResult.InvalidCoordinateFormat;
import Game.Model.ValidatorResult.MoveOutOfBound;
import Game.Model.ValidatorResult.ValidMove;
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
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "q");
        boolean expectedMatch = inputValidatorResult instanceof Exit;
        assertThat(expectedMatch, equalTo(true));
    }

    @Test
    public void validMove_ShouldReturn_ValidMoveSuccess() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "2,2");
        boolean expectedMatch = inputValidatorResult instanceof ValidMove;
        assertThat(expectedMatch, equalTo(true));
    }

    @Test
    public void invalidMove_ShouldReturn_InvalidMoveSuccess() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "4,4");
        boolean expectedMatch = inputValidatorResult instanceof MoveOutOfBound;
        assertThat(expectedMatch, equalTo(true));
    }

    @Test
    public void invalidCoordinateFormat_ShouldReturn_InvalidCoordinateFormatSuccess() {
        InputValidatorResult inputValidatorResult = inputValidator.validate(gameBoard, "44");
        boolean expectedMatch = inputValidatorResult instanceof InvalidCoordinateFormat;
        assertThat(expectedMatch, equalTo(true));
    }
}