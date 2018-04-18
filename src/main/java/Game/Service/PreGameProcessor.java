package Game.Service;

import Game.Model.CannotConvertToTicTacToeException;
import Game.Model.TicTacToe;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;

import java.io.*;

public class PreGameProcessor {

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;
    private StringTTTConverter stringTTTConverter;
    private Configurator configurator;

    public PreGameProcessor(InputReader inputReader, ConsoleWriter consoleWriter, StringTTTConverter stringTTTConverter, Configurator configurator) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
        this.stringTTTConverter = stringTTTConverter;
        this.configurator = configurator;
    }

    public TicTacToe process() throws CannotConvertToTicTacToeException, IOException {
        File file = new File("./tictactoe.txt");
        TicTacToe ticTacToe;

        if (file.exists()){
            consoleWriter.write("Would you like to load from the previous game?[Y/N]");
            String response = inputReader.read();
            if (response.equals("Y") || response.equals("y")){
                BufferedReader reader = new BufferedReader(new FileReader("./tictactoe.txt"));
                String text = readText(reader);
                ticTacToe = stringTTTConverter.convertStringToTTT(text);
                file.delete();
            }
            else {
                ticTacToe = configurator.configure();
            }
        }
        else {
            ticTacToe = configurator.configure();
        }

        return ticTacToe;
    }

    private String readText(BufferedReader reader) throws IOException {
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }


}
