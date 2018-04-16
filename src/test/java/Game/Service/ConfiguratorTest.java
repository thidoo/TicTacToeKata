package Game.Service;

import Game.Model.Board.GameBoard2D;
import Game.Model.IO.ConsoleWriter;
import Game.Model.IO.InputReader;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Model.TupleStructure.Triplet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfiguratorTest {

    @Test
    public void allowForCustomisedTokenTest(){
        Configurator configurator = new Configurator(new InputReader(), new ConsoleWriter());

        TicTacToe expectedTicTacToe = new TicTacToe(new Player(1, "X"),
                                                    new Player(2, "O"),
                                                    new GameBoard2D(3));

        TicTacToe actualTicTacToe = configurator.createTicTacToe(new Triplet<>("X", "O", "3"));

        assertThat(actualTicTacToe.getCurrentPlayer(), equalTo(new Player(1, "X")));
        assertThat(actualTicTacToe.getBoard().toString().equals(expectedTicTacToe.getBoard().toString()), equalTo(true));
    }

}