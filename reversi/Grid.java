package reversi;

public class Grid extends Object{
    private Piece piece;
    private Boolean confilm;

    public Grid(){
        piece = new Piece(0);
        confilm = false;
        return;
    }

    public Piece getPiece(){
        return piece;
    }

    public Integer getColor(){
        return piece.getColor();
    }

    public void setPiece(Piece aPiece){
        piece = aPiece;
        return;
    }
}
