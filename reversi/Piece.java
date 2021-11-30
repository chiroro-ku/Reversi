package reversi;

public class Piece extends Object{
    private Integer number;

    public Piece(Integer aNumber){
        number = aNumber;
        return;
    }

    public Integer getColor(){
        return number;
    }
}
