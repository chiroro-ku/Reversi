package reversi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * ビュー：ウィンドウの中に配置する、表示を司るコンポーネントの一種である。
 */
@SuppressWarnings("serial")
public class View extends JPanel {

    /**
     * 情報を握っているモデルのインスタンスを束縛する。
     */
    public Model model;

    /**
     * 自身を配置するウィンドウのインスタンスを束縛する。
     */
    private JFrame window;

    /**
     * ビューのインスタンスである。モデルを設定する。
     *
     * @param aModel モデルのインスタンス
     */
    public View(Model aModel) {

        // パラメータを設定する。
        this.model = aModel;
        this.window = new JFrame();

        // 初期化する。
        this.initialize();

        return;
    }

    /**
     * ウィンドウの初期化。
     */
    private void initialize() {

        // ウィンドウを初期化して自身を追加する。
        window.add(this);
        Dimension aDimension = new Dimension(800, 600);
        window.setSize(aDimension);
        window.setMinimumSize(aDimension);
        window.setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer x = (screenSize.width / 2) - (aDimension.width / 2);
        Integer y = (screenSize.height / 2) - (aDimension.height / 2);
        window.setLocation(x, y);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addNotify();
        window.setVisible(true);
        window.toFront();

        // テキストを更新する。
        updateText();
        return;
    }

    /**
     * テキストを更新する。
     */
    public void updateText() {
        window.setTitle(model.getText());
        return;
    }

    /**
     * 指定されたグラフィックスに対して、テーブルとプレイヤーを描画する。
     *
     * @param aGraphics グラフィックスコンテキスト
     */
    @Override
    public void paintComponent(Graphics aGraphics) {
        Integer gridWidth = getGridWidth();
        List<Grid> grids = model.getGameTableGrids();
        grids.stream().filter(item -> (!item.isWallGrid())).forEach(item -> paintGrid(aGraphics, item, gridWidth));
        List<Player> players = model.getGamePlayers();
        players.forEach(item -> paintPiece(aGraphics, item.getPiece(), model.getGameTable().getMaxColumn() * gridWidth,
                (item.getIndex() - 1) * gridWidth, gridWidth));
        paintPiece(aGraphics, model.getGame().getCurrentPlayer().getPiece(),
                model.getGameTable().getMaxColumn() * gridWidth,
                (model.getGameTable().getMaxRow() - 1) * gridWidth, gridWidth);
        return;
    }

    /**
     * 指定されたグラフィックスに対して、グリッドを描画する。
     *
     * @param aGraphics グラフィックスコンテキスト
     * @param aGrid     描画するグリッド
     * @param width     グリッドの幅
     */
    private void paintGrid(Graphics aGraphics, Grid aGrid, Integer width) {

        // グリッドの行と列を取得する。
        Integer aColumn = aGrid.getColumn();
        Integer aRow = aGrid.getRow();

        // 描画座標を計算する。
        Integer x = aColumn * width;
        Integer y = aRow * width;

        // グリッドの背景を描画する。
        aGraphics.setColor(Color.GREEN);
        aGraphics.fillRect(x, y, width, width);

        // グリッドの枠を描画する。
        aGraphics.setColor(Color.BLACK);
        aGraphics.drawRect(x, y, width, width);

        // 駒を描画する。
        Piece aPiece = aGrid.getPiece();
        if (aPiece.getColor() > 0) // グリッドに駒がある場合
            paintPiece(aGraphics, aPiece, x, y, width);

        return;
    }

    /**
     * 指定されたグラフィックスに対して、駒を描画する。
     *
     * @param aGraphics グラフィックスコンテキスト
     * @param aPiece    描画する駒
     * @param x         描画するx座標
     * @param y         描画するy座標
     * @param width     駒の幅
     */
    private void paintPiece(Graphics aGraphics, Piece aPiece, Integer x, Integer y, Integer width) {

        // 駒の情報を取得する
        Color color = aPiece.getUIColor();

        // 駒を描画する。
        aGraphics.setColor(color);
        aGraphics.fillOval(x, y, width, width);

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
     * テーブルの幅に応答する。
     *
     * @return テーブルの幅
     */
    public Integer getTableWidth() {
        return this.getHeight();
    }

    /**
     * テーブルの情報から、グリッドの幅を応答する。
     *
     * @return グリッドの幅
     */
    public Integer getGridWidth() {

        // テーブルの情報を取得する。
        Table aTable = model.getGameTable();
        Integer aTableWidth = this.getHeight();

        // グリッドの幅を計算する。
        Integer maxColumn = aTable.getMaxColumn();
        Integer width = aTableWidth / maxColumn;

        return width;
    }
}
