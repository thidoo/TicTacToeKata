package Game.Service.Coordinate;

import Game.Model.Coordinate.Coordinate;

public interface CoordinateConverter {

    String DEFAULT_COORDINATE_DELIMITER = ",";

    boolean isCorrectFormat(String input);
    Coordinate convert(String input);
}
