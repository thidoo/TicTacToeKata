package GameComponent.Coordinate;

public interface CoordinateService {

    boolean isCorrectFormat(String input);
    Coordinate convertToCoordinate(String input);
}
