package GameService.ValidatorOutcome.Running;

import GameService.ValidatorOutcome.InputValidatorOutcome;

public abstract class RunningOutcome implements InputValidatorOutcome {

    @Override
    public abstract String getMessage();

}
