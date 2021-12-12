package reversi;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class Controller extends MouseInputAdapter {
    public Model model;
    public View view;

    public Controller(Model aModel) {
        model = aModel;
        view = aModel.view;
        view.controller = this;
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
        view.addMouseWheelListener(this);
        return;
    }

    public synchronized void mouseClicked(MouseEvent aMouseEvent) {
        if(model.getGridSetting()){
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
        }
        else if (model.getPlacePiece()) {
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
