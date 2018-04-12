package Game.Model.ValidatorResult;

public class Exit implements InputValidatorResult {

    @Override
    public String getMessage() {
        return "\nGame exiting...";
    }
}
