package Game.Service.Board;

import Game.Model.Board.Board2D;
import Game.Model.Board.GameBoard;
import Game.Model.Cell;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate2D;

public class Board2DService implements GameBoardService{

    @Override
    public boolean checkForWinner(GameBoard gameBoard, Coordinate coordinate){

        int boardSize = gameBoard.getSize();
        Cell[][] boardContent = ((Board2D)gameBoard).getContent();

        int x = coordinate.getX();
        int y = coordinate.getY();

        if (hasFullRow(boardSize, boardContent, x,y)
                || hasFullColumn(boardSize, boardContent, x,y)
                || hasFullDiagonal(boardSize, boardContent, x,y)){
            return true;
        }

        return false;
    }

    private boolean hasFullRow(int boardSize, Cell[][] boardContent, int x, int y){
        for (int col = 0; col < boardSize; col++){
            if ((boardContent[x][col].getToken().equals(Cell.getDefaultToken()))
                    || (!boardContent[x][col].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    private boolean hasFullColumn(int boardSize, Cell[][] boardContent, int x, int y){
        for (int row = 0; row < boardSize; row++){
            if ((boardContent[row][y].getToken().equals(Cell.getDefaultToken()))
                    ||(!boardContent[row][y].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    private boolean hasFullDiagonal(int boardSize, Cell[][] boardContent, int x, int y){
        return hasFullDiagonalWithPositiveSlope(boardSize, boardContent, x,y) || hasFullDiagonalWithNegativeSlope(boardSize, boardContent, x,y);
    }

    private boolean hasFullDiagonalWithPositiveSlope(int boardSize, Cell[][] boardContent, int x, int y){
        if (x!=y){
            return false;
        }

        int i = 0;
        while (i<boardSize){
            if ((boardContent[i][i].getToken().equals(Cell.getDefaultToken()))
                    || (!boardContent[i][i].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean hasFullDiagonalWithNegativeSlope(int boardSize, Cell[][] boardContent, int x, int y){
        if (x+y != boardSize-1){
            return false;
        }

        for (int i=0; i < boardSize; i++){
            if ((boardContent[boardSize-1-i][i].getToken().equals(Cell.getDefaultToken())) ||
                    (!boardContent[boardSize-1-i][i].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    @Override
    public GameBoard convertStringToBoard(String boardString){
        String[] rows = boardString.split("\\n");
        Board2D board = new Board2D(rows.length);

        for (int row=0; row < rows.length; row++){
            String[] columns = rows[row].trim().split(" ");
            for (int column=0; column < columns.length; column++){
                if (!columns[column].equals(".")){
                    board.updateCell(columns[column], new Coordinate2D(row, column));
                }
            }
        }
        return board;
    }
}
