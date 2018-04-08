package State;

import GameComponent.Game;

public interface GameState {

    void doAction(Game game);
    String toString();
}
