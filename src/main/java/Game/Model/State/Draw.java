package Game.Model.State;

public class Draw implements GameState {

    @Override
    public String getMessage() {
        return "No more moves available! It's a draw!\n";
    }
}
