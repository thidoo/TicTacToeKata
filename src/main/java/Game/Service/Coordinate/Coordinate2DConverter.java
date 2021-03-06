package Game.Service.Coordinate;

import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate2D;

public class Coordinate2DConverter implements CoordinateConverter {

    public boolean isCorrectFormat(String input){

        return input.matches("([0-9]+),([0-9]+)");
    }

    public Coordinate convert(String input) {
        String[] xy = input.split(DEFAULT_COORDINATE_DELIMITER);
        return new Coordinate2D(Integer.parseInt(xy[0]) - 1, Integer.parseInt(xy[1]) - 1);
    }

}
