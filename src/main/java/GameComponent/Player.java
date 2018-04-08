package GameComponent;

public class Player {
    private int playOrder;
    private String token;

    public Player(String token) {
        this.token = token;
    }

    public void setPlayOrder(int playOrder) {
        this.playOrder = playOrder;
    }

    public int getPlayerOrder() {
        return playOrder;
    }

    public String getToken() {
        return token;
    }
}
