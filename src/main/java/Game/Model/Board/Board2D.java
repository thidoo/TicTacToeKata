package Game.Model.Board;

import Game.Model.Cell;
import Game.Model.Coordinate.Coordinate;

import java.util.Arrays;

public class Board2D implements GameBoard {

    private Cell[][] content;
    private int size;

    public Board2D(int size) {
        this.size = size;
        initialiseEmptyBoard(size);
    }

    @Override
    public void updateCell(String token, Coordinate coordinate) {
        content[coordinate.getX()][coordinate.getY()].setToken(token);
        content[coordinate.getX()][coordinate.getY()].setEmpty(false);
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return (coordinate.getX() >= 0 && coordinate.getX() < size)
                && (coordinate.getY() >= 0 && coordinate.getY() < size);
    }

    @Override
    public boolean isEmptyAt(Coordinate coordinate){
        return content[coordinate.getX()][coordinate.getY()].isEmpty();
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i< size; i++){
            for (int j = 0; j< size; j++){
                if (this.content[i][j].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    private void initialiseEmptyBoard(int size){
        this.content = new Cell[size][size];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                content[i][j] = new Cell();
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public Cell[][] getContent() {
        return content.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board2D)) return false;
        Board2D that = (Board2D) o;
        return size == that.size &&
                Arrays.equals(getContent(), that.getContent());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                stringBuilder.append(content[i][j].getToken() + " ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
