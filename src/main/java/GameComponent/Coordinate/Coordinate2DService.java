package GameComponent.Coordinate;

public class Coordinate2DService implements CoordinateService{

    private static final String DEFAULT_COORDINATE_DELIMITER = ",";

    public boolean isCorrectFormat(String input){
        return input.matches("([0-9]+),([0-9]+)");
    }

    public Coordinate convertToCoordinate(String input) {
        String[] xy = input.split(DEFAULT_COORDINATE_DELIMITER);
        return new Coordinate2D(Integer.parseInt(xy[0]) - 1, Integer.parseInt(xy[1]) - 1);
    }

}