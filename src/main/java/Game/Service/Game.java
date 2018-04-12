package Game.Service;

import Game.Model.Board.GameBoard;
import Game.Model.Console.ConsolePrinter;
import Game.Model.Console.ConsoleReader;
import Game.Model.Coordinate.Coordinate;
import Game.Model.State.NotFinished;
import Game.Service.Coordinate.Coordinate2DConverter;
import Game.Service.Coordinate.CoordinateConverter;
import Game.Model.Player;
import Game.Model.State.Draw;
import Game.Model.State.Status;
import Game.Model.State.Win;
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
        InputValidatorResult validatorOutcome = inputValidator.validate(board, input);

        if (validatorOutcome instanceof ValidMove) {
            Coordinate coordinate = coordinate2DService.convertToCoordinate(input);

            move(validatorOutcome, coordinate);
            determineGameStatus(coordinate);
        } else {
            isRunning = !(validatorOutcome instanceof Exit);
            System.out.println(validatorOutcome.getMessage());
        }
    }

    private void move(InputValidatorResult validatorOutcome, Coordinate coordinate){
        board.updateCell(this.currentPlayer.getToken(), coordinate);
        consolePrinter.showUpdate(validatorOutcome, board);
    }

    private void determineGameStatus(Coordinate coordinate) {
        Status gameStatus = gameStateDecider.check(board, coordinate, this.currentPlayer);

        if (gameStatus instanceof NotFinished){
            switchPlayer();
        }
        else {
            System.out.println(gameStatus.getMessage());
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
