package Game.Model.Board;

import Game.Model.Coordinate.Coordinate;

public interface GameBoard {

    boolean isEmptyAtPosition(Coordinate coordinate);

    boolean isFull();

    void printBoard();

    void updateCell(String token, Coordinate coordinate);

    boolean contains(Coordinate coordinate);

    boolean hasFilledLine(Coordinate coordinate);
}
