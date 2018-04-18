package Game.Model.Cell;

import Game.Model.Coordinate.Coordinate;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell2D)) return false;
        Cell2D cell2D = (Cell2D) o;
        return isEmpty() == cell2D.isEmpty() &&
                Objects.equals(getCoordinate(), cell2D.getCoordinate()) &&
                Objects.equals(getToken(), cell2D.getToken());
    }

}
