package GameService.ValidatorOutcome.Running;

public class InvalidCoordinateFormat extends RunningOutcome {
    @Override
    public String getMessage() {
        return "\nInvalid coordinate format! Try again...\n";
    }
}
