package Game.Service.Board;

import Game.Model.Board.Board3D;
import Game.Model.Coordinate.Coordinate3D;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Board3DServiceTest {

    private Board3DService board3DService;

    @Before
    public void setUp(){
        board3DService = new Board3DService(new Board2DService(), new PlaneSplitter());
    }

    @Test
    public void checkLineFilledHorizontally(){
        Board3D winBoard = createBoardWithALineFilledHorizontally(3);

        boolean result = board3DService.checkForWinner(winBoard, new Coordinate3D(1, 1, 0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledVertically_YDirection(){
        Board3D winBoard = createBoardWithALineFilledVertically_YDirection(3);

        boolean result = board3DService.checkForWinner(winBoard, new Coordinate3D(0, 1, 1));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledVertically_ZDirection(){
        Board3D winBoard = createBoardWithALineFilledVertically_ZDirection(3);

        boolean result = board3DService.checkForWinner(winBoard, new Coordinate3D(1, 1, 2));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledDiagonally_BoardSize3(){
        Board3D winBoard = createBoardWithDiagonalLineFilledSize3();

        boolean result = board3DService.checkForWinner(winBoard, new Coordinate3D(2, 0, 0));
        assertThat(result, equalTo(true));
    }

    @Test
    public void checkLineFilledDiagonally_BoardSize4(){
        Board3D winBoard = createBoardWithDiagonalLineFilledSize4();

        boolean result = board3DService.checkForWinner(winBoard, new Coordinate3D(2, 1, 2));
        assertThat(result, equalTo(true));
    }

    @Test
    public void noWinnerTest(){
        Board3D board = new Board3D(3);
        board.updateCell("X", new Coordinate3D(0,0,0));
        board.updateCell("X", new Coordinate3D(2, 1, 1));

        boolean result = board3DService.checkForWinner(board, new Coordinate3D(0, 0, 0));
        assertThat(result, equalTo(false));
    }

    @Test
    public void convertStringToBoardRepresentation(){
        String boardString = ". . . \nX X X\n. . . \n\n. . . \n. . . \n. . . \n\n. . . \n. . . \n. . . \n\n";
        Board3D expectedBoard = createBoardWithALineFilledHorizontally(3);

        Board3D actual = (Board3D) board3DService.convertStringToBoard(boardString);
        assertThat(actual.getSize(), equalTo(expectedBoard.getSize()));
        assertThat(actual.toString(), equalTo(expectedBoard.toString()));
    }

    private Board3D createBoardWithALineFilledHorizontally(int size) {
        Board3D winBoard = new Board3D(size);
        for (int y=0; y<size; y++){
            winBoard.updateCell("X", new Coordinate3D(1, y, 0));
        }
        return winBoard;
    }

    private Board3D createBoardWithALineFilledVertically_YDirection(int size) {
        Board3D winBoard = new Board3D(size);
        for (int x=0; x<size; x++){
            winBoard.updateCell("X", new Coordinate3D(x, 1, 1));
        }
        return winBoard;
    }

    private Board3D createBoardWithALineFilledVertically_ZDirection(int size) {
        Board3D winBoard = new Board3D(size);
        for (int z=0; z<size; z++){
            winBoard.updateCell("X", new Coordinate3D(1, 1, z));
        }
        return winBoard;
    }

    private Board3D createBoardWithDiagonalLineFilledSize3() {
        Board3D winBoard = new Board3D(3);
        winBoard.updateCell("X", new Coordinate3D(2, 0, 0));
        winBoard.updateCell("X", new Coordinate3D(1, 1, 1));
        winBoard.updateCell("X", new Coordinate3D(0, 2, 2));
        return winBoard;
    }

    private Board3D createBoardWithDiagonalLineFilledSize4() {
        Board3D winBoard = new Board3D(4);
        winBoard.updateCell("X", new Coordinate3D(0, 3, 0));
        winBoard.updateCell("X", new Coordinate3D(1, 2, 1));
        winBoard.updateCell("X", new Coordinate3D(2, 1, 2));
        winBoard.updateCell("X", new Coordinate3D(3, 0, 3));
        return winBoard;
    }

}
