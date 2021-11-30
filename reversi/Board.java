package reversi;

import java.util.HashMap;
import java.util.Map;

public class Board extends Object {
    private Integer maxColumn;
    private Integer maxRow;
    private Map<Integer, Grid> grids;

    public Board(Integer aMaxColumn, Integer aMaxRow) {
        maxColumn = aMaxColumn;
        maxRow = aMaxRow;
        this.initialize();
    }

    public void initialize() {
        grids = new HashMap<Integer, Grid>();
        for (Integer i = 0; i < maxColumn * maxRow; i++)
            grids.put(i, new Grid());
    }

    public void reset() {
        grids.forEach((key, value) -> grids.replace(key, new Grid()));
        return;
    }

    public Integer getMaxColumn(){
        return maxColumn;
    }

    public Integer getMaxRow(){
        return maxRow;
    }

    public Map<Integer,Grid> getGrids(){
        return grids;
    }

    public Grid getGrid(Integer aColumn,Integer aRow){
        Integer key = aColumn * maxColumn + aRow;
        return grids.get(key);
    }
}
