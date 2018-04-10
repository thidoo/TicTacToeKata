package GameComponent;

import GameComponent.Board.GameBoard;
import GameComponent.Console.ConsoleReader;
import GameComponent.Coordinate.Coordinate;
import GameComponent.Coordinate.Coordinate2D;
import GameComponent.Coordinate.Coordinate2DService;
import GameComponent.Coordinate.CoordinateService;
import GameService.GameStatusChecker;
import GameService.InputValidator;
import GameService.MatchStatus.Draw;
import GameService.MatchStatus.Status;
import GameService.MatchStatus.Win;
import GameService.ValidatorOutcome.Exit;
import GameService.ValidatorOutcome.InputValidatorOutcome;
import GameService.ValidatorOutcome.Running.InvalidCoordinateFormat;
import GameService.ValidatorOutcome.Running.MoveOutOfBound;
import GameService.ValidatorOutcome.Running.OccupiedCell;
import GameService.ValidatorOutcome.Running.ValidMove;

public class Game {

    private ConsoleReader consoleReader;
    private InputValidator inputValidator;
    private GameStatusChecker gameStatusChecker;
    private CoordinateService coordinate2DService;

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private GameBoard board;

    private boolean isRunning;

    public Game(ConsoleReader consoleReader, InputValidator inputValidator, GameStatusChecker gameStatusChecker, Coordinate2DService coordinate2DService) {
        this.consoleReader = consoleReader;
        this.inputValidator = inputValidator;
        this.gameStatusChecker = gameStatusChecker;
        this.coordinate2DService = coordinate2DService;

        this.isRunning = true;
    }

    public void init(Player player1, Player player2, GameBoard board) {
        this.player1 = player1;
        this.player2 = player2;
        setUpPlayOrder();

        this.board = board;
    }

    public void run() {
        startGame();

        while (isRunning){
            printPrompt();
            String playerInput = consoleReader.readFromConsole();
            respondToInput(playerInput);
        }
    }

    private void startGame(){
        System.out.println("Welcome to Tic Tac Toe!\n");
        System.out.println("Here's the current board:\n");
        board.printBoard();
    }

    private void printPrompt(){
        System.out.printf("Player %d enter a coord x,y to place your %s or enter 'q' to give up: ",
                            currentPlayer.getPlayerOrder(), currentPlayer.getToken());
    }

    private void respondToInput(String input){
        InputValidatorOutcome validatorOutcome = inputValidator.validate(board, input);

        if (validatorOutcome instanceof Exit){
            System.out.println(validatorOutcome.getMessage());
            isRunning = false;
        }
        else {
            if (validatorOutcome instanceof InvalidCoordinateFormat ||
                    validatorOutcome instanceof MoveOutOfBound ||
                    validatorOutcome instanceof OccupiedCell){
                System.out.println(validatorOutcome.getMessage());
            }
            else if (validatorOutcome instanceof ValidMove){
                respondToValidMove(input, validatorOutcome);
            }
        }
    }

    private void respondToValidMove(String input, InputValidatorOutcome validatorOutcome){
        Coordinate coordinate = coordinate2DService.convertToCoordinate(input);

        board.updateCell(this.currentPlayer.getToken(), coordinate);
        System.out.println(validatorOutcome.getMessage());
        board.printBoard();

        determineGameStatus(coordinate);
    }

    private void determineGameStatus(Coordinate coordinate){
        Status gameStatus = gameStatusChecker.check(board, coordinate, this.currentPlayer);

        if (gameStatus instanceof Win){
            System.out.println(gameStatus.getMessage());
            isRunning = false;
        }
        else if (gameStatus instanceof Draw){
            System.out.println(gameStatus.getMessage());
            isRunning = false;
        }
        else {
            switchPlayer();
        }
    }

    private void switchPlayer() {
        if (this.currentPlayer.getPlayerOrder() == 1){
            this.currentPlayer = player2;
        }
        else{
            this.currentPlayer = player1;
        }
    }

    private void setUpPlayOrder(){
        this.player1.setPlayOrder(1);
        this.player2.setPlayOrder(2);

        this.currentPlayer = this.player1;
    }
}
