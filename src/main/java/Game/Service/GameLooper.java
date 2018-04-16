package Game.Service;

import Game.Model.TicTacToe;

public class GameLooper {

    private boolean isLoop;

    private Configurator configurator;
    private GameEngine gameEngine;
    private PostExitProcessor postExitProcessor;

    public GameLooper(Configurator configurator, GameEngine gameEngine, PostExitProcessor postExitProcessor) {
        this.configurator = configurator;
        this.gameEngine = gameEngine;
        this.postExitProcessor = postExitProcessor;

        this.isLoop = true;
    }

    public void loop(){
        while (isLoop){
            isLoop = false;
            TicTacToe inTicTacToe = configurator.configure();
            TicTacToe exitTicTacToe = gameEngine.run(inTicTacToe);
            processPostGame(exitTicTacToe);
        }
    }

    private void processPostGame(TicTacToe ticTacToe){
        if (!ticTacToe.isFinished()){
            postExitProcessor.process(ticTacToe);
        }
        else {
            processLoopingRequest();
        }
    }

    private void processLoopingRequest(){
        gameEngine.getConsoleWriter().write("Would you like to play another game?[Y/N]");
        String response = gameEngine.getInputReader().read().trim();

        boolean validInput = false;
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
