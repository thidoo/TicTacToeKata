package Game.Service.Board;

import Game.Model.Board.GameBoard2D;
import Game.Model.Coordinate.Coordinate2D;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GameBoard2DServiceTest {

    private GameBoard2DService gameBoard2DService;

    @Before
    public void setUp(){
        gameBoard2DService = new GameBoard2DService();
    }

    @Test
    public void canCheckHorizontalFill(){
        GameBoard2D boardWithHorizontalLineFilled = createWinBoard_HorizontalLineFilled(3);
        boolean result = gameBoard2DService.checkHasFilledLine(boardWithHorizontalLineFilled, new Coordinate2D(0,0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void canCheckVerticalFill(){
        GameBoard2D boardWithVerticalLineFilled = createWinBoard_VerticalLineFilled(3);
        boolean result = gameBoard2DService.checkHasFilledLine(boardWithVerticalLineFilled, new Coordinate2D(0,0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void canCheckDiagonalFill(){
        GameBoard2D boardWithDiagonalLineFilled_Positive = createWinBoard_DiagonalLineFilled_PositiveSlope(3);
        boolean resultPositive = gameBoard2DService.checkHasFilledLine(boardWithDiagonalLineFilled_Positive, new Coordinate2D(0,2));

        GameBoard2D boardWithDiagonalLineFilled_Negative = createWinBoard_DiagonalLineFilled_NegativeSlope(3);
        boolean resultNegative = gameBoard2DService.checkHasFilledLine(boardWithDiagonalLineFilled_Negative, new Coordinate2D(0,0));

        assertThat(resultPositive, equalTo(true));
        assertThat(resultNegative, equalTo(true));
    }

    @Test
    public void shouldReturnFalse_ForNoFilledLine(){
        GameBoard2D noWinBoard = createNoWinningBoard(3);
        boolean result = gameBoard2DService.checkHasFilledLine(noWinBoard, new Coordinate2D(0,0));
        assertThat(result, equalTo(false));
    }

    @Test
    public void convertFromBoardString_toBoardTest() {

        String board = ". . . \n. . . \n. . . \n";
        GameBoard2D expectedBoard = new GameBoard2D(3);
        GameBoard2D actualBoard = (GameBoard2D) gameBoard2DService.convertFromBoardStringtoBoard(board);

        assertThat(actualBoard.getBoardSize(), equalTo(expectedBoard.getBoardSize()));
        assertThat(actualBoard.toString(), equalTo(expectedBoard.toString()));
    }

    private GameBoard2D createWinBoard_HorizontalLineFilled(int size){
        GameBoard2D winBoard = new GameBoard2D(size);
        for (int i=0; i<size; i++){
            winBoard.getBoardContent()[0][i].setToken("X");
            winBoard.getBoardContent()[0][i].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard2D createWinBoard_VerticalLineFilled(int size) {
        GameBoard2D winBoard = new GameBoard2D(size);
        for (int i=0; i<size; i++){
            winBoard.getBoardContent()[i][0].setToken("X");
            winBoard.getBoardContent()[i][0].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard2D createWinBoard_DiagonalLineFilled_NegativeSlope(int size) {
        GameBoard2D winBoard = new GameBoard2D(size);
        for (int i=0; i<3; i++){
            winBoard.getBoardContent()[i][i].setToken("X");
            winBoard.getBoardContent()[i][i].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard2D createWinBoard_DiagonalLineFilled_PositiveSlope(int size) {
        GameBoard2D winBoard = new GameBoard2D(size);
        for (int i=0; i<3; i++){
            winBoard.getBoardContent()[3-1-i][i].setToken("X");
            winBoard.getBoardContent()[3-1-i][i].setIsEmpty(false);
        }
        return winBoard;
    }

    private GameBoard2D createNoWinningBoard(int size){
        GameBoard2D noWinBoard = new GameBoard2D(size);
        for (int i=0; i<size-1; i++){
            noWinBoard.getBoardContent()[0][i].setToken("X");
            noWinBoard.getBoardContent()[0][i].setIsEmpty(false);
        }
        return noWinBoard;
    }
}