package Game.Service;

import Game.Model.Board.GameBoard2D;
import Game.Model.CannotConvertToTicTacToeException;
import Game.Model.Player;
import Game.Model.TicTacToe;
import Game.Service.Board.GameBoard2DService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONConverter {

    private GameBoard2DService gameBoard2DService;

    public JSONConverter(GameBoard2DService gameBoard2DService) {
        this.gameBoard2DService = gameBoard2DService;
    }

    public String convertToJSONString(TicTacToe ticTacToe){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("player1", ticTacToe.getPlayer1().getToken());
        jsonObject.addProperty("player2", ticTacToe.getPlayer2().getToken());
        jsonObject.addProperty("currentPlayer", ticTacToe.getCurrentPlayer().getToken());
        jsonObject.addProperty("board", ticTacToe.getBoard().toString());
        return jsonObject.toString();
    }

    public TicTacToe convertToTicTacToe(File jsonFile) throws CannotConvertToTicTacToeException {
        JsonParser jsonParser = new JsonParser();

        try {
            Object object = jsonParser.parse(new FileReader(jsonFile));
            JsonObject jsonObject = ((JsonElement) object).getAsJsonObject();

            String player1Token = jsonObject.get("player1").toString().substring(1, 1);
            String player2Token = jsonObject.get("player2").toString().substring(1, 1);
            String currentPlayerToken = jsonObject.get("currentPlayer").toString().substring(1, 1);

            String board = jsonObject.get("board").toString();

            return createTicTacToe(player1Token, player2Token, currentPlayerToken, board.substring(1, board.length()-1));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        throw new CannotConvertToTicTacToeException();
    }

    private TicTacToe createTicTacToe(String player1Token, String player2Token, String currentPlayerToken, String board) throws CannotConvertToTicTacToeException {

        GameBoard2D gameBoard2D = gameBoard2DService.convertFromBoardStringtoBoard(board);

        if (player1Token.equals(currentPlayerToken)){
            return new TicTacToe(new Player(1, player1Token),
                    new Player(2, player2Token),
                    gameBoard2D);
        }
        else if (player2Token.equals(currentPlayerToken)){
            return new TicTacToe(new Player(1, player2Token),
                    new Player(2, player1Token),
                    gameBoard2D);
        }
        else{
            throw new CannotConvertToTicTacToeException();
        }
    }
}
