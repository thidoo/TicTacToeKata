package Game.Service.Board;

import Game.Model.Board.GameBoard2D;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class GameBoard2DServiceTest {

    @Test
    public void convertFromBoardStringtoBoardTest() {
        GameBoard2DService gameBoard2DService = new GameBoard2DService();

        String board = ". . . \n. . . \n. . . \n";
        GameBoard2D expectedBoard = new GameBoard2D(3);
        System.out.println(board);
        GameBoard2D actualBoard = gameBoard2DService.convertFromBoardStringtoBoard(board);
        System.out.println(actualBoard.toString());

        assertThat(actualBoard.toString(), equalTo(expectedBoard.toString()));
    }
}