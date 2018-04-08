import Board.GameBoard2D;
import Console.ConsolePrinter;
import Console.ConsoleReader;
import GameComponent.Game;
import GameComponent.Player;

public class GameDriver {

    public static void main(String[] args){
        Game game = new Game(new ConsolePrinter(), new ConsoleReader());
        game.init(new Player("X"), new Player("O"), new GameBoard2D(3));

        game.run();
    }
}
