package reversi;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

public class Piece extends Object {
    private Integer color;
    private Integer number;
    private Color uiColor;

    public Piece(Integer color){
        this.color = color;
        this.number = 0;
        this.uiColor = uiColorRandom();
        return;
    }

    public Color uiColorRandom() {
        if (color == 1)
            return Color.WHITE;
        if (color == 2)
            return Color.BLACK;
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        Color color = new ColorUIResource(r, g, b);
        return color;
    }

    public void increase() {
        this.number++;
        return;
    }

    public void decrease() {
        this.number--;
        return;
    }

    public Integer getColor() {
        return color;
    }

    public Integer getNumber() {
        return number;
    }

    public Color getUIColor(){
        return uiColor;
    }
}
