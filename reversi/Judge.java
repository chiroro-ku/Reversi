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

        players.get(0).placePiece(aColumn, aRow);
        players.get(0).placePiece(aColumn - 1, aRow - 1);
        players.get(1).placePiece(aColumn - 1, aRow);
        players.get(1).placePiece(aColumn, aRow - 1);
        placePiece = true;
        return;
    }

    /**
     * 列と行から駒の配置の処理を行う。
     * 
     * @param aColumn 列
     * @param aRow    行
     */
    public void placePieceAction(Integer aColumn, Integer aRow) {
        Player aPlayer = this.getCurrentPlayer();
        if (table.isPlacePiece(aPlayer, aColumn, aRow)) {
            aPlayer.placePiece(aColumn, aRow);
            if (isEnd())
                this.changeWinPlayer();
            else
                this.changePlayer();
        }
        return;
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
     * ジャッジが見てるプレイヤーを変更する。
     */
    public void changePlayer() {
        index++;
        if (index == players.size())
            index = 0;
        Integer current = index;
        while (!isPlayerPlacePiece()) {
            index++;
            if (index == players.size())
                index = 0;
            if (current == index) {
                this.changeWinPlayer();
                return;
            }
        }
        return;
    }

    public Boolean isPlayerPlacePiece() {
        Player aPlayer = this.getCurrentPlayer();
        List<Grid> grids = table.getGrids();
        for (Grid aGrid : grids) {
            if (table.isPlacePiece(aPlayer, aGrid))
                return true;
        }
        return false;
    }

    /**
     * ジャッジが勝利したプレイヤーを見る。
     */
    public void changeWinPlayer() {
        List<Integer> aList = new ArrayList<>();
        players.forEach(item -> aList.add(item.getCount()));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        index = aList.indexOf(max.get());
        placePiece = false;
        end = true;
        return;
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
