package reversi;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

public class View extends JPanel {
    public Model model;
    public Controller controller;

    public View(Model aModel) {
        model = aModel;
        return;
    }

    public void updata() {
        this.repaint(0, 0, this.getWidth(), this.getHeight());
        return;
    }

    public void paintComponent(Graphics aGraphics) {
        Integer width = this.getWidth();
        Integer height = this.getHeight();
        Integer boardWidth;
        if (width >= height)
            boardWidth = height;
        else
            boardWidth = width;
        Board aBoard = model.getBoard();
        Map<Integer, Grid> grids = aBoard.getGrids();
        Integer aMaxColumn = aBoard.getMaxColumn();
        Integer aMaxRow = aBoard.getMaxRow();
        Integer gridWidth = boardWidth / aMaxColumn;
        grids.forEach((i , v) -> {
            Integer aColumn = i / aMaxColumn;
            Integer aRow = i - (aColumn * aMaxColumn);
            Integer x = aRow * gridWidth;
            Integer y = aColumn * gridWidth;
            aGraphics.setColor(Color.GREEN);
            aGraphics.fillRect(x, y, gridWidth, gridWidth);
            aGraphics.setColor(Color.BLACK);
            aGraphics.drawRect(x, y, gridWidth, gridWidth);
            Grid aGrid = (Grid)v;
            if(aGrid.getColor() > 0){
                aGraphics.setColor(Color.BLACK);
                aGraphics.fillOval(x, y, gridWidth, gridWidth);
            }else if(aGrid.getColor() < 0){
                aGraphics.setColor(Color.WHITE);
                aGraphics.fillOval(x, y, gridWidth, gridWidth);
            }
        });
    }
}