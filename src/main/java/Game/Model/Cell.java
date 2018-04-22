package Game.Model;

public class Cell {

    private final static String DEFAULT_TOKEN = ".";

    private String token;
    private boolean isEmpty;

    public Cell() {
        this.token = DEFAULT_TOKEN;
        this.isEmpty = true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

}