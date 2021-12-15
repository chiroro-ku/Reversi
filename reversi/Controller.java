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
     * コンストラクトである。モデルを設定し、MVC（モデル・ビュー・コントローラ）を構築する。
     * 
     * @param aModel モデルのインスタンス
     */
    public Controller(Model aModel) {
        model = aModel;
        view = aModel.view;
        view.controller = this;
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        view.addMouseWheelListener(this);
        return;
    }

    /**
     * 指定されたマウスイベントからクリックされた位置からグリッドを計算して処理を行う。
     * 
     * @param aMouseEvent マウスイベント
     */
    public synchronized void mouseClicked(MouseEvent aMouseEvent) {
        Point aPoint = aMouseEvent.getPoint();
        if (model.getTableSetting()) {
            model.tableSettring(aPoint);
        } else if (model.getPlayerSetting()) {
            model.playerSetting(aPoint);
        } else if (model.getGridSetting()){
            model.gridSetting(aPoint);
        }else if (model.getPlacePiece()) {
            model.placePiece(aPoint);
        }
        return;
    }
}
