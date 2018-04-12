package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Console.ConsolePrinter;
import Game.Model.Console.ConsoleReader;
import Game.Model.Coordinate.Coordinate;
import Game.Model.InputValidatorOutcome;
import Game.Model.State.NotFinished;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.Coordinate.CoordinateConverter;
import Game.Model.Player;
import Game.Model.State.GameState;
import Game.Model.ValidatorResult.Exit;
import Game.Model.ValidatorResult.InputValidatorResult;
import Game.Model.ValidatorResult.ValidMove;

public class Game {

    private ConsoleReader consoleReader;
    private ConsolePrinter consolePrinter;

    private InputValidator inputValidator;
    private GameStateDecider gameStateDecider;
    private CoordinateConverter coordinate2DService;

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private GameBoard board;

    private boolean isRunning;

    public Game(ConsoleReader consoleReader,
                ConsolePrinter consolePrinter,
                InputValidator inputValidator,
                GameStateDecider gameStateDecider,
                Coordinate2DConverter coordinate2DConverter) {

        this.consoleReader = consoleReader;
        this.consolePrinter = consolePrinter;

        this.inputValidator = inputValidator;
        this.gameStateDecider = gameStateDecider;
        this.coordinate2DService = coordinate2DConverter;

        this.isRunning = true;
    }

    public void init(Player player1, Player player2, GameBoard board) {
        this.player1 = player1;
        this.player2 = player2;
        setUpPlayOrder();

        this.board = board;
    }

    public void run() {
        consolePrinter.startGame(board);

        while (isRunning) {
            consolePrinter.prompt(currentPlayer);
            String playerInput = consoleReader.readFromConsole();
            respondToInput(playerInput);
        }
    }

    private void respondToInput(String input) {
        InputValidatorOutcome validatorOutcome = inputValidator.validate(board, input);

        if (validatorOutcome == InputValidatorOutcome.VALID_MOVE) {
            Coordinate coordinate = coordinate2DService.convertToCoordinate(input);

            move(validatorOutcome, coordinate);
            determineGameState(coordinate);
        } else {
            isRunning = !(validatorOutcome == InputValidatorOutcome.EXIT);
            System.out.println(validatorOutcome.message());
        }
    }

    private void move(InputValidatorOutcome validatorOutcome, Coordinate coordinate){
        board.updateCell(this.currentPlayer.getToken(), coordinate);
        consolePrinter.showUpdate(validatorOutcome, board);
    }

    private void determineGameState(Coordinate coordinate) {
        GameState state = gameStateDecider.check(board, coordinate, this.currentPlayer);

        if (state instanceof NotFinished){
            switchPlayer();
        }
        else {
            System.out.println(state.getMessage());
            isRunning = false;
        }
    }

    private void switchPlayer() {
        if (this.currentPlayer.getPlayerOrder() == 1) {
            this.currentPlayer = player2;
        } else {
            this.currentPlayer = player1;
        }
    }

    private void setUpPlayOrder() {
        this.player1.setPlayOrder(1);
        this.player2.setPlayOrder(2);

        this.currentPlayer = this.player1;
    }
}
