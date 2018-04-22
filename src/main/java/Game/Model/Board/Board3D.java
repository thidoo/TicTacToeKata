package Game.Model.Board;

import Game.Model.Cell;
import Game.Model.Coordinate.Coordinate;

public class Board3D implements GameBoard {

    private Cell[][][] content;
    private int size;

    public Board3D(int size) {
        this.size = size;
        initialiseEmptyBoard(size);
    }

    private void initialiseEmptyBoard(int size) {
        this.content = new Cell[size][size][size];
        for (int z = 0; z < size; z++){
            for (int x = 0; x < size; x++){
                for (int y = 0; y < size; y++){
                    content[z][x][y] = new Cell();
                }
            }
        }
    }

    @Override
    public boolean isEmptyAt(Coordinate coordinate) {
        return content[coordinate.getZ()][coordinate.getX()][coordinate.getY()].isEmpty();
    }

    @Override
    public boolean isFull() {
        for (int z = 0; z < size; z++){
            for (int x = 0; x < size; x++){
                for (int y = 0; y < size; y++){
                    if (content[z][x][y].isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void updateCell(String token, Coordinate coordinate) {
        content[coordinate.getZ()][coordinate.getX()][coordinate.getY()].setToken(token);
        content[coordinate.getZ()][coordinate.getX()][coordinate.getY()].setEmpty(false);
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return (coordinate.getX() >= 0 && coordinate.getX() < size)
                && (coordinate.getY() >= 0 && coordinate.getY() < size)
                && (coordinate.getZ() >=0 && coordinate.getZ() < size);
    }

    @Override
    public int getSize() {
        return size;
    }

    public Cell[][][] getContent() {
        return content;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int z = 0; z < size; z++){
            for (int x = 0; x < size; x++){
                for (int y = 0; y < size; y++){
                    stringBuilder.append(content[z][x][y].getToken() + " ");
                }
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
