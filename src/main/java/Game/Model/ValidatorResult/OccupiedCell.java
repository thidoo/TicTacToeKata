package Game.Model.ValidatorResult;

public class OccupiedCell implements InputValidatorResult {

    @Override
    public String getMessage(){
        return "\nOh no, a piece is already at this place! Try again...\n";
    }
}
