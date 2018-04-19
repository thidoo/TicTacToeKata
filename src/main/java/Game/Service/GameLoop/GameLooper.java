package Game.Service.GameLoop;

import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.TicTacToe.TicTacToe2Players;

import java.io.IOException;

public class GameLooper {

    private boolean isLoop;

    private GameEngine gameEngine;
    private PreGameProcessor preGameProcessor;
    private PostGameProcessor postGameProcessor;

    public GameLooper(GameEngine gameEngine, PreGameProcessor preGameProcessor, PostGameProcessor postGameProcessor) {
        this.gameEngine = gameEngine;
        this.preGameProcessor = preGameProcessor;
        this.postGameProcessor = postGameProcessor;

        this.isLoop = true;
    }

    public void loop() throws CannotConvertToTicTacToeException, IOException {
        while (isLoop){
            isLoop = false;
            TicTacToe2Players inTicTacToe2Players = preGameProcessor.process();
            TicTacToe2Players exitTicTacToe2Players = gameEngine.run(inTicTacToe2Players);
            processPostGame(exitTicTacToe2Players);
        }
    }

    private void processPostGame(TicTacToe2Players ticTacToe2Players){
        if (!ticTacToe2Players.isFinished()){
            postGameProcessor.process(ticTacToe2Players);
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
