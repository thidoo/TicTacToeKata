package Game.Service.Board;

import Game.Model.Board.Board2D;
import Game.Model.Board.Board3D;
import Game.Model.Coordinate.Coordinate3D;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlaneSplitterTest {

    private PlaneSplitter planeSplitter;

    @Before
    public void setUp(){
        planeSplitter = new PlaneSplitter();
    }

    @Test
    public void splitXYTest(){
        Board2DService board2DService = new Board2DService();

        Board3D testBoard = createTestBoard();

        Board2D[] expectedXYPlanes = new Board2D[testBoard.getSize()];
        expectedXYPlanes[0] = (Board2D) board2DService.convertStringToBoard("X . . \n. . . \n. . . \n");
        expectedXYPlanes[1] = (Board2D) board2DService.convertStringToBoard(". . . \n. . O \n. . . \n");
        expectedXYPlanes[2] = (Board2D) board2DService.convertStringToBoard(". . . \n. . . \n. O . \n");

        Board2D[] actualXYPlanes = planeSplitter.splitXY(testBoard);

        assertThat(equal2DBoardArrays(actualXYPlanes, expectedXYPlanes), equalTo(true));
    }

    @Test
    public void splitXZTest(){
        Board2DService board2DService = new Board2DService();
        Board3D testBoard = createTestBoard();

        Board2D[] expectedXZPlanes = new Board2D[testBoard.getSize()];
        expectedXZPlanes[0] = (Board2D) board2DService.convertStringToBoard("X . . \n. . . \n. . . \n");
        expectedXZPlanes[1] = (Board2D) board2DService.convertStringToBoard(". . . \n. . . \n. . O \n");
        expectedXZPlanes[2] = (Board2D) board2DService.convertStringToBoard(". . . \n. O . \n. . . \n");

        Board2D[] actualXZPlanes = planeSplitter.splitXZ(testBoard);

        assertThat(equal2DBoardArrays(actualXZPlanes, expectedXZPlanes), equalTo(true));
    }

    @Test
    public void splitYZTest(){
        Board2DService board2DService = new Board2DService();
        Board3D testBoard = createTestBoardForYZPlanes();

        Board2D[] expectedYZPlanes = new Board2D[testBoard.getSize()];
        expectedYZPlanes[0] = (Board2D) board2DService.convertStringToBoard("X . . \n. . O \n. . . \n");
        expectedYZPlanes[1] = (Board2D) board2DService.convertStringToBoard(". . . \n. . . \nO . . \n");
        expectedYZPlanes[2] = (Board2D) board2DService.convertStringToBoard(". . . \n. . . \n. . O \n");

        Board2D[] actualYZPlanes = planeSplitter.splitYZ(testBoard);

        assertThat(equal2DBoardArrays(actualYZPlanes, expectedYZPlanes), equalTo(true));
    }

    private Board3D createTestBoard(){
        Board3D board = new Board3D(3);
        board.updateCell("X", new Coordinate3D(0,0,0));
        board.updateCell("O", new Coordinate3D(1,2,1));
        board.updateCell("O", new Coordinate3D(2,1,2));
        return board;
    }

    private Board3D createTestBoardForYZPlanes(){
        Board3D board = new Board3D(3);
        board.updateCell("X", new Coordinate3D(0,0,0));
        board.updateCell("O", new Coordinate3D(0,2,1));
        board.updateCell("O", new Coordinate3D(1,0,2));
        board.updateCell("O", new Coordinate3D(2,2,2));
        return board;
    }



    private boolean equal2DBoardArrays(Board2D[] array1, Board2D[] array2){
        int size = array1.length;

        for (int height=0; height<size; height++){
            if (!equal2DBoards(array1[height], array2[height])){
                return false;
            }
        }
        return true;
    }

    private boolean equal2DBoards(Board2D board1, Board2D board2){
        if (board1.getSize() == board2.getSize()){
            for (int x = 0; x < board1.getSize(); x++){
                for (int y =0; y < board2.getSize(); y++){
                    if (!(board1.getContent()[x][y].getToken().equals(board2.getContent()[x][y].getToken()))){
                        return false;
                    }
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    private void print2DBoardArray(Board2D[] array){
        for (int height = 0; height < array.length; height++){
            System.out.println(array[height].toString());
        }
    }
}
