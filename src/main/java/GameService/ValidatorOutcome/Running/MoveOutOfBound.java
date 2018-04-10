package GameService.ValidatorOutcome.Running;

public class MoveOutOfBound extends RunningOutcome {

    @Override
    public String getMessage() {
        return "\nThe coordinate entered is out of bound! Try again...\n";
    }
}
