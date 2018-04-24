package Game.Model.GameResult;

public enum InputValidatorResult {
    EXIT("\nYou've chosen to terminate this game.\n"),
    INVALID_COORD_FORMAT("\nInvalid coordinate format! Try again...\n"),
    MOVE_OUT_OF_BOUND("\nThe coordinate entered is out of bound! Try again...\n"),
    OCCUPIED_CELL("\nOh no, a piece is already at this place! Try again...\n"),
    VALID_MOVE("\nMove accepted, here's the current board:\n");

    private String message;

    InputValidatorResult(String message) {
        this.message = message;
    }

    public String message(){
        return message;
    }
}
