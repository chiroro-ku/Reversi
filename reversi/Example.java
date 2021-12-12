package reversi;

import java.util.Arrays;
import java.util.List;

public class Example extends Object {
    public static void main(String[] args) {
        Integer aMaxColumn = 8;
        Integer aMaxRow = 8;
        Table aTable = new Table(aMaxColumn, aMaxRow);
        Player aPlayerA = new Player(aTable, "a");
        Player aPlayerB = new Player(aTable, "b");
        Player aPlayerC = new Player(aTable, "c");
        Player aPlayerD = new Player(aTable, "d");
        List<Player> players = Arrays.asList(aPlayerA,aPlayerB,aPlayerC,aPlayerD);
        Judge aJudge = new Judge(aTable, players);
        Model aModel = new Model(aJudge,"Reversi");

        aModel.open();
        return;
    }
}
