package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Computer extends Player {
    public Computer(Integer index, Table table) {
        super(index, "com", table);
    }

    public void placePiece() {
        List<Integer> aList = new ArrayList<>();
        List<Grid> grids = table.getGrids();
        grids.forEach(item -> aList.add(reversiNumber(item)));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        Integer index = aList.indexOf(max.get());
        Grid aGrid = grids.get(index);
        aGrid.placePiece(piece);
        reversi(aGrid);
        return;
    }

    public void reversi(Grid aGrid) {
        List<Grid> nextGrids = aGrid.getNextGrids();
        nextGrids.stream().filter(item -> super.reversiNext(item, nextGrids.indexOf(item)))
                .forEach(item -> item.placePiece(piece));
        return;
    }

    public Integer reversiNumber(Grid aGrid) {
        if (!table.isPlacePiece(this, aGrid))
            return 0;
        List<Integer> aList = new ArrayList<>();
        List<Grid> nextGrids = aGrid.getNextGrids();
        nextGrids.stream().filter(item -> this.reversiNext(item, nextGrids.indexOf(item)))
                .forEach(item -> aList.add(reversiColumnNumber(item, nextGrids.indexOf(item), 0)));
        Integer sum = aList.stream().mapToInt(value -> value).sum();
        return sum;
    }

    public Boolean reversiNext(Grid aGrid, Integer index) {
        Integer color = aGrid.getPiece().getColor();
        if (color > 0 && color != piece.getColor())
            return true;
        return false;
    }

    public Integer reversiColumnNumber(Grid aGrid, Integer index, Integer number) {
        Integer aGridColor = aGrid.getPiece().getColor();
        if (aGridColor <= 0)
            return 0;
        if (aGridColor == piece.getColor())
            return number;
        return reversiColumnNumber(aGrid.getNextGrid(index), index, number + 1);
    }

    public Boolean isComputer() {
        return true;
    }
}
