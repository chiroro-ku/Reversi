package reversi;

public class Example extends Object{
    public static void main(String[] args) {
        Game aGame = new Game("Reversi");
        Model aModel = new Model(aGame);
        aModel.gameSetting();
    }
}
