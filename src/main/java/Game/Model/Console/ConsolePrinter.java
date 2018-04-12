package Game.Model.Console;

import Game.Model.Board.GameBoard;
import Game.Model.InputValidatorOutcome;
import Game.Model.Player;

public class ConsolePrinter {

    public void startGame(GameBoard board){
        System.out.println("Welcome to Tic Tac Toe!\n");
        System.out.println("Here's the current board:\n");
        board.printBoard();
    }

    public void prompt(Player player){
        System.out.printf("Player %d enter a coord x,y to place your %s or enter 'q' to give up: ",
                player.getPlayerOrder(), player.getToken());
    }

    public void showUpdate(InputValidatorOutcome validatorOutcome, GameBoard board) {
        System.out.println(validatorOutcome.message());
        board.printBoard();
    }
}
