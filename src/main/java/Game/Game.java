package Game;

import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.TicTacToe;
import Game.Service.GameLoop.GameProcessor;
import Game.Service.GameLoop.PostGameProcessor;
import Game.Service.GameLoop.PreGameProcessor;

import java.io.IOException;

public class Game {

    private boolean isLoop;

    private GameProcessor gameProcessor;
    private PreGameProcessor preGameProcessor;
    private PostGameProcessor postGameProcessor;

    public Game(GameProcessor gameProcessor, PreGameProcessor preGameProcessor, PostGameProcessor postGameProcessor) {
        this.gameProcessor = gameProcessor;
        this.preGameProcessor = preGameProcessor;
        this.postGameProcessor = postGameProcessor;

        this.isLoop = true;
    }

    public void run() throws CannotConvertToTicTacToeException, IOException {
        while (isLoop){
            isLoop = false;
            TicTacToe inTicTacToe = preGameProcessor.process();
            TicTacToe exitTicTacToe = gameProcessor.play(inTicTacToe);
            processPostGame(exitTicTacToe);
        }
    }

    private void processPostGame(TicTacToe ticTacToe){
        if (!ticTacToe.isFinished()){
            postGameProcessor.process(ticTacToe);
        }
        else {
            processLoopingRequest();
        }
    }

    private void processLoopingRequest(){
        gameProcessor.getConsoleWriter().write("Would you like to play another game?[Y/N]");
        String response = gameProcessor.getInputReader().read().trim();

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
                gameProcessor.getConsoleWriter().write("Please enter a valid response [Y/N]");
            }
        }
    }
}
