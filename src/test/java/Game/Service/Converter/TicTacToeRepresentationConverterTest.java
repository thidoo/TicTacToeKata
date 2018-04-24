package Game.Service.Converter;

import Game.Model.Board.Board2D;
import Game.Model.Coordinate.Coordinate2D;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Service.Board.Board2DService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TicTacToeRepresentationConverterTest {

    private TicTacToeRepresentationConverter ticTacToeRepresentationConverter;

    @Before
    public void setUp() throws Exception {
        ticTacToeRepresentationConverter = new TicTacToeRepresentationConverter(new Board2DService());
    }

    @Test
    public void convertTTTtoStringTest_EmptyBoardCase(){
        TicTacToe ticTacToe = new TicTacToe(new Player(1, "X"),
                new Player(2, "O"),
                new Board2D(3));

        String expected = "X,O,X,. . . \n. . . \n. . . \n";
        String actual = ticTacToeRepresentationConverter.convertTTTToString(ticTacToe);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void convertTTTtoStringTest_BoardWithOneTokenPlacedCase(){
        TicTacToe ticTacToe = createTicTacToe2DWithOneTokenPlaced();

        String expected = "X,O,X,X . . \n. . . \n. . . \n";
        String actual = ticTacToeRepresentationConverter.convertTTTToString(ticTacToe);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void convertToStringToTTT() throws CannotConvertToTicTacToeException {
        String ticTacToe = "X,O,X,X . . \n. . . \n. . . \n";
        TicTacToe expected = createTicTacToe2DWithOneTokenPlaced();
        TicTacToe actual = ticTacToeRepresentationConverter.convertStringToTTT(ticTacToe);

        assertThat(actual.getCurrentPlayer().getToken(), equalTo(expected.getCurrentPlayer().getToken()));
        assertThat(actual.getBoard().toString(), equalTo(expected.getBoard().toString()));
    }

    private TicTacToe createTicTacToe2DWithOneTokenPlaced() {
        return new TicTacToe(new Player(1, "X"),
                new Player(2, "O"),
                createBoardWithOneTokenPlaced(3));
    }

    private Board2D createBoardWithOneTokenPlaced(int size){
        Board2D board = new Board2D(size);
        board.updateCell("X", new Coordinate2D(0,0));

        return board;
    }
}
