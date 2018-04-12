package Game.Model.ValidatorResult;

public class InvalidCoordinateFormat implements InputValidatorResult {
    @Override
    public String getMessage() {
        return "\nInvalid coordinate format! Try again...\n";
    }
}
