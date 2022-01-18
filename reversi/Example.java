package reversi;

/**
 * メイン：メインクラス。
 */
public class Example extends Object{
    /**
     * メインクラス。モデルとゲームをインスタンスさせて、ゲームを開始させる。
     * 
     * @param args 標準入出力
     */
    public static void main(String[] args) {
        Game aGame = new Game("Reversi");
        Model aModel = new Model(aGame);
        aModel.gameSetting();
    }
}
