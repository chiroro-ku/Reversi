package reversi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

/**
 * モデル：描画する文字列を表現するモデルである。
 */
public class Model {

    /**
	 * 描画する文字列を束縛するフィールドである。
	 */
    public View view;

    /**
	 * 描画する文字列のフォントサイズ群を定数として束縛しておくフィールドである。
	 */
    public Controller controller;

    /**
     * 盤面とプレイヤーを束縛したフィールドである。
     */
    private Judge judge;

    /**
     * グリッドの設定の可不可を束縛する。
     */
    private Boolean gridSetting;

    /**
     * ウィンドウのフレームを束縛する。
     */
    private JFrame window;

    /**
     * ウィンドウのフレームタイトルを束縛する。
     * フレームタイトルをテキストとして利用する。
     */
    private String title;

    /**
     * コンストラクトである。ジャッジとフレームのタイトルを設定する。
     * @param aJudge ジャッジのインスタンス
     * @param aTitle フレームタイトル
     */
    public Model(Judge aJudge, String aTitle) {
        judge = aJudge;
        gridSetting = true;
        title = aTitle;
        return;
    }

    /**
     * MVC（モデル・ビュー・コントローラ）を構築して、ウィンドウを表示する。
     */
    public void open() {
        view = new View(this);
        controller = new Controller(this);
        window = new JFrame(title);
        this.setText("Grid - Setting");
        window.add(view);
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
        return;
    }

    /**
     * 行と列からグリッドの設定を変更する。
     * @param aColumn 列
     * @param aRow 行
     */
    public void gridSetting(Integer aColumn, Integer aRow) {
        Table aTable = judge.getTable();
        if (aColumn >= aTable.getMaxColumn() || aRow >= aTable.getMaxRow()) {
            this.setText("Game - " + judge.getPlayer().getName());
            gridSetting = false;
            return;
        }
        Grid aGrid = aTable.getGrid(aColumn, aRow);
        Piece aPiece = aGrid.getPiece();
        Integer aColor = aPiece.getColor();
        if (aColor == aTable.getEmptyPiece().getColor()) {
            aPiece = judge.getPlayer(aColor + 1).getPiece();
            aGrid.setPiece(aPiece);
            aGrid.setPlacePiece(false);
        } else if (aColor + 1 == Player.getPlayerNumber()) {
            aPiece = aTable.getWallGrid().getPiece();
            aGrid.setPiece(aPiece);
        } else if (aColor > 0) {
            aPiece = judge.getPlayer(aColor + 1).getPiece();
            aGrid.setPiece(aPiece);
        } else {
            aPiece = aTable.getEmptyPiece();
            aGrid.setPiece(aPiece);
            aGrid.setPlacePiece(true);
        }
        view.updata();
        return;
    }

    /**
     * 行と列から駒を配置する。
     * @param aColumn 列
     * @param aRow 行
     */
    public void placePiece(Integer aColumn, Integer aRow) {
        Table aTable = judge.getTable();
        if (aColumn < aTable.getMaxColumn() && aRow < aTable.getMaxRow())
            judge.placePieceAction(aColumn, aRow);
        else
            judge.changePlayer();
        if (judge.isEnd())
            this.setText("Game - Win -" + judge.getPlayer().getName());
        else
            this.setText("Game - " + judge.getPlayer().getName());
        view.updata();
        return;
    }

    /**
     * テキストを設定する。
     * フレームタイトルの変更をしている。
     * @param aText テキスト
     */
    public void setText(String aText) {
        String aString = title + " - " + aText;
        window.setTitle(aString);
        return;
    }

    /**
     * ジャッジに応答する。
     * @return ジャッジ
     */
    public Judge getJudge() {
        return judge;
    }

    /**
     * テーブルに対応する。
     * @return ジャッジに対応する。
     */
    public Table getTable() {
        return judge.getTable();
    }

    /**
     * グリッドの設定の可不可に対応する。
     * @return 可不可
     */
    public Boolean getGridSetting() {
        return gridSetting;
    }

    /**
     * 駒の配置の可不可に対応する。
     * @return 可不可
     */
    public Boolean getPlacePiece() {
        return judge.getPlacePiece();
    }

    /**
     * デバック用。駒の数と空のグリッドの数を出力する。
     */
    public void tablePrint() {
        List<Player> aList = judge.getPlayers();
        aList.forEach(item -> System.out.print(item.getName() + item.getPiece().getCount() + " "));
        Table aTable = judge.getTable();
        System.out.print("e" + aTable.getEmptyPiece().getCount());
        System.out.println();
        return;
    }
}
