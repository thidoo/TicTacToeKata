package Game.Service;

import Game.Model.Board.Board2D;
import Game.Model.Coordinate.Coordinate2D;
import Game.Model.InputValidatorResult;
import Game.Model.TicTacToe;
import Game.Model.TupleStructure.Pair;
import Game.Model.Player;
import Game.Service.Board.Board2DService;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.GameLoop.InputProcessor;
import Game.Service.GameLoop.InputValidator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputProcessorTest {

    private InputProcessor inputProcessor;

    @Before
    public void setUp(){
        inputProcessor = new InputProcessor(new InputValidator(new Coordinate2DConverter()),
                                            new StateDecider(),
                                            new Board2DService(),
                                            new Coordinate2DConverter());
    }

    @Test
    public void exit_ShouldStopGame(){
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                        new Player(2, "O"),
                                                        new Board2D(3));
        inputTicTacToe.setIsPlaying(true);

        Pair processorInput = new Pair<>("q", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.EXIT.message()));
        assertThat(outTicTacToe.isPlaying(), equalTo(false));
    }

    @Test
    public void inValidCoordinateFormatTest(){
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new Board2D(3));
        inputTicTacToe.setIsPlaying(true);

        Pair processorInput = new Pair<>("12", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.INVALID_COORD_FORMAT.message()));
        assertThat(outTicTacToe.isPlaying(), equalTo(true));
    }

    @Test
    public void outOfBoundMoveTest(){
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new Board2D(3));
        inputTicTacToe.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,5", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.MOVE_OUT_OF_BOUND.message()));
        assertThat(outTicTacToe.isPlaying(), equalTo(true));
    }

    @Test
    public void occupiedCellTest(){
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new Board2D(3));
        inputTicTacToe.setIsPlaying(true);
        inputTicTacToe.getBoard().updateCell("O", new Coordinate2D(0,0));

        Pair processorInput = new Pair<>("1,1", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.OCCUPIED_CELL.message()));
        assertThat(outTicTacToe.isPlaying(), equalTo(true));
    }

    @Test
    public void validMoveTest_emptyBoardStart(){
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new Board2D(3));
        inputTicTacToe.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,2", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.VALID_MOVE.message() + "\n" + ". X . \n. . . \n. . . \n"));
        assertThat(outTicTacToe.isPlaying(), equalTo(true));
    }

    @Test
    public void validMoveTest_occupiedBoardStart(){
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new Board2D(3));
        inputTicTacToe.setIsPlaying(true);
        inputTicTacToe.getBoard().updateCell("O", new Coordinate2D(2,2));

        Pair processorInput = new Pair<>("1,2", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.VALID_MOVE.message() + "\n" + ". X . \n. . . \n. . O \n"));
        assertThat(outTicTacToe.isPlaying(), equalTo(true));
    }

    @Test
    public void winTest(){
        Board2D winBoard = createWinBoard_HorizontalLineFilled();
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                winBoard);
        inputTicTacToe.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,2", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(("\nMove accepted, here's the current board:\n" + "\n"
                                        + outTicTacToe.getBoard().toString() + "\n"
                                        + "Player 1 won!\n")));
        assertThat(outTicTacToe.isPlaying(), equalTo(false));
    }

    @Test
    public void drawTest(){
        Board2D fullBoard = createFullBoard();
        TicTacToe inputTicTacToe = new TicTacToe(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                fullBoard);
        inputTicTacToe.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,2", inputTicTacToe);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe outTicTacToe = (TicTacToe) processorOutput.getRight();

        assertThat(outMessage, equalTo(("\nMove accepted, here's the current board:\n" + "\n"
                                        + outTicTacToe.getBoard().toString() + "\n"
                                        + "No more moves available! It's a draw!\n")));
        assertThat(outTicTacToe.isPlaying(), equalTo(false));
    }

    private Board2D createWinBoard_HorizontalLineFilled(){
        Board2D winBoard = new Board2D(3);
        winBoard.updateCell("X", new Coordinate2D(0,0));
        winBoard.updateCell("X", new Coordinate2D(0,2));
        return winBoard;
    }

    private Board2D createFullBoard(){
        Board2D fullBoard = new Board2D(3);
        fullBoard.updateCell("O", new Coordinate2D(0,0));
        fullBoard.updateCell("O", new Coordinate2D(0,2));
        fullBoard.updateCell("X", new Coordinate2D(1,0));
        fullBoard.updateCell("X", new Coordinate2D(1,1));
        fullBoard.updateCell("O", new Coordinate2D(1,2));
        fullBoard.updateCell("O", new Coordinate2D(2,0));
        fullBoard.updateCell("O", new Coordinate2D(2,1));
        fullBoard.updateCell("X", new Coordinate2D(2,2));

        return fullBoard;
    }

}
