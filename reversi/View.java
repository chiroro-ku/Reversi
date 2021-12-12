package reversi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {
    public Model model;
    public Controller controller;

    private Integer tableWidth;

    public View(Model aModel) {
        model = aModel;
        tableWidth = 0;
        return;
    }

    public void updata() {
        this.repaint(0, 0, this.getWidth(), this.getHeight());
        return;
    }

    public void paintComponent(Graphics aGraphics) {
        tableWidth = this.getHeight();
        Table aTable = model.getTable();
        List<Grid> grids = aTable.getGrids();
        Integer maxColumn = aTable.getMaxColumn();
        Integer maxRow = aTable.getMaxRow();
        Integer gridWidth = tableWidth / maxColumn;
        paintPiece(aGraphics, model.getJudge().getPlayer().getViewColor(), maxColumn * gridWidth,
                (maxRow - 1) * gridWidth, gridWidth);
        grids.stream().filter(item -> item.getColor() >= 0).forEach(item -> paintGrid(aGraphics, item, gridWidth));
        return;
    }

    public void paintGrid(Graphics aGraphics, Grid aGrid, Integer width) {
        Integer aColumn = aGrid.getColumn();
        Integer aRow = aGrid.getRow();
        Integer x = aRow * width;
        Integer y = aColumn * width;
        aGraphics.setColor(Color.GREEN);
        aGraphics.fillRect(x, y, width, width);
        aGraphics.setColor(Color.BLACK);
        aGraphics.drawRect(x, y, width, width);
        if (!aGrid.isPlacePiece())
            paintPiece(aGraphics, aGrid.getPiece().getViewColor(), x, y, width);
    }

    public void paintPiece(Graphics aGraphics, Color aColor, Integer x, Integer y, Integer width) {
        aGraphics.setColor(aColor);
        aGraphics.fillOval(x, y, width, width);
        return;
    }

    public Integer getTableWidth() {
        return tableWidth;
    }
}