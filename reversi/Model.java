package reversi;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Model {
    public View view;
    public Controller controller;

    private Judge judge;

    public Model(Judge aJudge) {
        judge = aJudge;
        return;
    }

    public void open(String aString) {
        view = new View(this);
        controller = new Controller(this);
        JFrame aWindow = new JFrame(aString);
        aWindow.add(view);
        Dimension aDimension = new Dimension(800, 600);
        aWindow.setSize(aDimension);
        aWindow.setMinimumSize(aDimension);
        aWindow.setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Integer x = (screenSize.width / 2) - (aDimension.width / 2);
        Integer y = (screenSize.height / 2) - (aDimension.height / 2);
        aWindow.setLocation(x, y);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.addNotify();
        aWindow.setVisible(true);
        aWindow.toFront();
        return;
    }

    public void set(Integer aColumn, Integer aRow) {
        Table aTable = judge.getTable();
        if (aColumn < aTable.getMaxColumn() && aRow < aTable.getMaxRow())
            judge.setAction(aColumn,aRow);
        else
            judge.changePlayer();
        view.updata();
        return;
    }

    public Judge getJudge(){
        return judge;
    }

    public Table getBoard() {
        return judge.getTable();
    }

    public Boolean getSet() {
        return judge.getSet();
    }

}
