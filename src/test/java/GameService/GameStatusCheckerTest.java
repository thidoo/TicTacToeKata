package GameService;

import GameComponent.Board.GameBoard;
import GameComponent.Board.GameBoard2D;
import GameComponent.Coordinate.Coordinate2D;
import GameComponent.Player;
import GameService.MatchStatus.NotFinished;
import GameService.MatchStatus.Status;
import GameService.MatchStatus.Win;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameStatusCheckerTest {

    private GameStatusChecker gameStatusChecker;

    @Before
    public void setUp(){
        gameStatusChecker = new GameStatusChecker();
    }

    @Test
    public void NotFinishedStatusTest(){
        GameBoard board = new GameBoard2D(3);
        ((GameBoard2D) board).getBoardContent()[1][1].setToken("X");

        Status gameStatus = gameStatusChecker.check(board, new Coordinate2D(1,1), new Player("X"));
        boolean expectedResult = gameStatus instanceof NotFinished;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithHorizontalLineFilled_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_HorizontalLineFilled();
        winBoard.printBoard();

        Status gameStatus = gameStatusChecker.check(winBoard, new Coordinate2D(0,2), new Player("X"));
        boolean expectedResult = gameStatus instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithVerticalLineFilled_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_VerticalLineFilled();
        winBoard.printBoard();

        Status gameStatus = gameStatusChecker.check(winBoard, new Coordinate2D(1,0), new Player("X"));
        boolean expectedResult = gameStatus instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithDiagonalLineFilled_NegativeSlope_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_DiagonalLineFilled_NegativeSlope();
        winBoard.printBoard();

        Status gameStatus = gameStatusChecker.check(winBoard, new Coordinate2D(1,1), new Player("X"));
        boolean expectedResult = gameStatus instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    @Test
    public void boardWithDiagonalLineFilled_PositiveSlope_ShouldReturnWin(){
        GameBoard winBoard = createWinBoard_DiagonalLineFilled_PositiveSlope();
        winBoard.printBoard();

        Status gameStatus = gameStatusChecker.check(winBoard, new Coordinate2D(1,1), new Player("X"));
        boolean expectedResult = gameStatus instanceof Win;

        assertThat(expectedResult, equalTo(true));
    }

    private GameBoard createWinBoard_HorizontalLineFilled(){
        GameBoard winBoard = new GameBoard2D(3);
        for (int i=0; i<3; i++){
            ((GameBoard2D) winBoard).getBoardContent()[0][i].setToken("X");
            ((GameBoard2D) winBoard).getBoardContent()[0][i].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard createWinBoard_VerticalLineFilled() {
        GameBoard winBoard = new GameBoard2D(3);
        for (int i=0; i<3; i++){
            ((GameBoard2D) winBoard).getBoardContent()[i][0].setToken("X");
            ((GameBoard2D) winBoard).getBoardContent()[i][0].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard createWinBoard_DiagonalLineFilled_NegativeSlope() {
        GameBoard winBoard = new GameBoard2D(3);
        for (int i=0; i<3; i++){
            ((GameBoard2D) winBoard).getBoardContent()[i][i].setToken("X");
            ((GameBoard2D) winBoard).getBoardContent()[i][i].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard createWinBoard_DiagonalLineFilled_PositiveSlope() {
        GameBoard winBoard = new GameBoard2D(3);
        for (int i=0; i<3; i++){
            ((GameBoard2D) winBoard).getBoardContent()[3-1-i][i].setToken("X");
            ((GameBoard2D) winBoard).getBoardContent()[3-1-i][i].setIsEmpty(false);
        }
        return winBoard;
    }
}