package Game.Service.GameLoop;

import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.TicTacToe.TicTacToe2D;

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
            TicTacToe2D inTicTacToe2D = preGameProcessor.process();
            TicTacToe2D exitTicTacToe2D = gameEngine.run(inTicTacToe2D);
            processPostGame(exitTicTacToe2D);
        }
    }

    private void processPostGame(TicTacToe2D ticTacToe2D){
        if (!ticTacToe2D.isFinished()){
            postGameProcessor.process(ticTacToe2D);
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
