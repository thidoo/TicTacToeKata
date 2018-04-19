package Game.Service.Board;

import Game.Model.Board.GameBoard3D;
import Game.Model.Coordinate.Coordinate3D;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameBoard3DServiceTest {

    private GameBoard3DService gameBoard3DService;

    @Before
    public void setUp(){
        gameBoard3DService = new GameBoard3DService();
    }

    @Test
    public void checkLineFilledHorizontally(){
        GameBoard3D winBoard = createBoardWithALineFilledHorizontally(3);

        boolean result = gameBoard3DService.checkHasFilledLine(winBoard, new Coordinate3D(1, 1, 0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledVertically_YDirection(){
        GameBoard3D winBoard = createBoardWithALineFilledVertically_YDirection(3);

        boolean result = gameBoard3DService.checkHasFilledLine(winBoard, new Coordinate3D(0, 1, 1));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledVertically_ZDirection(){
        GameBoard3D winBoard = createBoardWithALineFilledVertically_ZDirection(3);

        boolean result = gameBoard3DService.checkHasFilledLine(winBoard, new Coordinate3D(1, 1, 2));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledDiagonally(){
        GameBoard3D winBoard = createBoardWithDiagonalLineFilled(3);
        winBoard.printBoard();

        boolean result = gameBoard3DService.checkHasFilledLine(winBoard, new Coordinate3D(2, 0, 0));
        assertThat(result, equalTo(true));
    }

    private GameBoard3D createBoardWithALineFilledHorizontally(int size) {
        GameBoard3D winBoard = new GameBoard3D(size);
        for (int y=0; y<size; y++){
            winBoard.updateCell("X", new Coordinate3D(1, y, 0));
        }
        return winBoard;
    }

    private GameBoard3D createBoardWithALineFilledVertically_YDirection(int size) {
        GameBoard3D winBoard = new GameBoard3D(size);
        for (int x=0; x<size; x++){
            winBoard.updateCell("X", new Coordinate3D(x, 1, 1));
        }
        return winBoard;
    }

    private GameBoard3D createBoardWithALineFilledVertically_ZDirection(int size) {
        GameBoard3D winBoard = new GameBoard3D(size);
        for (int z=0; z<size; z++){
            winBoard.updateCell("X", new Coordinate3D(1, 1, z));
        }
        return winBoard;
    }

    private GameBoard3D createBoardWithDiagonalLineFilled(int size) {
        GameBoard3D winBoard = new GameBoard3D(size);
        winBoard.updateCell("X", new Coordinate3D(2, 0, 0));
        winBoard.updateCell("X", new Coordinate3D(1, 1, 1));
        winBoard.updateCell("X", new Coordinate3D(0, 2, 2));
        return winBoard;
    }

}
