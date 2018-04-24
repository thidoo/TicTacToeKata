package Game.Service.GameLoop;

import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.TicTacToe;
import Game.Service.IO.ConsoleWriter;
import Game.Service.IO.InputReader;
import Game.Service.Converter.TicTacToeRepresentationConverter;

import java.io.*;

public class PreGameProcessor {

    static final String SAVED_GAME_FILE_PATH = "./tictactoe.txt";

    private InputReader inputReader;
    private ConsoleWriter consoleWriter;
    private TicTacToeRepresentationConverter ticTacToeRepresentationConverter;
    private Configurator configurator;

    public PreGameProcessor(InputReader inputReader, ConsoleWriter consoleWriter, TicTacToeRepresentationConverter ticTacToeRepresentationConverter, Configurator configurator) {
        this.inputReader = inputReader;
        this.consoleWriter = consoleWriter;
        this.ticTacToeRepresentationConverter = ticTacToeRepresentationConverter;
        this.configurator = configurator;
    }

    public TicTacToe process() throws CannotConvertToTicTacToeException, IOException {
        File file = new File(SAVED_GAME_FILE_PATH);
        TicTacToe ticTacToe;

        if (file.exists()){
            consoleWriter.write("Would you like to load from the previous game?[Y/N]");
            String response = inputReader.read();

            if (response.equals("Y") || response.equals("y")){
                ticTacToe = convertTextToTicTacToe(SAVED_GAME_FILE_PATH);
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

    private TicTacToe convertTextToTicTacToe(String filePath) throws IOException, CannotConvertToTicTacToeException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            return ticTacToeRepresentationConverter.convertStringToTTT(stringBuilder.toString());

        } finally {
            bufferedReader.close();
        }
    }


}
