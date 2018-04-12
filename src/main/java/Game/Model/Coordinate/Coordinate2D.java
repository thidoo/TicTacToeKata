package Game.Model.Coordinate;

public class Coordinate2D implements Coordinate {

    private int x;
    private int y;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate2D)) return false;
        Coordinate2D that = (Coordinate2D) o;
        return getX() == that.getX() &&
                getY() == that.getY();
    }
}
