package Game.Service;

import Game.Model.Board.GameBoard2D;
import Game.Model.Coordinate.Coordinate2D;
import Game.Model.InputValidatorResult;
import Game.Model.TicTacToe.TicTacToe2D;
import Game.Model.TupleStructure.Pair;
import Game.Model.Player;
import Game.Service.Coordinate.Coordinate2DConverter;
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
                                            new Coordinate2DConverter());
    }

    @Test
    public void exit_ShouldStopGame(){
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                        new Player(2, "O"),
                                                        new GameBoard2D(3));
        inputTicTacToe2D.setIsPlaying(true);

        Pair processorInput = new Pair<>("q", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.EXIT.message()));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(false));
    }

    @Test
    public void inValidCoordinateFormatTest(){
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new GameBoard2D(3));
        inputTicTacToe2D.setIsPlaying(true);

        Pair processorInput = new Pair<>("12", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.INVALID_COORD_FORMAT.message()));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(true));
    }

    @Test
    public void outOfBoundMoveTest(){
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new GameBoard2D(3));
        inputTicTacToe2D.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,5", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.MOVE_OUT_OF_BOUND.message()));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(true));
    }

    @Test
    public void occupiedCellTest(){
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new GameBoard2D(3));
        inputTicTacToe2D.setIsPlaying(true);
        inputTicTacToe2D.getBoard().updateCell("O", new Coordinate2D(0,0));

        Pair processorInput = new Pair<>("1,1", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.OCCUPIED_CELL.message()));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(true));
    }

    @Test
    public void validMoveTest_emptyBoardStart(){
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new GameBoard2D(3));
        inputTicTacToe2D.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,2", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.VALID_MOVE.message() + "\n" + ". X . \n. . . \n. . . \n"));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(true));
    }

    @Test
    public void validMoveTest_occupiedBoardStart(){
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                new GameBoard2D(3));
        inputTicTacToe2D.setIsPlaying(true);
        inputTicTacToe2D.getBoard().updateCell("O", new Coordinate2D(2,2));

        Pair processorInput = new Pair<>("1,2", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(InputValidatorResult.VALID_MOVE.message() + "\n" + ". X . \n. . . \n. . O \n"));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(true));
    }

    @Test
    public void winTest(){
        GameBoard2D winBoard = createWinBoard_HorizontalLineFilled();
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                winBoard);
        inputTicTacToe2D.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,2", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(("\nMove accepted, here's the current board:\n" + "\n"
                                        + outTicTacToe2D.getBoard().toString() + "\n"
                                        + "Player 1 won!\n")));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(false));
    }

    @Test
    public void drawTest(){
        GameBoard2D fullBoard = createFullBoard();
        TicTacToe2D inputTicTacToe2D = new TicTacToe2D(new Player(1, "X"),
                                                                new Player(2, "O"),
                                                                fullBoard);
        inputTicTacToe2D.setIsPlaying(true);

        Pair processorInput = new Pair<>("1,2", inputTicTacToe2D);
        Pair processorOutput = inputProcessor.process(processorInput);

        String outMessage = (String) processorOutput.getLeft();
        TicTacToe2D outTicTacToe2D = (TicTacToe2D) processorOutput.getRight();

        assertThat(outMessage, equalTo(("\nMove accepted, here's the current board:\n" + "\n"
                                        + outTicTacToe2D.getBoard().toString() + "\n"
                                        + "No more moves available! It's a draw!\n")));
        assertThat(outTicTacToe2D.isPlaying(), equalTo(false));
    }

    private GameBoard2D createWinBoard_HorizontalLineFilled(){
        GameBoard2D winBoard = new GameBoard2D(3);
        winBoard.updateCell("X", new Coordinate2D(0,0));
        winBoard.updateCell("X", new Coordinate2D(0,2));
        return winBoard;
    }

    private GameBoard2D createFullBoard(){
        GameBoard2D fullBoard = new GameBoard2D(3);
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
