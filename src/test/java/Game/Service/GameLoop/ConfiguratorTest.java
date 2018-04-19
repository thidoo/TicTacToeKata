package Game.Service.GameLoop;

import Game.Model.Board.GameBoard2D;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Model.Player;
import Game.Model.TicTacToe.TicTacToe2Players;
import Game.Model.TupleStructure.Triplet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfiguratorTest {

    @Test
    public void allowForCustomisedTokenTest(){
        Configurator configurator = new Configurator(new InputReader(), new ConsoleWriter());

        TicTacToe2Players expectedTicTacToe2Players = new TicTacToe2Players(new Player(1, "X"),
                                                    new Player(2, "O"),
                                                    new GameBoard2D(3));

        TicTacToe2Players actualTicTacToe2Players = configurator.createTicTacToe(new Triplet<>("X", "O", "3"));

        assertThat(actualTicTacToe2Players.getCurrentPlayer(), equalTo(new Player(1, "X")));
        assertThat(actualTicTacToe2Players.getBoard().toString().equals(expectedTicTacToe2Players.getBoard().toString()), equalTo(true));
    }

    @Test
    public void allowForCustomisedTokenTest2(){
        Configurator configurator = new Configurator(new InputReader(), new ConsoleWriter());

        TicTacToe2Players expectedTicTacToe2Players = new TicTacToe2Players(new Player(1, "O"),
                new Player(2, "X"),
                new GameBoard2D(4));

        TicTacToe2Players actualTicTacToe2Players = configurator.createTicTacToe(new Triplet<>("O", "X", "4"));

        assertThat(actualTicTacToe2Players.getCurrentPlayer(), equalTo(new Player(1, "O")));
        assertThat(actualTicTacToe2Players.getBoard().toString().equals(expectedTicTacToe2Players.getBoard().toString()), equalTo(true));
    }

}