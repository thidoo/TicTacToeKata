package Game.Service.Board;

import Game.Model.Board.GameBoard;
import Game.Model.Board.GameBoard2D;
import Game.Model.Cell.Cell2D;
import Game.Model.Coordinate.Coordinate;
import Game.Model.Coordinate.Coordinate2D;

public class GameBoard2DService implements GameBoardService{

    @Override
    public boolean checkHasFilledLine(GameBoard gameBoard, Coordinate coordinate){

        int boardSize = gameBoard.getBoardSize();
        Cell2D[][] boardContent = ((GameBoard2D)gameBoard).getBoardContent();

        int x = coordinate.getX();
        int y = coordinate.getY();

        if (hasLineFilledHorizontally(boardSize, boardContent, x,y)
                || hasLineFilledVertically(boardSize, boardContent, x,y)
                || hasLineFilledDiagonally(boardSize, boardContent, x,y)){
            return true;
        }

        return false;
    }

    private boolean hasLineFilledHorizontally(int boardSize, Cell2D[][] boardContent, int x, int y){
        for (int col = 0; col < boardSize; col++){
            if (!(boardContent[x][col].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledVertically(int boardSize, Cell2D[][] boardContent, int x, int y){
        for (int row = 0; row < boardSize; row++){
            if (!(boardContent[row][y].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    private boolean hasLineFilledDiagonally(int boardSize, Cell2D[][] boardContent, int x, int y){
        return hasDiagonalLineFilled_NegativeSlope(boardSize, boardContent, x,y) || hasDiagonalLineFilled_PositiveSlope(boardSize, boardContent, x,y);
    }

    private boolean hasDiagonalLineFilled_NegativeSlope(int boardSize, Cell2D[][] boardContent, int x, int y){
        if (x!=y){
            return false;
        }

        int i = 0;

        while (i<boardSize){
            if (!(boardContent[i][i].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean hasDiagonalLineFilled_PositiveSlope(int boardSize, Cell2D[][] boardContent, int x, int y){
        if (x+y != boardSize-1){
            return false;
        }

        for (int i=0; i < boardSize; i++){
            if (!(boardContent[boardSize-1-i][i].getToken().equals(boardContent[x][y].getToken()))){
                return false;
            }
        }
        return true;
    }

    @Override
    public GameBoard convertFromBoardStringtoBoard(String boardString){
        String[] rows = boardString.split("\\n");
        GameBoard2D board = new GameBoard2D(rows.length);

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
