package Board;

import Coordinate.Coordinate;
import Cell.Cell;

public interface GameBoard {

    void initialiseEmptyBoard(int size);

    boolean isEmptyAtPosition(Coordinate coordinate);

    boolean isFull();

    void printBoard();

    void updateCell(String token, Coordinate coordinate);

    boolean contains(Coordinate coordinate);

    boolean hasFilledLine(Coordinate coordinate);

    int getBoardSize();
}
