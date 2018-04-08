package GameComponent;

import Console.ConsolePrinter;
import Board.GameBoard;
import Console.ConsoleReader;
import Coordinate.Coordinate;
import Coordinate.Coordinate2D;

public class Game {

    private static final String QUIT_KEY = "q";

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private GameBoard board;

    private ConsolePrinter consolePrinter;
    private ConsoleReader consoleReader;

    private boolean isRunning;

    public Game(ConsolePrinter consolePrinter, ConsoleReader consoleReader) {
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;

        this.isRunning = true;
    }

    public void init(Player player1, Player player2, GameBoard board) {
        this.player1 = player1;
        this.player2 = player2;
        setUpPlayOrder();

        this.board = board;
    }

    public Player run() {
        consolePrinter.printGameStart(board);

        while (isRunning){
            consolePrinter.printNextPrompt(this.currentPlayer);
            String playerInput = consoleReader.readFromConsole();
            respondToInput(playerInput);
        }

        //TODO
        //finish()
        return player1;
    }

    private void respondToInput(String playerInput){
        if (playerInput.equals(QUIT_KEY)){
            consolePrinter.printQuit(this.currentPlayer);
            isRunning = false;
        }
        else {
            if (Coordinate2D.isCoordinate2D(playerInput)){
                processInput(playerInput);
            }
            else {
                consolePrinter.printInvalidInput(playerInput);
            }
        }
    }

    private void processInput(String playerInput){
        Coordinate2D coordinate = Coordinate2D.convertToCoordinate2D(playerInput);

        if (isValidMove(coordinate)){

            board.updateCell(this.currentPlayer.getToken(), coordinate);
            determineIfGameShouldKeepRunning(coordinate);
            consolePrinter.printSuccessfulMove(board);
            switchPlayer();
        }
        else {
            consolePrinter.printInvalidMove();
        }
    }

    private void determineIfGameShouldKeepRunning(Coordinate coordinate){
        if (board.hasFilledLine(coordinate)){
            consolePrinter.printWinner(this.currentPlayer);
            isRunning = false;
        }

        if (board.isFull()){
            consolePrinter.printBoardFull();
            isRunning = false;
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

    private boolean isValidMove(Coordinate coordinate){
        return board.contains(coordinate) && board.isEmptyAtPosition(coordinate);
    }

    private void setUpPlayOrder(){
        this.player1.setPlayOrder(1);
        this.player2.setPlayOrder(2);

        this.currentPlayer = this.player1;
    }
}
