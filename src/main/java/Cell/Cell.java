package Cell;

import Coordinate.Coordinate;

public interface Cell {
    String DEFAULT_TOKEN = ".";

    Coordinate getCoordinate();

    void setCoordinate(Coordinate coordinate);

    String getToken();

    void setToken(String token);

    boolean isEmpty();
}
