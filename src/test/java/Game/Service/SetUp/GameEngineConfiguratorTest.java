package Game.Service.SetUp;

import Game.Model.Board.GameBoard2D;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Model.TupleStructure.Triplet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameEngineConfiguratorTest {

    @Test
    public void configureTest(){
        GameConfigurator gameConfigurator = new GameConfigurator();

        TicTacToe expectedTicTacToe = new TicTacToe(new Player(1, "X"),
                                                    new Player(2, "O"),
                                                    new GameBoard2D(3));

        TicTacToe actualTicTacToe = gameConfigurator.configure(new Triplet<>("X","O",3));

        // assertThat(expectedTicTacToe, equalTo(actualTicTacToe));
        assertThat(expectedTicTacToe.equals(actualTicTacToe), equalTo(true));
    }

    @Test
    public void configureTest2(){
        GameConfigurator gameConfigurator = new GameConfigurator();
        TicTacToe expectedTicTacToe = new TicTacToe(new Player(1, "T"),
                                                                new Player(2, "U"),
                                                                new GameBoard2D(3));
        TicTacToe actualTicTacToe = gameConfigurator.configure(new Triplet<>("T","U",3));

        assertThat(expectedTicTacToe, equalTo(actualTicTacToe));
    }
}