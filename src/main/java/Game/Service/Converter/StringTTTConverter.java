package Game.Service.Converter;

import Game.Model.Board.GameBoard2D;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.Player;
import Game.Model.TicTacToe.TicTacToe2Players;
import Game.Service.Board.GameBoard2DService;

import java.util.StringJoiner;

public class StringTTTConverter {

    private final String DELIMITER = ",";
    private GameBoard2DService gameBoard2DService;

    public StringTTTConverter(GameBoard2DService gameBoard2DService) {
        this.gameBoard2DService = gameBoard2DService;
    }

    public String convertTTTToString(TicTacToe2Players ticTacToe2Players){
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(ticTacToe2Players.getPlayer1().getToken());
        stringJoiner.add(ticTacToe2Players.getPlayer2().getToken());
        stringJoiner.add(ticTacToe2Players.getCurrentPlayer().getToken());
        stringJoiner.add(ticTacToe2Players.getBoard().toString());

        return stringJoiner.toString();
    }

    public TicTacToe2Players convertStringToTTT(String ticTacToe) throws CannotConvertToTicTacToeException {
        String[] components = ticTacToe.split(DELIMITER);

        String player1Token = components[0];
        String player2Token = components[1];
        String currentPlayerToken = components[2];
        String board = components[3];

        return createTicTacToe(player1Token, player2Token, currentPlayerToken, board);

    }

    private TicTacToe2Players createTicTacToe(String player1Token, String player2Token, String currentPlayerToken, String board) throws CannotConvertToTicTacToeException {

        GameBoard2D gameBoard2D = (GameBoard2D) gameBoard2DService.convertFromBoardStringtoBoard(board);

        if (player1Token.equals(currentPlayerToken)){
            return new TicTacToe2Players(new Player(1, player1Token),
                    new Player(2, player2Token),
                    gameBoard2D);
        }
        else if (player2Token.equals(currentPlayerToken)){
            return new TicTacToe2Players(new Player(1, player2Token),
                    new Player(2, player1Token),
                    gameBoard2D);
        }
        else{
            throw new CannotConvertToTicTacToeException();
        }
    }
}
