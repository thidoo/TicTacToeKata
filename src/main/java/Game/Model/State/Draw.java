package Game.Model.State;

public class Draw implements GameState {

    @Override
    public String getMessage() {
        return "\nNo more moves available! It's a draw!\n";
    }
}
