package GameComponent.Cell;

import GameComponent.Coordinate.Coordinate;

public class Cell2D implements Cell {

    private Coordinate coordinate;
    private String token;
    private boolean isEmpty;

    public Cell2D(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.token = Cell.DEFAULT_TOKEN;
        this.isEmpty = true;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
