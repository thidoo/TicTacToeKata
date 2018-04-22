package Game.Model.Board;

import Game.Model.Coordinate.Coordinate;

public interface GameBoard {

    boolean isEmptyAt(Coordinate coordinate);

    boolean isFull();

    void updateCell(String token, Coordinate coordinate);

    boolean contains(Coordinate coordinate);

    int getSize();

    boolean equals(Object o);
}
