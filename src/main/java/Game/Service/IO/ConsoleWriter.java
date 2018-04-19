package Game.Service.IO;

import Game.Model.Board.GameBoard;
import Game.Model.Player;

public class ConsoleWriter {

    public void write(String string){
        System.out.println(string);
    }

    public void startGame(GameBoard board){
        System.out.println("\nWelcome to Tic Tac Toe!\n");
        System.out.println("Here's the current board:\n");
        board.printBoard();
    }

    public void prompt(Player player){
        System.out.printf("Player %d enter a coord x,y to place your %s or enter 'q' to give up: ",
                player.getPlayerOrder(), player.getToken());
    }
}
