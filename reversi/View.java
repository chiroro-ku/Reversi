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
        piece(aGraphics, model.getJudge().getPlayer().getColor(), maxColumn * gridWidth, (maxRow-1)*gridWidth, gridWidth);
        grids.forEach(item -> {
            Integer aColumn = item.getColumn();
            Integer aRow = item.getRow();
            Integer x = aRow * gridWidth;
            Integer y = aColumn * gridWidth;
            aGraphics.setColor(Color.GREEN);
            aGraphics.fillRect(x, y, gridWidth, gridWidth);
            aGraphics.setColor(Color.BLACK);
            aGraphics.drawRect(x, y, gridWidth, gridWidth);
            piece(aGraphics, item.getColor(), x, y, gridWidth);
        });
        return;
    }

    public void piece(Graphics aGraphics, Integer aColor,Integer x, Integer y,Integer width){
        if(aColor == 1){
            aGraphics.setColor(Color.BLACK);
            aGraphics.fillOval(x, y, width, width);
        }else if(aColor == 2){
            aGraphics.setColor(Color.WHITE);
            aGraphics.fillOval(x, y, width, width);
        }
        return;
    }

    public Integer getBoardWidth(){
        return boardWidth;
    }
}