package reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * テーブル：グリッド全体の情報、グリッドの操作を行う。
 */
public class Table extends Object {

    /**
     * テーブルの最大列数を束縛している。
     */
    private Integer maxColumn;

    /**
     * テーブルの最大行数を束縛している。
     */
    private Integer maxRow;

    /**
     * グリッド全体のインスタンスを束縛している。
     */
    private List<Grid> grids;

    /**
     * 壁のグリッドを束縛している。
     */
    private Grid wall;

    /**
     * 空のグリッドのに配置する駒を束縛している。
     */
    private Piece empty;

    /**
     * テーブル上での方向性を束縛している。
     */
    private List<Integer> direction;

    /**
     * テーブルのインスタンスである。テーブルの大きさを指定する。
     * 
     * @param maxColumn 最大列数
     * @param maxRow    最大行数
     */
    public Table(Integer maxColumn, Integer maxRow) {

        // パラメーターの設定
        this.maxColumn = maxColumn;
        this.maxRow = maxRow;
        this.grids = new ArrayList<>();
        this.wall = new Grid(new Piece(-1));
        this.empty = new Piece(0);
        this.direction = new ArrayList<>(
                Arrays.asList(getDirectionUp(), getDirectionRight(), getDirectionDown(), getDirectionLeft(),
                        getDirectionUpRight(), getDirectionUpLeft(), getDirectionDownRight(), getDirectionDownLeft()));

        // 初期化
        initialize();

        return;
    }

    /**
     * グリッドのインスタンスを初期化する。
     */
    public void initialize() {
        IntStream.range(0, maxColumn * maxRow).forEach(i -> grids.add(new Grid(i, empty, gridColumn(i), gridRow(i))));
        grids.forEach(item -> item.initialize(nextGrids(item)));
        return;
    }

    /**
     * インデックスから列数を応答する。
     * 
     * @param index インデックス
     * @return 列数
     */
    public Integer gridColumn(Integer index) {
        return index % maxColumn;
    }

    /**
     * インデックスから行数を応答する。
     * 
     * @param index 行数
     * @return 行数
     */
    public Integer gridRow(Integer index) {
        return index / maxRow;
    }

    /**
     * 全ての隣のグリッドに応答する。
     * 
     * @param aGrid 中心のグリッド
     * @return グリッドのリスト
     */
    public List<Grid> nextGrids(Grid aGrid) {
        List<Grid> aList = new ArrayList<>();
        direction.forEach(item -> aList.add(nextGrid(aGrid, item)));
        return aList;
    }

    /**
     * 隣のグリッドに応答する。
     * 
     * @param aGrid 中心のグリッド
     * @param directionIndex 方向
     * @return 隣のグリッド
     */
    public Grid nextGrid(Grid aGrid, Integer directionIndex) {
        Integer index = aGrid.getIndex() + directionIndex;
        if (isWall(aGrid, directionIndex))
            return wall;
        return grids.get(index);
    }

    /**
     * 隣のグリッドの壁の是非を応答する。
     * 
     * @param aGrid 中心のグリッド
     * @param nextGridIndex 方向
     * @return 壁の是非
     */
    public Boolean isWall(Grid aGrid, Integer nextGridIndex) {
        Integer endGridColumn = maxColumn - 1;
        Integer endGridRow = maxRow - 1;
        Integer column = aGrid.getColumn();
        Integer row = aGrid.getRow();
        if (column == 0 && isDirectionLeft(nextGridIndex))
            return true;
        if (column == endGridColumn && isDirectionRight(nextGridIndex))
            return true;
        if (row == 0 && isDirectionUp(nextGridIndex))
            return true;
        if (row == endGridRow && isDirectionDown(nextGridIndex))
            return true;
        return false;
    }

    /**
     * グリッド全体の情報に応答する。
     * 
     * @return グリッドのリスト
     */
    public List<Grid> getGrids() {
        return grids;
    }

    /**
     * 最大列数に応答する。
     * 
     * @return 最大列数
     */
    public Integer getMaxColumn(){
        return maxColumn;
    }

    /**
     * 最大行数に応答する。
     * 
     * @return 最大行数
     */
    public Integer getMaxRow(){
        return maxRow;
    }

    /**
     * 列と行からグリッドのインスタンスを応答する。
     * 
     * @param aColumn 列数
     * @param aRow    行数
     * @return グリッド
     */
    public Grid getGrid(Integer aColumn,Integer aRow){
        Integer index = gridsIndex(aColumn, aRow);
        return grids.get(index);
    }

    /**
     * プレイヤーとグリッドから、駒の配置の可不可に応答する。
     * 
     * @param aPlayer プレイヤー
     * @param aGrid   グリッド
     * @return 駒の配置の可不可
     */
    public Boolean isPlacePiece(Player aPlayer,Grid aGrid){
        Integer color = aPlayer.getPiece().getColor();
        if(!aGrid.isPlacePiece()) return false;
        Integer index = 0;
        for (Grid aNextGrid : aGrid.getNextGrids()) {
            Integer nextColor = aNextGrid.getPiece().getColor();
            if (nextColor > 0 && nextColor != color && isPlacePieceColumn(color, aNextGrid.getNextGrid(index), index))
                return true;
            else
                index++;
        }
        return false;
    }

    /**
     * プレイヤーと列と行から、駒の配置の可不可に応答する。
     * 
     * @param aPlayer プレイヤー
     * @param aGrid   グリッド
     * @return 駒の配置の可不可
     */
    public Boolean isPlacePiece(Player aPlayer,Integer aColumn,Integer aRow){
        Grid aGrid = this.getGrid(aColumn, aRow);
        return isPlacePiece(aPlayer, aGrid);
    }

    /**
     * 駒の配置の可不可を列を見て判断する。
     * 
     * @param color     配置する駒の色
     * @param aNextGrid 隣のグリッド
     * @param index     インデックス
     * @return 駒の配置の可不可
     */
    public Boolean isPlacePieceColumn(Integer color, Grid aNextGrid, Integer index) {
        Integer nextColor = aNextGrid.getPiece().getColor();
        if (nextColor <= 0)
            return false;
        if (nextColor == color)
            return true;
        return isPlacePieceColumn(color, aNextGrid.getNextGrid(index), index);
    }

    /**
     * 列と行からインデックスを応答する。
     * 
     * @param aColumn 列
     * @param aRow    行
     * @return インデックス
     */
    public Integer gridsIndex(Integer aColumn,Integer aRow){
        return (aRow * maxColumn) + aColumn;
    }

    /**
     * 空のグリッドに配置する駒を応答する。
     * 
     * @return 駒
     */
    public Piece getEmpty(){
        return empty;
    }

    /**
     * 壁のグリッドを応答する。
     * 
     * @return グリッド
     */
    public Grid getWall(){
        return wall;
    }

    /**
     * 上のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionUp() {
        return -maxColumn;
    }

    /**
     * 下のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionDown() {
        return maxColumn;
    }

    /**
     * 右のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionRight() {
        return 1;
    }

    /**
     * 左のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionLeft() {
        return -1;
    }

    /**
     * 右上のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionUpRight() {
        return getDirectionUp() + getDirectionRight();
    }

    /**
     * 左上のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionUpLeft() {
        return getDirectionUp() + getDirectionLeft();
    }

    /**
     * 右下のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionDownRight() {
        return getDirectionDown() + getDirectionRight();
    }

    /**
     * 左下のグリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getDirectionDownLeft() {
        return getDirectionDown() + getDirectionLeft();
    }

    /**
     * インデックスが上方向か判断する。
     * 
     * @param index インデックス
     * @return 方向の是非
     */
    public Boolean isDirectionUp(Integer index) {
        if (index == getDirectionUp())
            return true;
        if (index == getDirectionUpRight())
            return true;
        if (index == getDirectionUpLeft())
            return true;
        return false;
    }

    /**
     * インデックスが右方向か判断する。
     * 
     * @param index インデックス
     * @return 方向の是非
     */
    public Boolean isDirectionRight(Integer index) {
        if (index == getDirectionRight())
            return true;
        if (index == getDirectionUpRight())
            return true;
        if (index == getDirectionDownRight())
            return true;
        return false;
    }

    /**
     * インデックスが下方向か判断する。
     * 
     * @param index インデックス
     * @return 方向の是非
     */
    public Boolean isDirectionDown(Integer index) {
        if (index == getDirectionDown())
            return true;
        if (index == getDirectionDownRight())
            return true;
        if (index == getDirectionDownLeft())
            return true;
        return false;
    }

    /**
     * インデックスが左方向か判断する。
     * 
     * @param index インデックス
     * @return 方向の是非
     */
    public Boolean isDirectionLeft(Integer index) {
        if (index == getDirectionLeft())
            return true;
        if (index == getDirectionUpLeft())
            return true;
        if (index == getDirectionDownLeft())
            return true;
        return false;
    }
}
