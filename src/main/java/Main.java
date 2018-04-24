import Game.Game;
import Game.GameBuilder;
import Game.Model.CustomException.CannotConvertToTicTacToeException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CannotConvertToTicTacToeException {

        GameBuilder gameBuilder = new GameBuilder();
        Game game = gameBuilder.build();
        game.run();
    }
}
