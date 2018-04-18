package Game.Service.GameLoop;

import Game.Model.TicTacToe;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Service.Converter.StringTTTConverter;

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
            FileWriter fileWriter = new FileWriter(PreGameProcessor.SAVED_GAME_FILE_PATH);
            fileWriter.write(stringTTTConverter.convertTTTToString(ticTacToe));
            fileWriter.close();
        }
        catch (IOException e){
            consoleWriter.write("Error saving game.");
            e.printStackTrace();
        }
    }
}
