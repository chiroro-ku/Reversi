package reversi;

public class Player extends Object{
    private Board board;
    private Piece piece;
    private String name;
    private Integer count;

    public Player(Board aBoard,Integer aNumber,String aName){
        board = aBoard;
        piece = new Piece(aNumber);
        name = aName;
        count = 0;
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
