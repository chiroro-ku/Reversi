package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game extends Object {
    private String name;
    private List<Player> players;
    private Table table;
    private Integer playerIndex;

    public Game(String name) {
        Integer maxColumn = 8;
        Integer maxRow = 8;
        this.name = name;
        this.players = new ArrayList<>();
        this.table = new Table(maxColumn, maxRow);
        this.playerIndex = 0;
        initialize();
        return;
    }

    public void initialize() {
        this.players.add(new Computer(1, table));
        // this.players.add(new Player(1, "a", table));
        // this.players.add(new Computer(2, table));
        this.players.add(new Player(2, "b", table));
        return;
    }

    public void prepare() {
        Integer aMaxColumn = table.getMaxColumn();
        Integer aMaxRow = table.getMaxRow();
        Integer aColumn = aMaxColumn / 2;
        Integer aRow = aMaxRow / 2;
        table.getGrid(aColumn, aRow).placePiece(players.get(0).getPiece());
        table.getGrid(aColumn - 1, aRow - 1).placePiece(players.get(0).getPiece());
        table.getGrid(aColumn - 1, aRow).placePiece(players.get(1).getPiece());
        table.getGrid(aColumn, aRow - 1).placePiece(players.get(1).getPiece());
        return;
    }

    public void placePiece(Integer aColumn, Integer aRow) {
        Player aPlayer = getCurrentPlayer();
        if (table.isPlacePiece(aPlayer, aColumn, aRow)) {
            aPlayer.placePiece(aColumn, aRow);
            nextPlayerIndex();
        }
        return;
    }

    public void computerPlacePiece() {
        Computer aComputer = (Computer) getCurrentPlayer();
        if (isPlayerPlacePiece(aComputer)) {
            aComputer.placePiece();
        }
        nextPlayerIndex();
        return;
    }

    public Boolean isPlayerPlacePiece(Player aPlayer) {
        List<Grid> grids = table.getGrids();
        for (Grid aGrid : grids) {
            if (table.isPlacePiece(aPlayer, aGrid))
                return true;
        }
        return false;
    }

    public void nextPlayerIndex() {
        playerIndex = (playerIndex + 1) % players.size();
        return;
    }

    public void newTable() {
        Integer maxColumn = table.getMaxColumn() + 1;
        Integer maxRow = table.getMaxRow() + 1;
        table = new Table(maxColumn, maxRow);
        players.forEach(item -> item.setTable(table));
        return;
    }

    public void addPlayer() {
        Integer index = players.size() + 1;
        players.add(new Computer(index, table));
        return;
    }

    public Boolean isEnd() {
        if (table.getEmpty().getNumber() == 0)
            return true;
        Integer current = playerIndex;
        while (!isPlayerPlacePiece(getCurrentPlayer())) {
            nextPlayerIndex();
            if (playerIndex == current)
                return true;
        }
        return false;
    }

    public void winPlayerIndex() {
        List<Integer> aList = new ArrayList<>();
        players.forEach(item -> aList.add(item.getNumber()));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        playerIndex = aList.indexOf(max.get());
        return;
    }

    public Player getCurrentPlayer() {
        return players.get(playerIndex);
    }

    public Player getWinPlayer() {
        winPlayerIndex();
        return getCurrentPlayer();
    }

    public Player getIndexPlayer(Integer index) {
        return players.get(index);
    }

    public String getName() {
        return name;
    }

    public Table getTable() {
        return table;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
