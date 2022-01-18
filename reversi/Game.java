package reversi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ゲーム：ゲームの進行を行う。
 */
public class Game extends Object {

    /**
     * ゲームの名前を掌握する。
     */
    private String name;

    /**
     * ゲームの参加プレイヤーを掌握する。
     */
    private List<Player> players;

    /**
     * ゲームに利用しているテーブルを掌握する。
     */
    private Table table;

    /**
     * プレイヤーリストのインデックスを掌握する。
     */
    private Integer playerIndex;

    /**
     * ゲームのコンストラクトである。8×8のテーブルとプレイヤーが設定される。
     * 
     * @param name ゲームの名前
     */
    public Game(String name) {
        Integer maxColumn = 8;
        Integer maxRow = 8;

        this.name = name;
        this.players = new ArrayList<>();
        this.table = new Table(maxColumn, maxRow);
        this.playerIndex = 0;

        // ゲームの初期化を行う
        initialize();
        return;
    }

    /**
     * ゲームを初期化する。
     */
    private void initialize() {
        this.players.add(new Computer(1, table));
        // this.players.add(new Player(1, "a", table));
        // this.players.add(new Computer(2, table));
        this.players.add(new Player(2, "b", table));
        return;
    }

    /**
     * ゲームの準備を行う。
     * 
     */
    public void prepare() {

        // テーブルの大きさを取得する
        Integer aMaxColumn = table.getMaxColumn();
        Integer aMaxRow = table.getMaxRow();

        // テーブルの中央を計算する
        Integer aColumn = aMaxColumn / 2;
        Integer aRow = aMaxRow / 2;

        // テーブルの中心に駒を配置する
        table.getGrid(aColumn, aRow).placePiece(players.get(0).getPiece());
        table.getGrid(aColumn - 1, aRow - 1).placePiece(players.get(0).getPiece());
        table.getGrid(aColumn - 1, aRow).placePiece(players.get(1).getPiece());
        table.getGrid(aColumn, aRow - 1).placePiece(players.get(1).getPiece());

        return;
    }

    /**
     * プレイヤーがテーブルに駒を配置する。
     * 
     * @param aColumn 駒を配置する行
     * @param aRow    駒を配置する列
     */
    public void placePiece(Integer aColumn, Integer aRow) {

        Player aPlayer = getCurrentPlayer();

        if (table.isPlacePiece(aPlayer, aColumn, aRow)) { // 選択されたグリットが配置可能か判断する

            // 駒の配置
            aPlayer.placePiece(aColumn, aRow);

            // 次のプレイヤーに配置の権限を渡す
            nextPlayerIndex();
        }
        return;
    }

    /**
     * コンピューターがテーブルに駒を配置する。
     */
    public void computerPlacePiece() {

        // プレイヤーをコンピュータにする
        Computer aComputer = (Computer) getCurrentPlayer();

        if (isPlayerPlacePiece(aComputer)) { // 駒を配置できるグリッドがあるか判断する

            // 駒を配置する
            aComputer.placePiece();

        }

        // 次のプレイヤーに配置の権限を渡す
        nextPlayerIndex();
        return;
    }

    /**
     * プレイヤーに駒の配置が可能か判断する。
     * 
     * @param aPlayer プレイヤー
     * @return        駒の配置の可不可
     */
    private Boolean isPlayerPlacePiece(Player aPlayer) {
        List<Grid> grids = table.getGrids();
        for (Grid aGrid : grids) {
            if (table.isPlacePiece(aPlayer, aGrid)) // 駒を配置できるグリッドがある場合
                return true;
        }
        return false;
    }

    /**
     * プレイヤーリストのインデックスを進める。駒を配置できる権限を次のプレイヤーに渡す。
     */
    public void nextPlayerIndex() {
        playerIndex = (playerIndex + 1) % players.size();
        return;
    }

    /**
     * 行と列が一つ大きいテーブルを新しく作る。
     */
    public void newTable() {
        Integer maxColumn = table.getMaxColumn() + 1;
        Integer maxRow = table.getMaxRow() + 1;
        table = new Table(maxColumn, maxRow);
        players.forEach(item -> item.setTable(table));
        return;
    }

    /**
     * プレイヤーの追加を行う。
     */
    public void addPlayer() {
        Integer index = players.size() + 1;
        players.add(new Computer(index, table));
        return;
    }

    /**
     * ゲーム終了の是非に応答する。
     * 
     * @return ゲーム終了の是非
     */
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

    /**
     * 駒の数が最も多いプレイヤーのインデックスに応答する。
     */
    private void winPlayerIndex() {
        List<Integer> aList = new ArrayList<>();
        players.forEach(item -> aList.add(item.getPiece().getNumber()));
        Optional<Integer> max = aList.stream().max(Integer::compareTo);
        playerIndex = aList.indexOf(max.get());
        return;
    }

    /**
     * 現在のプレイヤーに応答する。
     * 
     * @return 現在のプレイヤー
     */
    public Player getCurrentPlayer() {
        return players.get(playerIndex);
    }

    /**
     * 勝利したプレイヤーに応答する。
     * 
     * @return 勝利したプレイヤー
     */
    public Player getWinPlayer() {
        winPlayerIndex();
        return getCurrentPlayer();
    }

    /**
     * インデックスから、プレイヤーを応答する。
     * 
     * @param index インデックス
     * @return プレイヤー
     */
    public Player getIndexPlayer(Integer index) {
        return players.get(index);
    }

    /**
     * ゲームの名前に応答する。
     * 
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * ゲームで利用しているテーブルに応答する。
     * 
     * @return テーブル
     */
    public Table getTable() {
        return table;
    }

    /**
     * ゲームに参加しているプレイヤーリストに応答する。
     * 
     * @return プレイヤーリスト
     */
    public List<Player> getPlayers() {
        return players;
    }
}
