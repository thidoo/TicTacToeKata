package Game.Service.Board;

import Game.Model.Board.Board2D;
import Game.Model.Coordinate.Coordinate2D;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Board2DServiceTest {

    private Board2DService board2DService;

    @Before
    public void setUp(){
        board2DService = new Board2DService();
    }

    @Test
    public void canCheckHorizontalFill(){
        Board2D boardWithHorizontalLineFilled = createWinBoard_HorizontalLineFilled(3);
        boolean result = board2DService.checkForWinner(boardWithHorizontalLineFilled, new Coordinate2D(0,0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void canCheckVerticalFill(){
        Board2D boardWithVerticalLineFilled = createWinBoard_VerticalLineFilled(3);
        boolean result = board2DService.checkForWinner(boardWithVerticalLineFilled, new Coordinate2D(0,0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void canCheckDiagonalFill(){
        Board2D boardWithDiagonalLineFilled_Positive = createWinBoard_DiagonalLineFilled_PositiveSlope(3);
        boolean resultPositive = board2DService.checkForWinner(boardWithDiagonalLineFilled_Positive, new Coordinate2D(0,2));

        Board2D boardWithDiagonalLineFilled_Negative = createWinBoard_DiagonalLineFilled_NegativeSlope(3);
        boolean resultNegative = board2DService.checkForWinner(boardWithDiagonalLineFilled_Negative, new Coordinate2D(0,0));

        assertThat(resultPositive, equalTo(true));
        assertThat(resultNegative, equalTo(true));
    }

    @Test
    public void shouldReturnFalse_ForNoFilledLine(){
        Board2D noWinBoard = createNoWinningBoard(3);
        boolean result = board2DService.checkForWinner(noWinBoard, new Coordinate2D(0,0));
        assertThat(result, equalTo(false));
    }

    @Test
    public void convertFromBoardString_toBoardTest() {

        String board = ". . . \n. . . \n. . . \n";
        Board2D expectedBoard = new Board2D(3);
        Board2D actualBoard = (Board2D) board2DService.convertStringToBoard(board);

        assertThat(actualBoard.getSize(), equalTo(expectedBoard.getSize()));
        assertThat(actualBoard.toString(), equalTo(expectedBoard.toString()));
    }

    private Board2D createWinBoard_HorizontalLineFilled(int size){
        Board2D winBoard = new Board2D(size);
        for (int i=0; i<size; i++){
            winBoard.getContent()[0][i].setToken("X");
            winBoard.getContent()[0][i].setEmpty(false);
        }
        return winBoard;
    }

    private Board2D createWinBoard_VerticalLineFilled(int size) {
        Board2D winBoard = new Board2D(size);
        for (int i=0; i<size; i++){
            winBoard.getContent()[i][0].setToken("X");
            winBoard.getContent()[i][0].setEmpty(false);
        }
        return winBoard;
    }

    private Board2D createWinBoard_DiagonalLineFilled_NegativeSlope(int size) {
        Board2D winBoard = new Board2D(size);
        for (int i=0; i<3; i++){
            winBoard.getContent()[i][i].setToken("X");
            winBoard.getContent()[i][i].setEmpty(false);
        }
        return winBoard;
    }

    private Board2D createWinBoard_DiagonalLineFilled_PositiveSlope(int size) {
        Board2D winBoard = new Board2D(size);
        for (int i=0; i<3; i++){
            winBoard.getContent()[3-1-i][i].setToken("X");
            winBoard.getContent()[3-1-i][i].setEmpty(false);
        }
        return winBoard;
    }

    private Board2D createNoWinningBoard(int size){
        Board2D noWinBoard = new Board2D(size);
        for (int i=0; i<size-1; i++){
            noWinBoard.getContent()[0][i].setToken("X");
            noWinBoard.getContent()[0][i].setEmpty(false);
        }
        return noWinBoard;
    }
}