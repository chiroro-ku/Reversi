package reversi;

import java.util.ArrayList;
import java.util.List;

/**
 * グリッド：盤面の一つのマス目。
 */
public class Grid extends Object {

    /**
     * グリッドに配置されている駒を掌握している。
     */
    private Piece piece;

    /**
     * 盤面で管理されてるインデックスを掌握している。
     */
    private Integer index;

    /**
     * 駒の配置の可不可を掌握している。
     */
    private Boolean placePiece;

    /**
     * 隣の周囲のグリッドを掌握している。
     */
    private List<Grid> nextGrids;

    /**
     * 盤面で管理されている列を掌握している。
     */
    private Integer column;

    /**
     * 盤面で管理されている行を掌握している。
     */
    private Integer row;

    /**
     * コンストラクトである。グリッドの色状態を設定する。
     * @param aPiece グリッドに配置する駒
     * @param aInteger 管理番号、インデックス
     */
    public Grid(Piece aPiece,Integer aInteger) {
        piece = aPiece;
        aPiece.increment();
        index = aInteger;
        placePiece = true;
        nextGrids = new ArrayList<>();
        return;
    }

    /**
     * コンストラクトである。表示しない壁の仮想のグリッドをコンストラクトする。
     * @param aPiece 壁のグリッドに配置する駒
     */
    public Grid(Piece aPiece) {
        piece = aPiece;
        placePiece = false;
        return;
    }

    /**
     * グリッドの初期化。列と行をグリッドに設定する。
     * @param maxColumn
     */
    public void initialize(Integer maxColumn) {
        row = index / maxColumn;
        column = index - (row * maxColumn);
        return;
    }

    /**
     * 隣の周囲のグリッドを設定する。
     * @param aSetNextGrids
     */
    public void setNextGrids(List<Grid> aSetNextGrids) {
        nextGrids = aSetNextGrids;
        return;
    }

    /**
     * 駒をグリッドに配置する。
     */
    public void setPiece(Piece aPiece) {
        piece.decrement();
        piece = aPiece;
        piece.increment();
        placePiece = false;
        return;
    }

    /**
     * グリッドに配置されている駒を応答する。
     * @return 駒
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * グリッドに配置されている駒の色を応答する。
     * @return 駒の色
     */
    public Integer getPieceColor() {
        return piece.getColor();
    }

    /**
     * グリッドの列を応答する。
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * グリッドの行を応答する。
     */
    public Integer getRow() {
        return row;
    }

    /**
     * 隣の周囲のグリッドを応答する。
     * @return グリッドのリスト
     */
    public List<Grid> getNextGrids(){
        return nextGrids;
    }

    /**
     * インデックスから隣のグリッドを応答する。
     * @return グリッド
     */
    public Grid getNextGrid(Integer index){
        return nextGrids.get(index);
    }

    /**
     * グリッドからインデックスを応答する。
     * @return インデックス
     */
    public Integer getNextGridIndex(Grid aGrid){
        return nextGrids.indexOf(aGrid);
    }

    /**
     * グリッドの駒の配置の可不可を応答する。
     * @return 配置の可不可
     */
    public Boolean isPlacePiece(){
        return placePiece;
    }

    /**
     * グリッドに駒の配置の可不可を設定する。
     */
    public void setPlacePiece(Boolean aBoolean){
        placePiece = aBoolean;
        return;
    }
}
