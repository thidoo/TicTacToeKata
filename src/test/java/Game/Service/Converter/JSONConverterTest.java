package Game.Service.Converter;

import Game.Model.Board.Board2D;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Service.Board.Board2DService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONConverterTest {

    @Test
    public void convertTicTacToeObject_toJSONStringTest(){
        JSONConverter jsonConverter = new JSONConverter(new Board2DService());
        TicTacToe ticTacToe = new TicTacToe(new Player(1, "X"),
                new Player(2, "O"),
                new Board2D(3));

        String expectedJSON = "{\"player1\":\"X\"," +
                "\"player2\":\"O\"," +
                "\"currentPlayer\":\"X\"," +
                "\"board\":\". . . \\n. . . \\n. . . \\n\"}";

        String actualJSON = jsonConverter.convertToJSONString(ticTacToe);

        assertThat(actualJSON, equalTo(expectedJSON));
    }
}