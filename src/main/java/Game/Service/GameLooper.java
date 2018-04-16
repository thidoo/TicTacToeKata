package Game.Service;

import Game.GameEngine;
import Game.Model.TicTacToe;

public class GameLooper {

    private boolean isLoop;

    private Configurator configurator;
    private GameEngine gameEngine;

    public GameLooper(Configurator configurator, GameEngine gameEngine) {
        this.configurator = configurator;
        this.gameEngine = gameEngine;

        this.isLoop = true;
    }

    public void loop(){
        while (isLoop){
            isLoop = false;
            TicTacToe ticTacToe = configurator.configure();
            gameEngine.run(ticTacToe);
            processLoopingRequest();
        }
    }

    private void processLoopingRequest(){
        gameEngine.getConsoleWriter().write("Would you like to play another game?[Y/N]");
        boolean validInput = false;
        String response = gameEngine.getInputReader().read().trim();

        while (!validInput){
            if (response.equals("Y") || response.equals("y")){
                this.isLoop = true;
                validInput = true;
            }
            else if (response.equals("N") || response.equals("n")){
                this.isLoop = false;
                validInput = true;
            }
            else {
                gameEngine.getConsoleWriter().write("Please enter a valid response [Y/N]");
            }
        }
    }
}
