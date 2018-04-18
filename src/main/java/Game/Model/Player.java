package Game.Model;

import java.util.Objects;

public class Player {
    private int playOrder;
    private String token;

    public Player(String token) {
        this.token = token;
    }

    public Player(int playOrder, String token) {
        this.playOrder = playOrder;
        this.token = token;
    }

    public int getPlayerOrder() {
        return playOrder;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return playOrder == player.playOrder &&
                Objects.equals(getToken(), player.getToken());
    }
}
