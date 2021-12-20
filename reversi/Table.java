package reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 盤面：グリッド全体のデータを持っている。
 */
public class Table extends Object {

    /**
     * 盤面の列の長さ。
     */
    private Integer maxColumn;

    /**
     * 盤面全体の行の長さ。
     */
    private Integer maxRow;

    /**
     * 壁のグリッド。
     */
    private Grid wall;

    /**
     * 空のグリッドの配置する駒。
     */
    private Piece empty;

    /**
     * グリッド全体を掌握している。
     */
    private List<Grid> grids;

    /**
     * 盤面のベクトル値。
     */
    private List<Integer> direction;

    /**
     * コンストラクトである。盤面の列の長さと、行の長さを設定して盤面をコンストラクトする。
     * @param aMaxColumn 列の長さ
     * @param aMaxRow 行の長さ
     */
    public Table(Integer aMaxColumn, Integer aMaxRow) {
        maxColumn = aMaxColumn;
        maxRow = aMaxRow;
        direction = Arrays.asList(-maxColumn, 1, maxColumn, -1, -maxColumn - 1, -maxColumn + 1, maxColumn + 1,
                maxColumn - 1);
        this.initialize();
        return;
    }

    /**
     * グリッドのコンストラクト、初期化を行う。
     */
    public void initialize() {
        wall = new Grid(new Piece(-1));
        empty = new Piece(0);
        grids = new ArrayList<>();
        IntStream.range(0, maxColumn * maxRow).forEach(i -> grids.add(new Grid(empty,i)));
        grids.forEach(item -> {
            item.initialize(maxColumn);
            item.setNextGrids(nextGridList(item));
        });
        return;
    }

    /**
     * 与えられたグリッドの周囲のグリッドに応答する。
     * @param aGrid
     * @return グリッドのリスト
     */
    public List<Grid> nextGridList(Grid aGrid) {
        List<Grid> aList = new ArrayList<>();
        Integer index = grids.indexOf(aGrid);
        direction.forEach(item -> {
            if (isEndGrid(aGrid, item))
                aList.add(wall);
            else
                aList.add(grids.get(index + item));
        });
        return aList;
    }

    /**
     * 
     * @param aGrid
     * @param aDirection
     * @return
     */
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

    /**
     * 
     * @param aPlayer
     * @param aColumn
     * @param aRow
     * @return
     */
    public Boolean isPlacePiece(Player aPlayer, Integer aColumn, Integer aRow) {
        Integer aColor = aPlayer.getColor();
        Grid aGrid = this.getGrid(aColumn, aRow);
        if(!aGrid.isPlacePiece()) return false;
        Integer index = 0;
        for (Grid aNextGrid : aGrid.getNextGrids()) {
            Integer aGridColor = aNextGrid.getPieceColor();
            if (aGridColor > 0 && aGridColor != aColor && isPlacePieceColumn(aColor, aNextGrid.getNextGrid(index), index))
                return true;
            else
                index++;
        }
        return false;
    }

    /**
     * 
     * @param aPlayer
     * @param aColumn
     * @param aRow
     * @return
     */
    public Boolean isPlacePiece(Player aPlayer, Grid aGrid) {
        Integer aColor = aPlayer.getColor();
        if(!aGrid.isPlacePiece()) return false;
        Integer index = 0;
        for (Grid aNextGrid : aGrid.getNextGrids()) {
            Integer aGridColor = aNextGrid.getPieceColor();
            if (aGridColor > 0 && aGridColor != aColor && isPlacePieceColumn(aColor, aNextGrid.getNextGrid(index), index))
                return true;
            else
                index++;
        }
        return false;
    }

    /**
     * 
     * @param aPlayer
     * @param aColumn
     * @param aRow
     * @return
     */
    public Boolean isPlacePiece(Player aPlayer, Integer i) {
        Integer aColor = aPlayer.getColor();
        Grid aGrid = grids.get(i);
        if(!aGrid.isPlacePiece()) return false;
        Integer index = 0;
        for (Grid aNextGrid : aGrid.getNextGrids()) {
            Integer aGridColor = aNextGrid.getPieceColor();
            if (aGridColor > 0 && aGridColor != aColor && isPlacePieceColumn(aColor, aNextGrid.getNextGrid(index), index))
                return true;
            else
                index++;
        }
        return false;
    }

    /**
     * 
     * @param aColor
     * @param aGrid
     * @param index
     * @return
     */
    public Boolean isPlacePieceColumn(Integer aColor, Grid aGrid, Integer index) {
        Integer aGridColor = aGrid.getPieceColor();
        if (aGridColor <= 0)
            return false;
        if (aGridColor == aColor)
            return true;
        return isPlacePieceColumn(aColor, aGrid.getNextGrid(index), index);
    }

    public Boolean isTable(Integer aColumn,Integer aRow){
        if(aColumn < maxColumn && aRow < maxRow)
            return true;
        return false;
    }

    /**
     * 列と行からグリッドを応答する。
     * @param aColumn
     * @param aRow
     * @return グリッド
     */
    public Grid getGrid(Integer aColumn, Integer aRow) {
        Integer index = this.getIndex(aColumn, aRow);
        return this.getGrid(index);
    }

    /**
     * 列と行からグリッドのインデックスを応答する。
     * @param グリッドリストのインデックス
     */
    public Integer getIndex(Integer aColumn, Integer aRow) {
        return aColumn + (aRow * maxColumn);
    }

    public Grid getGrid(Integer index){
        return grids.get(index);
    }

    /**
     * 空きグリッドの数を応答する。
     * @return 個数
     */
    public Integer getEmptyCount(){
        return empty.getCount();
    }

    /**
     * 壁のグリッドを応答する。
     * @return 壁グリッド
     */
    public Grid getWallGrid(){
        return wall;
    }

    /**
     * 空のグリッドに配置される駒を応答する。
     * @return 空の駒
     */
    public Piece getEmptyPiece(){
        return empty;
    }

    /**
     * 列の長さを応答する。
     * @return 列の長さ
     */
    public Integer getMaxColumn() {
        return maxColumn;
    }

    /**
     * 行の長さを応答する。
     * @return 行の長さ
     */
    public Integer getMaxRow() {
        return maxRow;
    }

    /**
     * グリッド全体のコンストラクトを応答する。
     */
    public List<Grid> getGrids() {
        return grids;
    }
}
