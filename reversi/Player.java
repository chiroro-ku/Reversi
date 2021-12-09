package reversi;

public class Player extends Object {
    private static Integer number = 1;
    private Table table;
    private Piece piece;
    private String name;

    public Player(Table aBoard, String aName) {
        table = aBoard;
        piece = new Piece(number++);
        name = aName;
        return;
    }

    public Integer getColor() {
        return piece.getColor();
    }

    public void set(Integer aColumn, Integer aRow) {
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

    public Integer getCount(){
        return piece.getCount();
    }
}
