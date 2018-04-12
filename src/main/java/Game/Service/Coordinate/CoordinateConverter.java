package Game.Service.Coordinate;

import Game.Model.Coordinate.Coordinate;

public interface CoordinateConverter {

    boolean isCorrectFormat(String input);
    Coordinate convertToCoordinate(String input);
}
