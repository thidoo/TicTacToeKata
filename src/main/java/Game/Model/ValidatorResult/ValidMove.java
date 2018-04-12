package Game.Model.ValidatorResult;

public class ValidMove implements InputValidatorResult {

    @Override
    public String getMessage(){
        return "\nMove accepted, here's the current board:\n";
    }
}
