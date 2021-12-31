package reversi;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Object {
    private Integer index;
    private Piece piece;
    private List<Grid> nextGrids;
    private Integer column;
    private Integer row;
    private Boolean placePiece;

    public Grid(Integer index,Piece piece,Integer column,Integer row){
        this.index = index;
        this.setPiece(piece);
        this.nextGrids = new ArrayList<>();
        this.column = column;
        this.row = row;
        this.placePiece = true;
        return;
    }

    public Grid(Piece piece){
        this.index = -1;
        this.setPiece(piece);
        this.placePiece = false;
        return;
    }

    public Boolean isWallGrid(){
        if(piece.getColor() < 0)
            return true;
        return false;
    }

    public void initialize(List<Grid> next){
        this.nextGrids = next;
        return;
    }

    public void placePiece(Piece aPiece){
        this.piece.decrease();
        this.piece = aPiece;
        this.piece.increase();
        this.placePiece = false;
        return;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
        this.piece.increase();
        this.placePiece = true;
        return;
    }

    public Integer getIndex(){
        return index;
    }

    public Integer getColumn(){
        return column;
    }

    public Integer getRow(){
        return row;
    }

    public List<Grid> getNextGrids(){
        return nextGrids;
    }

    public Grid getNextGrid(Integer index){
        return nextGrids.get(index);
    }

    public Piece getPiece(){
        return piece;
    }

    public Boolean isPlacePiece(){
        return placePiece;
    }
}
