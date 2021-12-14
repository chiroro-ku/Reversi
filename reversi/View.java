package reversi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

/**
 * ビュー：ウィンドウの中に配置するコンポーネントの一種である。
 */
@SuppressWarnings("serial")
public class View extends JPanel {

    /**
	 * モデルを束縛するフィールドである。
	 */
    public Model model;

    /**
	 * コントローラを束縛するフィールドである。
	 */
    public Controller controller;

    /**
     * 盤面全体の幅である。
     */
    private Integer tableWidth;

    /**
     * コンストラクトである。モデルを設定する。
     * @param aModel モデルのインスタンス
     */
    public View(Model aModel) {
        model = aModel;
        tableWidth = 0;
        return;
    }

    /**
     * データを読み込んで再描画する。
     */
    public void updata() {
        this.repaint(0, 0, this.getWidth(), this.getHeight());
        return;
    }

    /**
     * 指定されたグラフィックスに対して、盤面とプレイヤを描画する。
     * @param aGraphics グラフィックスコンテキスト
     */
    public void paintComponent(Graphics aGraphics) {
        tableWidth = this.getHeight();
        Table aTable = model.getTable();
        List<Grid> grids = aTable.getGrids();
        Integer maxColumn = aTable.getMaxColumn();
        Integer maxRow = aTable.getMaxRow();
        Integer gridWidth = tableWidth / maxColumn;
        List<Player> players = model.getJudge().getPlayers();
        players.forEach(item -> paintPiece(aGraphics, item.getViewColor(), maxColumn * gridWidth,
                (item.getColor() - 1) * gridWidth, gridWidth));
        paintPiece(aGraphics, model.getJudge().getCurrentPlayer().getViewColor(), maxColumn * gridWidth,
                (maxRow - 1) * gridWidth, gridWidth);
        grids.stream().filter(item -> item.getPieceColor() >= 0).forEach(item -> paintGrid(aGraphics, item, gridWidth));
        return;
    }

    /**
     * 一つのグリッドを描画する。
     * @param aGraphics グラフィックスコンテキスト
     * @param aGrid グリッド
     * @param width グリッドを描画するときの幅
     */
    public void paintGrid(Graphics aGraphics, Grid aGrid, Integer width) {
        Integer aColumn = aGrid.getColumn();
        Integer aRow = aGrid.getRow();
        Integer x = aRow * width;
        Integer y = aColumn * width;
        aGraphics.setColor(Color.GREEN);
        aGraphics.fillRect(x, y, width, width);
        aGraphics.setColor(Color.BLACK);
        aGraphics.drawRect(x, y, width, width);
        if (!aGrid.isPlacePiece())
            paintPiece(aGraphics, aGrid.getPiece().getViewColor(), x, y, width);
    }

    /**
     * 一つの駒を描画する。
     * @param aGraphics グラフィックスコンテキスト
     * @param aColor 駒の色
     * @param x 駒を描画するときのx座標
     * @param y 駒を描画するときのy座標
     * @param width 駒の幅
     */
    public void paintPiece(Graphics aGraphics, Color aColor, Integer x, Integer y, Integer width) {
        aGraphics.setColor(aColor);
        aGraphics.fillOval(x, y, width, width);
        return;
    }

    /**
     * 盤面全体の幅に応答する。
     * @return 盤面全体の幅
     */
    public Integer getTableWidth() {
        return tableWidth;
    }
}