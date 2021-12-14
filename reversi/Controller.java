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
        if(model.getTableSetting()){
            Point aPoint = aMouseEvent.getPoint();
            int x = (int) aPoint.getX();
            int y = (int) aPoint.getY();
            Table aTable = model.getTable();
            Integer aTableWidth = view.getTableWidth();
            Integer aMaxColumn = aTable.getMaxColumn();
            Integer aGridWidth = aTableWidth / aMaxColumn;
            Integer aColumn = y / aGridWidth;
            Integer aRow = x / aGridWidth;
            model.tableSettring(aColumn, aRow);
        } else if (model.getPlayerSetting()) {
            Point aPoint = aMouseEvent.getPoint();
            int x = (int) aPoint.getX();
            int y = (int) aPoint.getY();
            Table aTable = model.getTable();
            Integer aTableWidth = view.getTableWidth();
            Integer aMaxColumn = aTable.getMaxColumn();
            Integer aGridWidth = aTableWidth / aMaxColumn;
            Integer aColumn = y / aGridWidth;
            Integer aRow = x / aGridWidth;
            model.playerSetting(aColumn, aRow);
        } else if (model.getGridSetting()) {
            Point aPoint = aMouseEvent.getPoint();
            int x = (int) aPoint.getX();
            int y = (int) aPoint.getY();
            Table aTable = model.getTable();
            Integer aTableWidth = view.getTableWidth();
            Integer aMaxColumn = aTable.getMaxColumn();
            Integer aGridWidth = aTableWidth / aMaxColumn;
            Integer aColumn = y / aGridWidth;
            Integer aRow = x / aGridWidth;
            model.gridSetting(aColumn, aRow);
        } else if (model.getPlacePiece()) {
            Point aPoint = aMouseEvent.getPoint();
            int x = (int) aPoint.getX();
            int y = (int) aPoint.getY();
            Table aTable = model.getTable();
            Integer aTableWidth = view.getTableWidth();
            Integer aMaxColumn = aTable.getMaxColumn();
            Integer aGridWidth = aTableWidth / aMaxColumn;
            Integer aColumn = y / aGridWidth;
            Integer aRow = x / aGridWidth;
            model.placePiece(aColumn, aRow);
        }
        return;
    }
}
