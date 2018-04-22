package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Board.Board2D;
import Game.Model.Coordinate.Coordinate2D;
import Game.Model.Player;
import Game.Model.State.NotFinished;
import Game.Model.State.GameState;
import Game.Model.State.Win;
import Game.Service.Board.Board2DService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StateDeciderTest {

    private StateDecider stateDecider;

    @Before
    public void setUp(){
        stateDecider = new StateDecider();
    }

    @Test
    public void NotFinishedStatusTest(){
        GameBoard board = new Board2D(3);
        ((Board2D) board).getContent()[1][1].setToken("X");

        GameState gameGameState = stateDecider.check(new Board2DService(), board, new Coordinate2D(1,1), new Player("X"));
        boolean expectedResult = gameGameState instanceof NotFinished;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithHorizontalLineFilled_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_HorizontalLineFilled();
        winBoard.printBoard();

        GameState gameGameState = stateDecider.check(new Board2DService(), winBoard, new Coordinate2D(0,2), new Player("X"));
        boolean expectedResult = gameGameState instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithVerticalLineFilled_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_VerticalLineFilled();
        winBoard.printBoard();

        GameState gameGameState = stateDecider.check(new Board2DService(), winBoard, new Coordinate2D(1,0), new Player("X"));
        boolean expectedResult = gameGameState instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithDiagonalLineFilled_NegativeSlope_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_DiagonalLineFilled_NegativeSlope();
        winBoard.printBoard();

        GameState gameGameState = stateDecider.check(new Board2DService(), winBoard, new Coordinate2D(1,1), new Player("X"));
        boolean expectedResult = gameGameState instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithDiagonalLineFilled_PositiveSlope_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_DiagonalLineFilled_PositiveSlope();
        winBoard.printBoard();

        GameState gameGameState = stateDecider.check(new Board2DService(), winBoard, new Coordinate2D(1,1), new Player("X"));
        boolean expectedResult = gameGameState instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    private GameBoard createWinBoard_HorizontalLineFilled(){
        GameBoard winBoard = new Board2D(3);
        for (int i=0; i<3; i++){
            ((Board2D) winBoard).getContent()[0][i].setToken("X");
            ((Board2D) winBoard).getContent()[0][i].setEmpty(false);
        }
        return winBoard;
    }

    private GameBoard createWinBoard_VerticalLineFilled() {
        GameBoard winBoard = new Board2D(3);
        for (int i=0; i<3; i++){
            ((Board2D) winBoard).getContent()[i][0].setToken("X");
            ((Board2D) winBoard).getContent()[i][0].setEmpty(false);
        }
        return winBoard;
    }

    private GameBoard createWinBoard_DiagonalLineFilled_NegativeSlope() {
        GameBoard winBoard = new Board2D(3);
        for (int i=0; i<3; i++){
            ((Board2D) winBoard).getContent()[i][i].setToken("X");
            ((Board2D) winBoard).getContent()[i][i].setEmpty(false);
        }
        return winBoard;
    }

    private GameBoard createWinBoard_DiagonalLineFilled_PositiveSlope() {
        GameBoard winBoard = new Board2D(3);
        for (int i=0; i<3; i++){
            ((Board2D) winBoard).getContent()[3-1-i][i].setToken("X");
            ((Board2D) winBoard).getContent()[3-1-i][i].setEmpty(false);
        }
        return winBoard;
    }
}