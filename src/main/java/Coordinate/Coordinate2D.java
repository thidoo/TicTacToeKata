package Coordinate;

public class Coordinate2D implements Coordinate {

    private static final String DEFAULT_COORDINATE_DELIMITER = ",";

    private int x;
    private int y;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isCoordinate2D(String string){
        return string.matches("([0-9]+),([0-9]+)");
    }

    public static Coordinate2D convertToCoordinate2D(String playerInput) {
        String[] xy = playerInput.split(DEFAULT_COORDINATE_DELIMITER);
        return new Coordinate2D(Integer.parseInt(xy[0]) - 1, Integer.parseInt(xy[1]) - 1);
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
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }


}
