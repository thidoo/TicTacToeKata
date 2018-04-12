import Game.Model.Board.GameBoard2D;
import Game.Model.Console.ConsolePrinter;
import Game.Model.Console.ConsoleReader;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.Game;
import Game.Service.GameStateDecider;
import Game.Service.InputValidator;
import Game.Model.Player;

public class GameDriver {

    public static void main(String[] args){
        Coordinate2DConverter coordinateConverter = new Coordinate2DConverter();

        Game game = new Game(new ConsoleReader(),
                                new ConsolePrinter(),
                                new InputValidator(coordinateConverter),
                                new GameStateDecider(),
                                coordinateConverter);

        game.init(new Player("X"), new Player("O"), new GameBoard2D(3));
        game.run();
    }
}
