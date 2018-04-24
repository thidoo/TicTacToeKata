package Game.Service.Converter;

import Game.Model.Board.Board2D;
import Game.Model.Board.GameBoard;
import Game.Model.CustomException.CannotConvertToTicTacToeException;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Service.Board.Board2DService;
import Game.Service.Board.GameBoardService;

import java.util.StringJoiner;

public class TicTacToeRepresentationConverter {

    private final String DELIMITER = ",";
    private GameBoardService boardService;

    public TicTacToeRepresentationConverter(GameBoardService boardService) {
        this.boardService = boardService;
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

    private TicTacToe createTicTacToe(String player1Token, String player2Token, String currentPlayerToken, String boardString) throws CannotConvertToTicTacToeException {

        GameBoard board = boardService.convertStringToBoard(boardString);

        if (player1Token.equals(currentPlayerToken)){
            return new TicTacToe(new Player(1, player1Token),
                    new Player(2, player2Token),
                    board);
        }
        else if (player2Token.equals(currentPlayerToken)){
            return new TicTacToe(new Player(1, player2Token),
                    new Player(2, player1Token),
                    board);
        }
        else{
            throw new CannotConvertToTicTacToeException();
        }
    }
}
