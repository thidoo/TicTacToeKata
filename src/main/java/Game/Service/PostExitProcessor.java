package Game.Service;

import Game.Model.TicTacToe;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;

import java.io.FileWriter;
import java.io.IOException;

public class PostExitProcessor {

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;

    public PostExitProcessor(InputReader inputReader, ConsoleWriter consoleWriter) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
    }

    public void process(TicTacToe ticTacToe){
        consoleWriter.write("Would you like to save this game?[Y/N]\n");
        String response = inputReader.read().trim();

        if (response.equals("Y") || response.equals("y")){
            saveCurrentGame(ticTacToe);
        }
    }

    private void saveCurrentGame(TicTacToe ticTacToe) {
        try {
            FileWriter fileWriter = new FileWriter("tictactoe.txt");
            fileWriter.write("Hello World!");
            fileWriter.close();
        }
        catch (IOException e){
            consoleWriter.write("Error saving game.");
            e.printStackTrace();
        }
    }
}
