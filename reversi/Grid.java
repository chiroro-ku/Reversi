package reversi;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Object {
    private Piece piece;
    private Boolean endGrid;
    private Integer index;
    private Boolean placePiece;
    private List<Grid> nextGrids;
    private Integer column;
    private Integer row;

    public Grid(Piece aPiece,Integer aInteger) {
        piece = aPiece;
        aPiece.increment();
        endGrid = false;
        index = aInteger;
        placePiece = true;
        nextGrids = new ArrayList<>();
        return;
    }

    public Grid(Piece aPiece) {
        piece = aPiece;
        placePiece = false;
        return;
    }

    public void initialize(Integer maxColumn) {
        row = index / maxColumn;
        column = index - (row * maxColumn);
        return;
    }

    public void setNextGrids(List<Grid> aSetNextGrids) {
        nextGrids = aSetNextGrids;
        return;
    }

    public void setPiece(Piece aPiece) {
        piece.decrement();
        piece = aPiece;
        piece.increment();
        placePiece = false;
        return;
    }

    public void setEndGrid(Boolean aBoolean) {
        endGrid = aBoolean;
        return;
    }

    public Boolean isEndGrid() {
        return endGrid;
    }

    public Boolean isPlacePiece(){
        return placePiece;
    }

    public Piece getPiece() {
        return piece;
    }

    public Integer getColor() {
        return piece.getColor();
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public List<Grid> getNextGrids(){
        return nextGrids;
    }

    public Grid getNextGrid(Integer index){
        return nextGrids.get(index);
    }

    public Integer getNextGridIndex(Grid aGrid){
        return nextGrids.indexOf(aGrid);
    }

    public void setPlacePiece(Boolean aBoolean){
        placePiece = aBoolean;
        return;
    }
}
