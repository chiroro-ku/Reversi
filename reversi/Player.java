package reversi;

import java.awt.Color;

public class Player extends Object {
    protected static Integer number = 1;
    protected Table table;
    protected Piece piece;
    protected String name;
    protected Color viewColor;

    public Player(Table aTable, String aName) {
        table = aTable;
        piece = new Piece(number++);
        name = aName;
        this.initialize();
        return;
    }

    public void initialize() {
        Integer aColor = piece.getColor();
        if (aColor == 1)
            viewColor = Color.WHITE;
        else if (aColor == 2)
            viewColor = Color.BLACK;
        else {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            viewColor = new Color(r, g, b);
        }
        piece.setViewColor(viewColor);
    }

    public Color getViewColor(){
        return viewColor;
    }

    public Integer getColor() {
        return piece.getColor();
    }

    public void placePiece(Integer aColumn, Integer aRow) {
        Grid aGrid = table.getGrid(aColumn, aRow);
        aGrid.setPiece(piece);
        this.reversi(aColumn, aRow);
        return;
    }

    public void reversi(Integer aColumn, Integer aRow) {
        Grid aGrid = table.getGrid(aColumn, aRow);
        aGrid.getNextGrids().stream().filter(item -> reversiNext(item, aGrid.getNextGridIndex(item)))
                .forEach(item -> item.setPiece(piece));
        return;
    }

    public Boolean reversiNext(Grid aGrid, Integer index) {
        Integer aColor = aGrid.getPieceColor();
        if (aColor > 0 && aColor != this.getColor() && reversiColumn(aGrid.getNextGrid(index), index))
            return true;
        return false;
    }

    public Boolean reversiColumn(Grid aGrid, Integer index) {
        Integer aGridColor = aGrid.getPieceColor();
        if (aGridColor <= 0)
            return false;
        if (aGridColor == this.getColor())
            return true;
        if (reversiColumn(aGrid.getNextGrid(index), index)) {
            aGrid.setPiece(piece);
            return true;
        }
        return false;
    }

    public Integer getCount() {
        return piece.getCount();
    }

    public Piece getPiece(){
        return piece;
    }

    public String getName(){
        return name;
    }

    public void setTable(Table aTable){
        table = aTable;
        return;
    }

    public Boolean isComputer(){
        return false;
    }
}
