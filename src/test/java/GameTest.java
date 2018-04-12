import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;
import Game.Model.Console.ConsolePrinter;
import Game.Model.Console.ConsoleReader;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.Game;
import Game.Model.Player;
import Game.Service.GameStateDecider;
import Game.Service.InputValidator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private GameBoard board;


    @Before
    public void setUpGame_With2DBoard_And2Players(){
        game = new Game(new ConsoleReader(),
                        new ConsolePrinter(),
                        new InputValidator(new Coordinate2DConverter()),
                        new GameStateDecider(),
                        new Coordinate2DConverter());

        player1 = new Player("X");
        player2 = new Player("O");
        board = new GameBoard2D(3);

        game.init(player1, player2, board);
        game.run();
    }

    @Test
    public void gameCanDetermine_PlayOrder(){
        assertThat(player1.getPlayerOrder(), equalTo(1));
        assertThat(player2.getPlayerOrder(), equalTo(2));
    }
}
