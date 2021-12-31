package reversi;

import java.util.List;

public class Player extends Object{
    protected Integer index;
    protected String name;
    protected Piece piece;
    protected Table table;
    
    public Player(Integer index,String name,Table table){
        this.index = index;
        this.name = name;
        this.piece = new Piece(index);
        this.table = table;
        return;
    }

    public void placePiece(Integer aColumn,Integer aRow){
        Grid aGrid = table.getGrid(aColumn, aRow);
        aGrid.placePiece(piece);
        reversi(aGrid);
        return;
    }

    public void reversi(Grid aGrid) {
        List<Grid> nextGrids = aGrid.getNextGrids();
        nextGrids.stream().filter(item -> reversiNext(item, nextGrids.indexOf(item)))
                .forEach(item -> item.placePiece(piece));
        return;
    }

    public Boolean reversiNext(Grid aGrid, Integer index) {
        Integer color = aGrid.getPiece().getColor();
        if (color > 0 && color != piece.getColor() && reversiColumn(aGrid.getNextGrid(index), index))
            return true;
        return false;
    }

    public Boolean reversiColumn(Grid aGrid, Integer index) {
        Integer color = aGrid.getPiece().getColor();
        if (color <= 0)
            return false;
        if (color == piece.getColor())
            return true;
        if (reversiColumn(aGrid.getNextGrid(index), index)) {
            aGrid.placePiece(piece);
            return true;
        }
        return false;
    }

    public void setTable(Table table){
        this.table = table;
        return;
    }

    public Piece getPiece(){
        return piece;
    }

    public Boolean isComputer(){
        return false;
    }

    public Integer getIndex(){
        return index;
    }

    public String getName(){
        return name;
    }

    public Integer getNumber(){
        return piece.getNumber();
    }
}
