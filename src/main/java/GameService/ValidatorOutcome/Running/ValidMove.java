package GameService.ValidatorOutcome.Running;

public class ValidMove extends RunningOutcome {

    @Override
    public String getMessage(){
        return "\nMove accepted, here's the current board:\n";
    }
}
