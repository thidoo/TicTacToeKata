package Game.Service;

import Game.Model.TicTacToe;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class PostGameProcessor {

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;
    private StringTTTConverter stringTTTConverter;

    public PostGameProcessor(InputReader inputReader, ConsoleWriter consoleWriter, StringTTTConverter stringTTTConverter) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
        this.stringTTTConverter = stringTTTConverter;
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
            //fileWriter.write("Test");
            fileWriter.write(stringTTTConverter.convertTTTToString(ticTacToe));
            fileWriter.close();
        }
        catch (IOException e){
            consoleWriter.write("Error saving game.");
            e.printStackTrace();
        }
    }
}
