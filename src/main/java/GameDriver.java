import GameComponent.Board.GameBoard2D;
import GameComponent.Console.ConsoleReader;
import GameComponent.Coordinate.Coordinate2DService;
import GameComponent.Game;
import GameService.GameStatusChecker;
import GameService.InputValidator;
import GameComponent.Player;

public class GameDriver {

    public static void main(String[] args){
        Coordinate2DService coordinateService = new Coordinate2DService();

        Game game = new Game(new ConsoleReader(),
                                new InputValidator(coordinateService),
                                new GameStatusChecker(),
                                coordinateService);

        game.init(new Player("X"), new Player("O"), new GameBoard2D(3));
        game.run();
    }
}
