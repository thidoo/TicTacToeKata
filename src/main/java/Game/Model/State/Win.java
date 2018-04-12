package Game.Model.State;

import Game.Model.Player;

public class Win implements Status {

    private Player winner;

    public Win(Player winner) {
        this.winner = winner;
    }

    @Override
    public String getMessage() {
        String message = String.format("Player %d won!\n", winner.getPlayerOrder());
        return message;
    }
}
