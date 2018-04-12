package Game.Model.ValidatorResult;

public class MoveOutOfBound implements InputValidatorResult {

    @Override
    public String getMessage() {
        return "\nThe coordinate entered is out of bound! Try again...\n";
    }
}
