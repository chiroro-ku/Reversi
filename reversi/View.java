package reversi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class View extends JPanel {
    public Model model;
    public Controller controller;

    private Integer boardWidth;

    public View(Model aModel) {
        model = aModel;
        boardWidth = 0;
        return;
    }

    public void updata() {
        this.repaint(0, 0, this.getWidth(), this.getHeight());
        return;
    }

    public void paintComponent(Graphics aGraphics) {
        Integer width = this.getWidth();
        Integer height = this.getHeight();
        if (width >= height)
            boardWidth = height;
        else
            boardWidth = width;
        Table aBoard = model.getBoard();
        List<Grid> grids = aBoard.getGrids();
        Integer maxColumn = aBoard.getMaxColumn();
        Integer maxRow = aBoard.getMaxRow();
        Integer gridWidth = boardWidth / maxColumn;
        grids.forEach((v) -> {
            Integer index = grids.indexOf(v);
            Integer aColumn = index / maxColumn;
            Integer aRow = index - (aColumn * maxColumn);
            Integer x = aRow * gridWidth;
            Integer y = aColumn * gridWidth;
            aGraphics.setColor(Color.GREEN);
            aGraphics.fillRect(x, y, gridWidth, gridWidth);
            aGraphics.setColor(Color.BLACK);
            aGraphics.drawRect(x, y, gridWidth, gridWidth);
            Grid aGrid = (Grid)v;
            if(aGrid.getColor() == 1){
                aGraphics.setColor(Color.BLACK);
                aGraphics.fillOval(x, y, gridWidth, gridWidth);
            }else if(aGrid.getColor() == 2){
                aGraphics.setColor(Color.WHITE);
                aGraphics.fillOval(x, y, gridWidth, gridWidth);
            }
        });
    }

    public Integer getBoardWidth(){
        return boardWidth;
    }
}