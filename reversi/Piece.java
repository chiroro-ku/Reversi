package reversi;

public class Piece extends Object{
    private Integer color;
    private Integer count;

    public Piece(Integer aColor){
        color = aColor;
        count = 0;
        return;
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
