package GameService.ValidatorOutcome.Running;

public class OccupiedCell extends RunningOutcome {

    @Override
    public String getMessage(){
        return "\nOh no, a piece is already at this place! Try again...\n";
    }
}
