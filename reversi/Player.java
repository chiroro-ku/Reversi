package reversi;

public class Player extends Object{
    private Board board;
    private Piece piece;
    private String name;
    private static Integer number = 1;

    public Player(Board aBoard,String aName){
        board = aBoard;
        piece = new Piece(number++);
        name = aName;
    }

    public Integer getColor(){
        return piece.getColor();
    }

    public void setPiece(Integer aColumn,Integer aRow){
        Grid aGrid = board.getGrid(aColumn, aRow);
        aGrid.setPiece(piece);
        return;
    }
}
