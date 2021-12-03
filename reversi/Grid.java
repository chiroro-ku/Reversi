package reversi;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Object{
    private Piece piece;
    private Boolean setPiecePossible;
    private Boolean pieceColorConfilm;
    private List<Grid> nextGrids;

    public Grid(){
        piece = new Piece(0);
        setPiecePossible = true;
        pieceColorConfilm = false;
        nextGrids = new ArrayList<>();
        return;
    }

    public Grid(Integer aNumber){
        piece = new Piece(aNumber);
        setPiecePossible = false;
        pieceColorConfilm = false;
        nextGrids = new ArrayList<>();
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

    public void setSetPossible(Boolean aBoolean){
        setPiecePossible = aBoolean;
        return;
    }

    public void setNextGrids(Grid aGrid,Integer index){
        nextGrids.add(index,aGrid);
    }
}
