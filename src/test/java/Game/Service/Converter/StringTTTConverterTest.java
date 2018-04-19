package Game.Service.Converter;

import Game.Model.Board.GameBoard2D;
import Game.Model.Coordinate.Coordinate2D;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.Player;
import Game.Model.TicTacToe.TicTacToe2Players;
import Game.Service.Board.GameBoard2DService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringTTTConverterTest {

    private StringTTTConverter stringTTTConverter;

    @Before
    public void setUp() throws Exception {
        stringTTTConverter = new StringTTTConverter(new GameBoard2DService());
    }

    @Test
    public void convertTTTtoStringTest_EmptyBoardCase(){
        TicTacToe2Players ticTacToe = new TicTacToe2Players(new Player(1, "X"),
                new Player(2, "O"),
                new GameBoard2D(3));

        String expected = "X,O,X,. . . \n. . . \n. . . \n";
        String actual = stringTTTConverter.convertTTTToString(ticTacToe);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void convertTTTtoStringTest_BoardWithOneTokenPlacedCase(){
        TicTacToe2Players ticTacToe = createTicTacToe2DWithOneTokenPlaced();

        String expected = "X,O,X,X . . \n. . . \n. . . \n";
        String actual = stringTTTConverter.convertTTTToString(ticTacToe);

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void convertToStringToTTT() throws CannotConvertToTicTacToeException {
        String ticTacToe = "X,O,X,X . . \n. . . \n. . . \n";
        TicTacToe2Players expected = createTicTacToe2DWithOneTokenPlaced();
        TicTacToe2Players actual = stringTTTConverter.convertStringToTTT(ticTacToe);

        assertThat(actual.getCurrentPlayer().getToken(), equalTo(expected.getCurrentPlayer().getToken()));
        assertThat(actual.getBoard().toString(), equalTo(expected.getBoard().toString()));
    }

    private TicTacToe2Players createTicTacToe2DWithOneTokenPlaced() {
        return new TicTacToe2Players(new Player(1, "X"),
                new Player(2, "O"),
                createBoardWithOneTokenPlaced(3));
    }

    private GameBoard2D createBoardWithOneTokenPlaced(int size){
        GameBoard2D board = new GameBoard2D(size);
        board.updateCell("X", new Coordinate2D(0,0));

        return board;
    }
}
