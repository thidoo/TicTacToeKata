package GameService.ValidatorOutcome;

public class Exit implements InputValidatorOutcome {

    @Override
    public String getMessage() {
        return "\nGame exiting...";
    }
}
