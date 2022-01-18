package reversi;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * コントローラー：制御まわりを専門に行う。
 */
public class Controller extends MouseInputAdapter {

    /**
     * 情報を握っているモデルのインスタンスを束縛する。
     */
    public Model model;

    /**
     * 表示を司るビューのインスタンスを束縛する。
     */
    public View view;

    /**
     * コントローラーのインスタンスである。モデルとビューを設定する。
     * 
     * @param aModel モデル
     * @param view ビュー
     */
    public Controller(Model aModel, View view) {

        // MVCモデルの構築する。
        this.model = aModel;
        this.view = view;

        // ビューにコントローラーを設定する。
        this.view.addMouseListener(this);
        this.view.addMouseMotionListener(this);
        this.view.addMouseWheelListener(this);

        return;
    }

    /**
     * 指定されたマウスイベントから、クリックされた座標を計算して処理を行う。
     * 
     * @param aMouseEvent マウスイベント
     */
    @Override
    public synchronized void mouseClicked(MouseEvent aMouseEvent) {

        // クリックされた座標を取得する。
        Point aPoint = aMouseEvent.getPoint();
        int x = (int) aPoint.getX();
        int y = (int) aPoint.getY();

        // テーブルの幅とグリッドの幅を取得する。
        Integer aTableWidth = view.getTableWidth();
        Integer aGridWidth = view.getGridWidth();

        // クリックされたグリッドの行と列を計算する。
        Integer aColumn = x / aGridWidth;
        Integer aRow = y / aGridWidth;

        if (model.isSettingTable()) { // テーブルの設定変更。
            if (x < aTableWidth && y < aTableWidth) {

                // 新しいテーブルの設定。
                model.newGameTable();
                view.updata();

            } else {

                // テーブルの設定を決定する。
                model.settingTableEnd();

            }
        } else if (model.isSettingPlayer()) { // プレイヤーの設定変更。
            if (x < aTableWidth && y < aTableWidth) {

                // プレイヤーを追加する。
                model.addPlayer();

            } else {

                // プレイヤーの設定を決定する。
                model.settingPlayerEnd();

            }

            // 設定の更新
            view.updata();

        } else if (model.isSettingGrid()) { // グリッドの設定変更
            if (x < aTableWidth && y < aTableWidth) {

                // グリッドの初期状態を変更する。
                model.settingGrid(aColumn, aRow);
                view.updata();

            } else {

                // グリッドの設定を決定する。
                model.settingGridEnd();
                model.gameStart();

            }
        } else if (model.isPlacePiece()) { // プレイヤーの行動。

            // 駒の配置、パスを実行する。
            model.placePiece(aColumn, aRow);

            // コンピュータに駒を配置させる。
            model.computerPlacePiece();

        } else if (model.isGameEnd()) { // ゲームのリスタート。
            if (x > aTableWidth || y > aTableWidth) {

                // 新しいゲームを設定する。
                model.restartGame();
                model.initialize();
                model.gameSetting();

                // ビューの更新
                view.updata();
            }
        }
        return;
    }
}
