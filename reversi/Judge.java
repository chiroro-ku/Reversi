package reversi;

import java.util.List;

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
        Player aPlayer = players.get(index);
        if (table.isSet(aPlayer, aColumn, aRow)) {
            aPlayer.set(aColumn, aRow);
            this.addIndex();
        }
        return;
    }

    public Table getBoard() {
        return table;
    }

    public Boolean getSet() {
        return playerSet;
    }

    public void addIndex(){
        index++;
        if (index == players.size())
            index = 0;
    }
}
