package Game.Model.Cell;

import Game.Model.Coordinate.Coordinate;

public interface Cell {
    String DEFAULT_TOKEN = ".";

    Coordinate getCoordinate();

    void setCoordinate(Coordinate coordinate);

    String getToken();

    void setToken(String token);

    boolean isEmpty();

    void setIsEmpty(boolean isEmpty);
}
