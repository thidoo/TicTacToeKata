import GameComponent.Board.GameBoard;
import GameComponent.Board.GameBoard2D;
import GameComponent.Console.ConsoleReader;
import GameComponent.Coordinate.Coordinate2DService;
import GameComponent.Game;
import GameComponent.Player;
import GameService.GameStatusChecker;
import GameService.InputValidator;
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
        game = new Game(new ConsoleReader(), new InputValidator(new Coordinate2DService()),
                        new GameStatusChecker(), new Coordinate2DService());

        player1 = new Player("X");
        player2 = new Player("O");
        board = new GameBoard2D(3);

        game.init(player1, player2, board);
    }

    @Test
    public void gamePrintsWelcomeMessage_AndEmptyBoard_WhenFirstStarts(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        game.run();

        String expectedGameStartString = ("Welcome to Tic Tac Toe!\n\n" +
                                            "Here's the current board:\n\n" +
                                            ". . . \n. . . \n. . . \n\n");
        assertThat(outContent.toString(), equalTo(expectedGameStartString));

        System.setOut(System.out);
    }

    @Test
    public void gameCanDetermine_PlayOrder(){
        assertThat(player1.getPlayerOrder(), equalTo(1));
        assertThat(player2.getPlayerOrder(), equalTo(2));
    }
}
