package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ジャッジ：ゲームの進行を行う。
 */
public class Judge extends Object {

    /**
     * 盤面を束縛している。
     */
    private Table table;

    /**
     * プレイヤー全員を束縛している。
     */
    private List<Player> players;

    private Boolean end;

    /**
     * 駒の配置の可不可を束縛している。
     */
    private Boolean placePiece;

    /**
     * 現在のプレイヤーのインデックスを束縛している。
     */
    private Integer index;

    /**
     * コンストラクトである。盤面とプレイヤー全員を設定する。
     */
    public Judge(Table aTable, List<Player> player) {
        table = aTable;
        players = player;
        end = false;
        placePiece = false;
        index = 0;
        this.initialize();
        return;
    }

    /**
     * ジャッジの初期化をしている。
     */
    public void initialize() {
        // this.prepare();
        return;
    }

    /**
     * ゲームの準備をしている。プレイヤー数が変更されても、この初期盤面は変更しない。
     */
    public void prepare() {
        Integer aMaxColumn = table.getMaxColumn();
        Integer aMaxRow = table.getMaxRow();
        Integer aColumn = aMaxColumn / 2;
        Integer aRow = aMaxRow / 2;

        table.getGrid(aColumn, aRow).setPiece(players.get(0).getPiece());
        table.getGrid(aColumn - 1, aRow - 1).setPiece(players.get(0).getPiece());
        table.getGrid(aColumn - 1, aRow).setPiece(players.get(1).getPiece());
        table.getGrid(aColumn, aRow - 1).setPiece(players.get(1).getPiece());
        placePiece = true;
        return;
    }

    public void game() {
        Player aPlayer = this.getCurrentPlayer();
        if (aPlayer.isComputer()) {
            placePiece = false;
            Computer aComputer = (Computer) aPlayer;
            aComputer.placePiece();
            placePiece = true;
            this.tableJuge();
        }
        return;
    }

    /**
     * 列と行から駒の配置の処理を行う。
     * 
     * @param aColumn 列
     * @param aRow    行
     */
    public void placePiece(Integer aColumn, Integer aRow) {
        Player aPlayer = this.getCurrentPlayer();
        if (table.isPlacePiece(aPlayer, aColumn, aRow)) {
            // System.out.println("ok");
            aPlayer.placePiece(aColumn, aRow);
            this.tableJuge();
        }
        return;
    }

    public void tableJuge() {
        if (table.getEmptyCount() == 0) {
            this.gameJuge();
        }
        this.changPlayer();
        this.playerJuge();
        return;
    }

    public void playerJuge() {
        Integer current = index;
        while (!this.isCurrentPlayerPlacePieces()) {
            this.changPlayer();
            if (current == index) {
                this.gameJuge();
                return;
            }
        }
        this.game();
        return;
    }

    public void changPlayer(){
        index = (index + 1) % players.size();
        return;
    }

    public void gameJuge() {
        List<Integer> aList = new ArrayList<>();
        players.forEach(item -> aList.add(item.getCount()));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        index = aList.indexOf(max.get());
        placePiece = false;
        end = true;
        return;
    }

    /**
     * ゲームの終了に応答する。
     */
    public Boolean isEnd() {
        if (end == true)
            return true;
        if (table.getEmptyCount() == 0)
            return true;
        return false;
    }

    /**
     * ジャッジが現在見てるプレイヤーに応答する。
     */
    public Player getCurrentPlayer() {
        return players.get(index);
    }

    public Player getNextPlayer() {
        if (index + 1 == players.size())
            return players.get(0);
        return players.get(index + 1);
    }

    public Boolean isCurrentPlayerPlacePieces() {
        Player aPlayer = this.getCurrentPlayer();
        List<Grid> grids = table.getGrids();
        for (Grid aGrid : grids) {
            if (table.isPlacePiece(aPlayer, aGrid))
                return true;
        }
        return false;
    }

    /**
     * 盤面のコンストラクトに応答する。
     * 
     * @return 盤面
     */
    public Table getTable() {
        return table;
    }

    /**
     * プレイヤー全員のコンストラクトに応答する。
     */
    public List<Player> getPlayers() {
        return players;
    }

    public Integer getPlayersNumber() {
        return players.size();
    }

    /**
     * 駒の配置の可不可に応答する。
     */
    public Boolean getPlacePiece() {
        return placePiece;
    }

    public Boolean setPlacePiece(Boolean aBoolean) {
        return placePiece = aBoolean;
    }

    /**
     * 色からプレイヤーを取得して、その色のプレイヤーを応答する。
     * 
     * @param aColor
     * @return 指定された色のプレイヤー
     */
    public Player getPlayer(Integer aColor) {
        return players.get(aColor - 1);
    }

    public void addPlayer(Player aPlayer) {
        players.add(aPlayer);
        return;
    }

    public void setTable(Table aTable) {
        table = aTable;
        players.forEach(item -> item.setTable(aTable));
        return;
    }
}
