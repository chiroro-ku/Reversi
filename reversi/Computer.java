package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * コンピューター：コンピュータの操作をする。
 */
public class Computer extends Player {

    /**
     * コンストラクトである。
     * 
     * @param index インデックス
     * @param table 使用するテーブル
     */
    public Computer(Integer index, Table table) {
        super(index, "com", table);
    }

    /**
     * 駒を配置する。
     */
    public void placePiece() {

        // 駒の配置
        Grid aGrid = preload();
        aGrid.placePiece(piece);
        super.reversi(aGrid);
        return;
    }

    private Grid preload(){

        // 反転できる駒の数のリスト
        List<Integer> aList = new ArrayList<>();

        // 配置可能なグリッドに駒を配置して、反転できる数をリストに追加する
        List<Grid> grids = table.getGrids();
        grids.forEach(item -> aList.add(reversiNumber(item)));

        // 最も駒を反転することができたグリッドに駒を配置する
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        Integer index = aList.indexOf(max.get());
        Grid aGrid = grids.get(index);

        return aGrid;
    }

    /**
     * 反転することができる駒の数を計算する。
     * 
     * @param aGrid グリッド
     * @return 数
     */
    private Integer reversiNumber(Grid aGrid) {
        if (!table.isPlacePiece(this, aGrid))
            return 0;
        List<Integer> aList = new ArrayList<>();
        List<Grid> nextGrids = aGrid.getNextGrids();
        nextGrids.stream().filter(item -> this.isReversiNext(item, nextGrids.indexOf(item)))
                .forEach(item -> aList.add(reversiColumnNumber(item, nextGrids.indexOf(item), 0)));
        Integer sum = aList.stream().mapToInt(value -> value).sum();
        return sum;
    }

    /**
     * 隣の駒が反転できるか判断する
     * 
     * @param aGrid グリッド
     * @param index インデックス
     * @return 駒の配置の可不可
     */
    private Boolean isReversiNext(Grid aGrid, Integer index) {
        Integer color = aGrid.getPiece().getColor();
        if (color > 0 && color != piece.getColor())
            return true;
        return false;
    }

    /**
     * 自身を再帰的に呼び出し、反転できる駒の数を計算する。
     * 
     * @param aGrid 現在のグリッド
     * @param index インデックス
     * @param number 数
     * @return 数
     */
    private Integer reversiColumnNumber(Grid aGrid, Integer index, Integer number) {
        Integer aGridColor = aGrid.getPiece().getColor();
        if (aGridColor <= 0)
            return 0;
        if (aGridColor == piece.getColor())
            return number;
        return reversiColumnNumber(aGrid.getNextGrid(index), index, number + 1);
    }

    /**
     * プレイヤーがコンピューターかどうか判断する。
     */
    public Boolean isComputer() {
        return true;
    }
}
