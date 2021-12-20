package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Computer extends Player {

    public Computer(Table aTable) {
        super(aTable, "com");
        return;
    }

    public void placePiece() {
        List<Integer> aList = new ArrayList<>();
        List<Grid> grids = table.getGrids();
        grids.forEach(item -> aList.add(reversiNumber(item)));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        Integer index = aList.indexOf(max.get());
        Grid aGrid = table.getGrid(index);
        aGrid.setPiece(piece);
        reversi(aGrid);
        return;
    }

    public void reversi(Grid aGrid) {
        aGrid.getNextGrids().stream().filter(item -> super.reversiNext(item, aGrid.getNextGridIndex(item)))
                .forEach(item -> item.setPiece(piece));
        return;
    }

    public Boolean isComputer() {
        return true;
    }

    public Integer reversiNumber(Grid aGrid) {
        if (!aGrid.isPlacePiece())
            return 0;
        List<Integer> aList = new ArrayList<>();
        aGrid.getNextGrids().stream().filter(item -> this.reversiNext(item, aGrid.getNextGridIndex(item)))
                .forEach(item -> aList.add(reversiColumnNumber(item, aGrid.getNextGridIndex(item), 0)));
        Integer sum = aList.stream().mapToInt(value -> value).sum();
        return sum;
    }

    public Boolean reversiNext(Grid aGrid, Integer index) {
        Integer aColor = aGrid.getPieceColor();
        if (aColor > 0 && aColor != this.getColor())
            return true;
        return false;
    }

    public Integer reversiColumnNumber(Grid aGrid, Integer index, Integer number) {
        Integer aGridColor = aGrid.getPieceColor();
        if (aGridColor <= 0)
            return 0;
        if (aGridColor == this.getColor())
            return number;
        return reversiColumnNumber(aGrid.getNextGrid(index), index, number + 1);
    }
}
