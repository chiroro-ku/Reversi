package reversi;

import java.awt.Point;
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
     * 盤面の設定の可不可を束縛する。
     */
    private Boolean tableSetting;

    /**
     * プレイヤー設定の可不可を束縛する。
     */
    private Boolean playerSetting;

    /**
     * グリッドの設定の可不可を束縛する。
     */
    private Boolean gridSetting;

    /**
     * ウィンドウのフレームを束縛する。
     */
    private JFrame window;

    /**
     * ウィンドウのフレームタイトルを束縛する。フレームタイトルをテキストとして利用する。
     */
    private String title;

    /**
     * コンストラクトである。ジャッジとフレームのタイトルを設定する。
     * 
     * @param aJudge ジャッジのインスタンス
     * @param aTitle フレームタイトル
     */
    public Model(Judge aJudge, String aTitle) {
        judge = aJudge;
        tableSetting = true;
        playerSetting = true;
        gridSetting = true;
        title = aTitle;
        return;
    }

    public Model(Judge aJudge, String aTitle, Boolean aBoolean) {
        judge = aJudge;
        tableSetting = aBoolean;
        playerSetting = aBoolean;
        gridSetting = aBoolean;
        title = aTitle;
        return;
    }

    public void initialize() {
        return;
    }

    /**
     * MVC（モデル・ビュー・コントローラ）を構築して、ウィンドウを表示する。
     */
    public void open() {
        view = new View(this);
        controller = new Controller(this);
        window = new JFrame(title);
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
        this.update();
        return;
    }

    public void tableSettring(Point aPoint) {
        Integer index = view.getIndex(aPoint);
        if (index >= 0) {
            Table aTable = this.getTable();
            Integer aMaxColumn = aTable.getMaxColumn();
            Integer aMaxRow = aTable.getMaxRow();
            judge.setTable(new Table(aMaxColumn + 1, aMaxRow + 1));
            view.updata();
        } else {
            tableSetting = false;
            this.update();
        }
        return;
    }

    public void playerSetting(Point aPoint) {
        Integer index = view.getIndex(aPoint);
        if (index >= 0) {
            Table aTable = this.getTable();
            String aName = String.valueOf(judge.getPlayersNumber() + 1);
            Player aPlayer = new Player(aTable, aName);
            judge.addPlayer(aPlayer);
            view.updata();
        } else {
            playerSetting = false;
            judge.prepare();
            view.updata();
            this.update();
        }
        return;
    }

    /**
     * 行と列からグリッドの設定を変更する。
     * 
     * @param aColumn 列
     * @param aRow    行
     */
    public void gridSetting(Point aPoint) {
        Integer index = view.getIndex(aPoint);
        if (index >= 0) {
            Table aTable = this.getTable();
            Grid aGrid = aTable.getGrid(index);
            Piece aPiece = aGrid.getPiece();
            Integer aColor = aPiece.getColor();
            if (aColor == aTable.getEmptyPiece().getColor()) {
                aPiece = judge.getPlayer(aColor + 1).getPiece();
                aGrid.setPiece(aPiece);
                aGrid.setPlacePiece(false);
            } else if (aColor == judge.getPlayersNumber()) {
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
        } else {
            gridSetting = false;
            this.update();
        }
        return;
    }

    /**
     * 行と列から駒を配置する。
     * 
     * @param aColumn 列
     * @param aRow    行
     */
    public void placePiece(Point aPoint) {
        int x = (int) aPoint.getX();
        int y = (int) aPoint.getY();
        Table aTable = this.getTable();
        Integer aMaxColumn = aTable.getMaxColumn();
        Integer aTableWidth = view.getTableWidth();
        Integer aGridWidth = aTableWidth / aMaxColumn;
        Integer aColumn = y / aGridWidth;
        Integer aRow = x / aGridWidth;
        if (aTable.isTable(aColumn, aRow))
            judge.placePieceAction(aColumn, aRow);
        else
            judge.changePlayer();
        this.update();
        view.updata();
        return;
    }

    /**
     * テキストを設定する。
     * フレームタイトルの変更をしている。
     * 
     * @param aText テキスト
     */
    public void setText(String aText) {
        String aString = title + " - " + aText;
        window.setTitle(aString);
        return;
    }

    /**
     * ジャッジに応答する。
     * 
     * @return ジャッジ
     */
    public Judge getJudge() {
        return judge;
    }

    /**
     * テーブルに応答する。
     * 
     * @return テーブル
     */
    public Table getTable() {
        return judge.getTable();
    }

    public Boolean getTableSetting() {
        return tableSetting;
    }

    public Boolean getPlayerSetting() {
        return playerSetting;
    }

    /**
     * グリッドの設定の可不可を応答する。
     * 
     * @return 可不可
     */
    public Boolean getGridSetting() {
        return gridSetting;
    }

    /**
     * 駒の配置の可不可を応答する。
     * 
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
        Table aTable = this.getTable();
        System.out.print("e" + aTable.getEmptyPiece().getCount());
        System.out.println();
        return;
    }

    public void update() {
        if (tableSetting)
            this.setText("Table - Setting");
        else if (playerSetting)
            this.setText("Player - Setting");
        else if (gridSetting)
            this.setText("Grid - Setting");
        else if (judge.getPlacePiece())
            this.setText("Game - " + judge.getCurrentPlayer().getName());
        else if (judge.isEnd())
            this.setText("Game - Win - " + judge.getCurrentPlayer().getName());
        return;
    }
}
