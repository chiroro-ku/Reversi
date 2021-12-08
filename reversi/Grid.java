package reversi;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Painter;

public class Grid extends Object {
    private Piece piece;
    private Boolean endGrid;
    private Integer index;
    private List<Grid> nextGrids;
    private Integer column;
    private Integer row;

    public Grid(Integer aInteger) {
        if(aInteger == -1) piece = new Piece(-1);
        else piece = new Piece(0);
        endGrid = false;
        index = aInteger;
        nextGrids = new ArrayList<>();
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
        piece = aPiece;
        return;
    }

    public void setEndGrid() {
        endGrid = true;
        return;
    }

    public Boolean isEndGrid() {
        return endGrid;
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

}
