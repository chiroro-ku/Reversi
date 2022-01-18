package reversi;

import java.util.List;

/**
 * プレイヤー：駒の操作を行う。
 */
public class Player extends Object{

    /**
     * プレイヤーのインデックスを掌握している。
     */
    protected Integer index;

    /**
     * プレイヤーの名前を掌握している。
     */
    protected String name;

    /**
     * プレイヤーが配置する駒を掌握している。
     */
    protected Piece piece;

    /**
     * プレイヤーが利用しているテーブルを掌握している。
     */
    protected Table table;
    
    /**
     * コンストラクトである。
     * 
     * @param index インデックス
     * @param name 名前
     * @param table 使用するテーブル
     */
    public Player(Integer index,String name,Table table){
        this.index = index;
        this.name = name;
        this.piece = new Piece(index);
        this.table = table;
        return;
    }

    /**
     * 行と列から駒を配置する。
     * 
     * @param aColumn 行
     * @param aRow 列
     */
    public void placePiece(Integer aColumn,Integer aRow){
        Grid aGrid = table.getGrid(aColumn, aRow);
        aGrid.placePiece(piece);
        reversi(aGrid);
        return;
    }

    /**
     * 駒を反転させる。
     * 
     * @param aGrid 駒を配置したグリッド
     */
    protected void reversi(Grid aGrid) {
        List<Grid> nextGrids = aGrid.getNextGrids();

        // 隣のグリッドを順々に見ていき反転させる
        nextGrids.stream().filter(item -> reversiNext(item, nextGrids.indexOf(item)))
                .forEach(item -> item.placePiece(piece));
        return;
    }

    /**
     * 自身を再起的に呼び出して、隣の駒を反転させる。
     * 
     * @param aGrid グリッド
     * @param index インデックス
     * @return 駒の反転の可不可
     */
    protected Boolean reversiNext(Grid aGrid, Integer index) {
        Integer color = aGrid.getPiece().getColor();
        if (color > 0 && color != piece.getColor() && reversiColumn(aGrid.getNextGrid(index), index))
            return true;
        return false;
    }

    /**
     * 反転が可能な列か判断する。
     * 
     * @param aGrid グリッド
     * @param index インデックス
     * @return 反転の可不可
     */
    private Boolean reversiColumn(Grid aGrid, Integer index) {
        Integer color = aGrid.getPiece().getColor();
        if (color <= 0)
            return false;
        if (color == piece.getColor())
            return true;
        if (reversiColumn(aGrid.getNextGrid(index), index)) {
            aGrid.placePiece(piece);
            return true;
        }
        return false;
    }

    /**
     * テーブルを設定する。
     * 
     * @param table テーブル
     */
    public void setTable(Table table){
        this.table = table;
        return;
    }

    /**
     * プレイヤーが掌握している駒に応答する。
     * 
     * @return 駒
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * プレイヤーがコンピュータかどうか判断する。
     * 
     * @return コンピューターの是非
     */
    public Boolean isComputer(){
        return false;
    }

    /**
     * プレイヤーのインデックスに応答する。
     * 
     * @return インデックス
     */
    public Integer getIndex(){
        return index;
    }

    /**
     * プレイヤーの名前に応答する。
     * 
     * @return 名前
     */
    public String getName(){
        return name;
    }
}
