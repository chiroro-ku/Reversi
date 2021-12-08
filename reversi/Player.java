package reversi;

public class Player extends Object {
    private Table table;
    private Piece piece;
    private String name;
    private static Integer number = 1;

    public Player(Table aBoard, String aName) {
        table = aBoard;
        piece = new Piece(number++);
        name = aName;
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
        Integer index = 0;
        for (Grid aNextGrid : aGrid.getNextGrids()) {
            Integer aGridColor = aNextGrid.getColor();
            if (aGridColor > 0 && aGridColor != this.getColor() && reversiColumn(aNextGrid.getNextGrid(index), index))
                aNextGrid.setPiece(piece);
            index++;
        }
        return;
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
}
