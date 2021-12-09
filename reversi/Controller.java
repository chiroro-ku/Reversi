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

    public void mouseClicked(MouseEvent aMouseEvent) {
        if(!model.getSet()) return;
        Point aPoint = aMouseEvent.getPoint();
        int x = (int) aPoint.getX();
        int y = (int) aPoint.getY();
        Table aBoard = model.getBoard();
        Integer aBoardWidth = view.getBoardWidth();
        Integer aMaxColumn = aBoard.getMaxColumn();
        Integer aGridWidth = aBoardWidth / aMaxColumn;
        Integer aColumn = y / aGridWidth;
        Integer aRow = x / aGridWidth;
        model.set(aColumn, aRow);
        return;
    }
}
