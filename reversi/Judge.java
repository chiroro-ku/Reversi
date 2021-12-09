package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Judge extends Object {
    private Table table;
    private List<Player> players;
    private Boolean playerSet;
    private Integer index;

    public Judge(Table aTable, List<Player> player) {
        table = aTable;
        players = player;
        playerSet = false;
        index = 0;
        this.initialize();
        return;
    }

    public void initialize() {
        Integer aMaxColumn = table.getMaxColumn();
        Integer aMaxRow = table.getMaxRow();
        Integer aColumn = aMaxColumn / 2;
        Integer aRow = aMaxRow / 2;

        table.initialize();
        players.get(0).set(aColumn, aRow);
        players.get(0).set(aColumn - 1, aRow - 1);
        players.get(1).set(aColumn - 1, aRow);
        players.get(1).set(aColumn, aRow - 1);
        playerSet = true;

        return;
    }

    public void setAction(Integer aColumn, Integer aRow) {
        Player aPlayer = this.getPlayer();
        if (table.isSet(aPlayer, aColumn, aRow)) {
            aPlayer.set(aColumn, aRow);
            if (isEnd())
                this.changeWinPlayer();
            else
                this.changePlayer();
        }
        return;
    }

    public Boolean isEnd() {
        if (table.getEmptyCount() == 0)
            return true;
        return false;
    }

    public void changePlayer() {
        index++;
        if (index == players.size())
            index = 0;
        return;
    }

    public void changeWinPlayer() {
        List<Integer> aList = new ArrayList<>();
        players.forEach(item -> aList.add(item.getCount()));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        index = aList.indexOf(max.get());
        playerSet = false;
        return;
    }

    public Table getTable() {
        return table;
    }

    public Boolean getSet() {
        return playerSet;
    }

    public Player getPlayer() {
        return players.get(index);
    }
}
