package reversi;

import java.util.ArrayList;
import java.util.List;

/**
 * グリッド：テーブルに配置されているグリッドの情報を掌握している。
 */
public class Grid extends Object {

    /**
     * テーブルに配置されているグリットのインデックスを掌握する。
     */
    private Integer index;

    /**
     * グリッドに配置されている駒を掌握する。
     */
    private Piece piece;

    /**
     * 隣のグリッドを掌握する。
     */
    private List<Grid> nextGrids;

    /**
     * テーブルに配置されているグリッドの行数を掌握する。
     */
    private Integer column;

    /**
     * テーブルに配置されているグリッドの列数を掌握する。
     */
    private Integer row;

    /**
     * 駒の配置の可不可を掌握する。
     */
    private Boolean placePiece;

    /**
     * グリッドのコンストラクトである。
     * 
     * @param index グリッドのインデックス
     * @param piece 駒
     * @param column 行
     * @param row 列
     */
    public Grid(Integer index,Piece piece,Integer column,Integer row){
        this.index = index;
        this.setPiece(piece);
        this.nextGrids = new ArrayList<>();
        this.column = column;
        this.row = row;
        this.placePiece = true;
        return;
    }

    /**
     * グリッドのコンストラクトである
     * 
     * @param piece 駒
     */
    public Grid(Piece piece){
        this.index = -1;
        this.setPiece(piece);
        this.placePiece = false;
        return;
    }

    /**
     * 壁のグリッドの是非に応答する。
     * 
     * @return 壁の是非
     */
    public Boolean isWallGrid(){
        if(piece.getColor() < 0)
            return true;
        return false;
    }

    /**
     * グリッドに隣のグリッドを設定する。
     * 
     * @param next 隣のグリッドのリスト
     */
    public void setNextGrids(List<Grid> next){
        this.nextGrids = next;
        return;
    }

    /**
     * グリッドに駒を配置する。
     * 
     * @param aPiece 駒
     */
    public void placePiece(Piece aPiece){
        this.piece.decrease();
        this.piece = aPiece;
        this.piece.increase();
        this.placePiece = false;
        return;
    }

    /**
     * グリッドに駒を設定する。
     * 
     * @param piece 駒
     */
    public void setPiece(Piece piece){
        this.piece = piece;
        this.piece.increase();
        this.placePiece = true;
        return;
    }

    /**
     * 駒の配置の是非に応答する。
     * 
     * @return 駒の配置の是非
     */
    public Boolean isPlacePiece(){
        return placePiece;
    }

    /**
     * グリッドのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getIndex(){
        return index;
    }

    /**
     * グリッドの行数に応答する。
     * 
     * @return グリッドの行
     */
    public Integer getColumn(){
        return column;
    }

    /**
     * グリッドの列数に応答する。
     * 
     * @return グリッドの列
     */
    public Integer getRow(){
        return row;
    }

    /**
     * 隣のグリッドのリストを取得する。
     * 
     * @return 隣のグリッドのリストを取得する
     */
    public List<Grid> getNextGrids(){
        return nextGrids;
    }

    /**
     * インデックスから隣のグリッドを応答する。
     * 
     * @param index インデックス
     * @return グリッド
     */
    public Grid getNextGrid(Integer index){
        return nextGrids.get(index);
    }

    /**
     * グリッドに配置されている駒を応答する。
     * 
     * @return 駒
     */
    public Piece getPiece(){
        return piece;
    }
}
