package Game.Model;

import java.util.Objects;

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

    public static String getDefaultToken() {
        return DEFAULT_TOKEN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return isEmpty() == cell.isEmpty() &&
                Objects.equals(getToken(), cell.getToken());
    }
}