package Game.Service;

import Game.Model.TicTacToe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.io.File;

public class JSONConverter {

    public String convertToJSONString(TicTacToe ticTacToe){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("player1", ticTacToe.getPlayer1().getToken());
        jsonObject.addProperty("player2", ticTacToe.getPlayer2().getToken());
        jsonObject.addProperty("currentPlayer", ticTacToe.getCurrentPlayer().getToken());
        jsonObject.addProperty("board", ticTacToe.getBoard().toString());

        return jsonObject.toString();
    }

    public TicTacToe convertToTicTacToe(File jsonFile){
        ObjectMapper objectMapper = new ObjectMapper();

        TicTacToe ticTacToe = null;
        try {
            ticTacToe = objectMapper.readValue(jsonFile, TicTacToe.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ticTacToe;
    }
}
