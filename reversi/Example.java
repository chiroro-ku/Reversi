package reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example extends Object {
    public static void main(String[] args) {
        Integer aMaxColumn = 8;
        Integer aMaxRow = 8;
        Table aTable = new Table(aMaxColumn, aMaxRow);
        Player aPlayerA = new Player(aTable, "1");
        Player aPlayerB = new Player(aTable, "2");
        List<Player> players = new ArrayList<>(Arrays.asList(aPlayerA,aPlayerB));
        Judge aJudge = new Judge(aTable, players);
        Model aModel = new Model(aJudge,"Reversi",true);

        aModel.open();
        return;
    }
}
