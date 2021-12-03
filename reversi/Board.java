package reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board extends Object {
    private Integer maxColumn;
    private Integer maxRow;
    private List<Grid> grids;
    private List<Integer> direction;

    public Board(Integer aMaxColumn, Integer aMaxRow) {
        maxColumn = aMaxColumn;
        maxRow = aMaxRow;
        direction = Arrays.asList(-maxColumn, 1, maxColumn, -1, -maxColumn - 1, -maxColumn + 1, maxColumn + 1, maxColumn - 1);
        this.initialize();
    }

    public void initialize() {
        grids = new ArrayList<Grid>();
        for (Integer i = 0; i < maxColumn * maxRow; i++)
            grids.add(i, new Grid());
    }

    public void reset() {
        grids.forEach(v -> v = new Grid());
        return;
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
        Integer index = aColumn + aRow * maxColumn;
        return grids.get(index);
    }
}
