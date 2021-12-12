package reversi;

import java.awt.Color;

public class Player extends Object {
    private static Integer number = 1;
    private Table table;
    private Piece piece;
    private String name;
    private Color viewColor;

    public Player(Table aBoard, String aName) {
        table = aBoard;
        piece = new Piece(number++);
        name = aName;
        this.initialize();
        return;
    }

    public void initialize() {
        Integer aColor = piece.getColor();
        if (aColor == 1)
            viewColor = Color.BLACK;
        else if (aColor == 2)
            viewColor = Color.WHITE;
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
        Integer aColor = aGrid.getColor();
        if (aColor > 0 && aColor != this.getColor() && reversiColumn(aGrid.getNextGrid(index), index))
            return true;
        return false;
    }

    public Boolean reversiColumn(Grid aGrid, Integer index) {
        Integer aGridColor = aGrid.getColor();
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

    public static Integer getPlayerNumber(){
        return number;
    }
}
