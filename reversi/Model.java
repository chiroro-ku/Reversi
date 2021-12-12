package reversi;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

public class Model {
    public View view;
    public Controller controller;

    private Judge judge;
    private Boolean gridSetting;
    private JFrame window;
    private String title;

    public Model(Judge aJudge, String aTitle) {
        judge = aJudge;
        gridSetting = true;
        title = aTitle;
        return;
    }

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

    public void setText(String aText) {
        String aString = title + " - " + aText;
        window.setTitle(aString);
        return;
    }

    public Judge getJudge() {
        return judge;
    }

    public Table getTable() {
        return judge.getTable();
    }

    public Boolean getPlacePiece() {
        return judge.getPlacePiece();
    }

    public Boolean getGridSetting() {
        return gridSetting;
    }

    public void tablePrint() {
        List<Player> aList = judge.getPlayers();
        aList.forEach(item -> System.out.print(item.getName() + item.getPiece().getCount() + " "));
        Table aTable = judge.getTable();
        System.out.print("e" + aTable.getEmptyPiece().getCount());
        System.out.println();
        return;
    }
}
