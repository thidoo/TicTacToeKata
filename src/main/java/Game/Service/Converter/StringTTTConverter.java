package Game.Service.Converter;

import Game.Model.Board.Board2D;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Service.Board.Board2DService;

import java.util.StringJoiner;

public class StringTTTConverter {

    private final String DELIMITER = ",";
    private Board2DService board2DService;

    public StringTTTConverter(Board2DService board2DService) {
        this.board2DService = board2DService;
    }

    public String convertTTTToString(TicTacToe ticTacToe){
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        stringJoiner.add(ticTacToe.getPlayer1().getToken());
        stringJoiner.add(ticTacToe.getPlayer2().getToken());
        stringJoiner.add(ticTacToe.getCurrentPlayer().getToken());
        stringJoiner.add(ticTacToe.getBoard().toString());

        return stringJoiner.toString();
    }

    public TicTacToe convertStringToTTT(String ticTacToe) throws CannotConvertToTicTacToeException {
        String[] components = ticTacToe.split(DELIMITER);

        String player1Token = components[0];
        String player2Token = components[1];
        String currentPlayerToken = components[2];
        String board = components[3];

        return createTicTacToe(player1Token, player2Token, currentPlayerToken, board);

    }

    private TicTacToe createTicTacToe(String player1Token, String player2Token, String currentPlayerToken, String board) throws CannotConvertToTicTacToeException {

        Board2D board2D = (Board2D) board2DService.convertStringToBoard(board);

        if (player1Token.equals(currentPlayerToken)){
            return new TicTacToe(new Player(1, player1Token),
                    new Player(2, player2Token),
                    board2D);
        }
        else if (player2Token.equals(currentPlayerToken)){
            return new TicTacToe(new Player(1, player2Token),
                    new Player(2, player1Token),
                    board2D);
        }
        else{
            throw new CannotConvertToTicTacToeException();
        }
    }
}
