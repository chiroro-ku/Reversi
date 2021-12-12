package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Judge extends Object {
    private Table table;
    private List<Player> players;
    private Boolean placePiece;
    private Integer index;

    public Judge(Table aTable, List<Player> player) {
        table = aTable;
        players = player;
        placePiece = false;
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
        players.get(0).placePiece(aColumn, aRow);
        players.get(0).placePiece(aColumn - 1, aRow - 1);
        players.get(1).placePiece(aColumn - 1, aRow);
        players.get(1).placePiece(aColumn, aRow - 1);
        placePiece = true;

        return;
    }

    public void placePieceAction(Integer aColumn, Integer aRow) {
        Player aPlayer = this.getPlayer();
        if (table.isPlacePiece(aPlayer, aColumn, aRow)) {
            aPlayer.placePiece(aColumn, aRow);
            if (isEnd())
                this.changeWinPlayer();
            else
                this.changePlayer();
        }
        return;
    }

    public Player getPlayer(Integer aColor){
        return players.get(aColor-1);
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
        System.out.println("ok");
        List<Integer> aList = new ArrayList<>();
        players.forEach(item -> aList.add(item.getCount()));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        index = aList.indexOf(max.get());
        placePiece = false;
        return;
    }

    public Table getTable() {
        return table;
    }

    public Boolean getPlacePiece() {
        return placePiece;
    }

    public Player getPlayer() {
        return players.get(index);
    }

    public Integer getPlayerColor(){
        Player aPlayer = getPlayer();
        return aPlayer.getColor();
    }

    public List<Player> getPlayers(){
        return players;
    }
}
