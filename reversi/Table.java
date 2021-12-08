package reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Table extends Object {
    private Integer maxColumn;
    private Integer maxRow;
    private List<Grid> grids;
    private List<Integer> direction;

    public Table(Integer aMaxColumn, Integer aMaxRow) {
        maxColumn = aMaxColumn;
        maxRow = aMaxRow;
        direction = Arrays.asList(-maxColumn, 1, maxColumn, -1, -maxColumn - 1, -maxColumn + 1, maxColumn + 1,
                maxColumn - 1);
        this.initialize();
        return;
    }

    public void initialize() {
        grids = new ArrayList<>();
        IntStream.range(0, maxColumn * maxRow).forEach(i -> grids.add(new Grid(i)));
        grids.forEach(item -> {
            item.initialize(maxColumn);
            item.setNextGrids(nextGridList(item));
        });
        return;
    }

    public List<Grid> nextGridList(Grid aGrid) {
        List<Grid> aList = new ArrayList<>();
        Integer index = grids.indexOf(aGrid);
        direction.forEach(item -> {
            if (isEndGrid(aGrid, item))
                aList.add(new Grid(-1));
            else
                aList.add(grids.get(index + item));
        });
        return aList;
    }

    public Boolean isEndGrid(Grid aGrid, Integer aDirection) {
        if (aGrid.getColumn() == 0
                && (aDirection == -maxColumn - 1 || aDirection == -1 || aDirection == maxColumn - 1))
            return true;
        if (aGrid.getColumn() == maxColumn - 1
                && (aDirection == -maxColumn + 1 || aDirection == 1 || aDirection == maxColumn + 1))
            return true;
        if (aGrid.getRow() == 0
                && (aDirection == -maxColumn - 1 || aDirection == -maxColumn || aDirection == -maxColumn + 1))
            return true;
        if (aGrid.getRow() == maxRow - 1
                && (aDirection == maxColumn - 1 || aDirection == maxColumn || aDirection == maxColumn + 1))
            return true;
        return false;
    }

    public Boolean isSet(Player aPlayer, Integer aColumn, Integer aRow) {
        Integer aColor = aPlayer.getColor();
        Grid aGrid = this.getGrid(aColumn, aRow);
        Integer index = 0;
        for (Grid aNextGrid : aGrid.getNextGrids()) {
            Integer aGridColor = aNextGrid.getColor();
            if (aGridColor > 0 && aGridColor != aColor && isSetNextGrids(aColor, aNextGrid.getNextGrid(index), index))
                return true;
            else
                index++;
        }
        return false;
    }

    public Boolean isSetNextGrids(Integer aColor, Grid aGrid, Integer index) {
        Integer aGridColor = aGrid.getColor();
        if (aGridColor <= 0)
            return false;
        if (aGridColor == aColor)
            return true;
        else
            return isSetNextGrids(aColor, aGrid.getNextGrid(index), index);
    }

    public Integer getMaxColumn() {
        return maxColumn;
    }

    public Integer getMaxRow() {
        return maxRow;
    }

    public List<Grid> getGrids() {
        return grids;
    }

    public Grid getGrid(Integer aColumn, Integer aRow) {
        Integer index = this.getIndex(aColumn, aRow);
        return grids.get(index);
    }

    public Integer getIndex(Integer aColumn, Integer aRow) {
        return aColumn + aRow * maxColumn;
    }
}
