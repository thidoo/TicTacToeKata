package Game.Service.Coordinate;

import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate3D;

public class Coordinate3DConverter implements CoordinateConverter {

    @Override
    public boolean isCorrectFormat(String input) {
        return input.matches("([0-9]+),([0-9]+),([0-9]+)");
    }

    @Override
    public Coordinate convert(String input) {
        String[] xyz = input.split(DEFAULT_COORDINATE_DELIMITER);
        return new Coordinate3D(Integer.parseInt(xyz[0])-1,
                                Integer.parseInt(xyz[1])-1,
                                Integer.parseInt(xyz[1])-1);
    }
}
