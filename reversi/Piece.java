package reversi;

import java.awt.Color;

public class Piece extends Object{
    private Integer color;
    private Integer count;
    private Color viewColor;

    public Piece(Integer aColor){
        color = aColor;
        count = 0;
        return;
    }

    public void setViewColor(Color aColor){
        viewColor = aColor;
        return;
    }

    public Color getViewColor(){
        return viewColor;
    }

    public Integer getColor(){
        return color;
    }

    public void increment(){
        count++;
        return;
    }

    public void decrement(){
        count--;
        return;
    }

    public Integer getCount(){
        return count;
    }
}
