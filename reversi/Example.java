package reversi;

import java.util.Arrays;
import java.util.List;

public class Example extends Object {
    public static void main(String[] args) {
        Integer aMaxColumn = 8;
        Integer aMaxRow = 8;
        Board aBoard = new Board(aMaxColumn, aMaxRow);
        Player aPlayerA = new Player(aBoard, "a");
        Player aPlayerB = new Player(aBoard, "b");
        List<Player> players = Arrays.asList(aPlayerA,aPlayerB);
        Judge aJudge = new Judge(aBoard, players);
        Model aModel = new Model(aJudge);

        aModel.open("Reversi");
    }
}
