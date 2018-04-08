package Console;

import Board.GameBoard;
import GameComponent.Player;

public class ConsolePrinter {

    public void printGameStart(GameBoard board){
        System.out.println("Welcome to Tic Tac Toe!\n");
        System.out.println("Here's the current board:\n");
        board.printBoard();
    }

    public void printNextPrompt(Player currentPlayer) {
        System.out.printf("Player %d enter a coord x,y to place your %s or enter 'q' to give up: ", currentPlayer.getPlayerOrder(),
                                                                                                    currentPlayer.getToken());
    }

    //TODO: Get id of other player to announce winning player
    public void printQuit(Player quittingPlayer) {
        System.out.printf("Player %d quit! Player %d won!", quittingPlayer.getPlayerOrder());
    }

    public void printInvalidInput(String playerInput) {
        System.out.printf("You've entered an invalid input: %s\n", playerInput);
    }

    public void printInvalidMove() {
        System.out.println("Oh no, a piece is already at this place! Try again...");
    }

    public void printSuccessfulMove(GameBoard board) {
        System.out.println("Move accepted, here's the current board:\n");
        board.printBoard();
    }

    public void printWinner(Player winner) {
        System.out.printf("Player %d won!\n", winner.getPlayerOrder());
    }

    public void printBoardFull() {
        System.out.println("No more move available!");
    }
}
