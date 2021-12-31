package reversi;

import java.util.List;

/**
 * モデル：ゲーム情報を握っている。
 */
public class Model extends Object {

    /**
     * 制御を扱うコントローラーのインスタンスを束縛する。
     */
    public Controller controller;

    /**
     * 表示を司るビューのインスタンスを束縛する。
     */
    public View view;

    /**
     * ゲームのインスタンスを束縛する。
     */
    private Game game;

    /**
     * 表示するテキストデータを束縛している。
     */
    private String text;

    /**
     * テーブル設定の可不可を束縛している。
     */
    private Boolean settingTable;

    /**
     * プレイヤー設定の可不可を束縛している。
     */
    private Boolean settingPlayer;

    /**
     * グリッド設定の可不可を束縛している。
     */
    private Boolean settingGrid;

    /**
     * 駒の配置の可不可を束縛している。
     */
    private Boolean placePiece;

    /**
     * ゲーム終了の是非を束縛している。
     */
    private Boolean gameEnd;

    /**
     * モデルのインスタンスである。ゲームを設定し、MVCモデルを構築する。
     * 
     * @param aGame ゲームのインスタンス
     */
    public Model(Game aGame) {

        // ゲームを設定する。
        this.game = aGame;

        // 初期化する。
        initialize();

        // MVCモデルを構築する。
        this.view = new View(this);
        this.controller = new Controller(this, view);
        return;
    }

    /**
     * モデルを初期化する。
     */
    public void initialize() {

        // テキストを初期化する。
        this.text = "";

        // 設定の可不可を初期化する。
        this.settingTable = true;
        this.settingPlayer = true;
        this.settingGrid = true;
        this.placePiece = true;
        this.gameEnd = false;

        return;
    }

    /**
     * ゲームの設定を変更する。
     */
    public void gameSetting() {
        if (settingTable) // テーブル設定
            text = " - Setting - Table";
        else if (settingPlayer) // プレイヤー設定
            text = " - Setting - Player";
        else if (settingGrid) // グリッドの設定
            text = " - Setting - Grid";
        else if (gameEnd) // ゲームの終了設定
            text = " - Game - win - " + game.getWinPlayer().getName();
        else // ゲーム設定
            text = " - Game - " + game.getCurrentPlayer().getName();

        // テキストの更新
        view.updateText();
        return;
    }

    /**
     * 新しいテーブルを作成する。
     */
    public void newGameTable() {
        game.newTable();
        return;
    }

    /**
     * プレイヤーを追加する。
     */
    public void addPlayer() {
        game.addPlayer();
        return;
    }

    /**
     * グリッドの初期設定を変更する。
     * 
     * @param aColumn クリックされた座標の列
     * @param aRow    クリックされた座標の行
     */
    public void settingGrid(Integer aColumn, Integer aRow) {

        // クリックされたグリッドと駒を取得する。
        Table table = game.getTable();
        Grid aGrid = table.getGrid(aColumn, aRow);
        Piece aPiece = aGrid.getPiece();

        // プレイヤー全員の情報を取得する。
        List<Player> players = game.getPlayers();

        if (aPiece.equals(players.get(players.size() - 1).getPiece())) // 駒から空のグリッドに取得する。
            aGrid.setPiece(table.getEmpty());
        else if (aPiece.equals(table.getEmpty())) // 空のグリッドから駒に変更する。
            aGrid.placePiece(table.getWall().getPiece());
        else if (aPiece.getColor() >= players.get(0).getPiece().getColor()) // 次のプレイヤーに変更する。
            aGrid.placePiece(players.get(aPiece.getColor()).getPiece());
        else if (aPiece.equals(table.getWall().getPiece())) // 壁のグリッドから駒に変更する。
            aGrid.placePiece(players.get(0).getPiece());

        return;
    }

    // テーブルの設定を決定する。
    public void settingTableEnd() {
        settingTable = false;
        gameSetting();
        return;
    }

    /** 
     * プレイヤーの設定を決定する。
     */
    public void settingPlayerEnd() {

        // 設定を変更する。
        settingPlayer = false;
        gameSetting();

        // ゲームの準備をする。
        game.prepare();

        return;
    }

    /**
     * グリッドの設定を決定する。
     */
    public void settingGridEnd() {
        settingGrid = false;
        gameSetting();
        return;
    }

    /**
     * ゲームを開始する。
     */ 
    public void gameStart() {

        // ゲームの準備して、ビューを更新する。
        game.prepare();
        view.updata();

        // コンピュータの初手を実行する。
        computerPlacePiece();

        return;
    }

    /**
     * クリックされた座標に駒を配置する。
     * 
     * @param aColumn 座標の列
     * @param aRow    座標の行
     */
    public void placePiece(Integer aColumn, Integer aRow) {

        // テーブルの情報を取得する
        Table table = game.getTable();
        Integer maxColumn = table.getMaxColumn();
        Integer maxRow = table.getMaxRow();

        if (aColumn >= maxColumn || aRow >= maxRow) { // パス

            // 次のプレイヤーに変更する。
            game.nextPlayerIndex();

        } else {

            // 駒を配置する。
            game.placePiece(aColumn, aRow);

            // ビューを更新する。
            view.updata();

            // ゲームの状況を更新する
            Judge();
        }

        return;
    }

    /**
     * コンピューターの駒を配置する。
     */
    public void computerPlacePiece() {

        // プレイヤーがコンピュータの間、駒の配置を実行する。
        while (game.getCurrentPlayer().isComputer()) {

            // プレイヤーの駒の配置を不可にする。
            placePiece = false;

            // コンピュータに駒を配置させる。
            game.computerPlacePiece();

            // ビューを更新する。
            view.updata();

            if (Judge()) // ゲームの状況を更新する。
                return; // ゲームが終了した時、プログラムを抜ける。
        }

        // プレイヤーの駒の配置を可能にする。
        placePiece = true;

        return;
    }

    /**
     * 新しいゲームを作成する。
     */
    public void restartGame() {
        game = new Game(game.getName());
        gameEnd = false;
        return;
    }

    /**
     * テーブルの状況を更新する。ゲームの終了を判断する。
     * 
     * @return ゲームの終了
     */
    public Boolean Judge() {

        // ゲームの状態を取得する。
        Boolean aBoolean = game.isEnd();

        if (aBoolean) { // ゲームの終了処理

            // 駒の配置を不可にして、ゲームを終了させる
            placePiece = false;
            gameEnd = true;
            gameSetting();

            // テキストを更新する。
            view.updateText();
        }

        return aBoolean;
    }

    /**
     * 表示するテキストデータに応答する。
     * 
     * @return テキスト
     */
    public String getText() {
        return game.getName() + text;
    }

    /**
     * ゲームのインスタンスに応答する。
     * 
     * @return ゲームのインスタンス
     */
    public Game getGame() {
        return game;
    }

    /**
     * ゲームが束縛しているテーブルのインスタンスに応答する。
     * 
     * @return テーブルのインスタンス
     */
    public Table getGameTable() {
        return game.getTable();
    }

    /**
     * ゲームが束縛しているプレイヤーのインスタンスに応答する。
     * 
     * @return テーブル
     */
    public List<Player> getGamePlayers() {
        return game.getPlayers();
    }

    /**
     * テーブルが束縛しているグリッドのリストに応答する。
     * 
     * @return プレイヤーのリスト
     */
    public List<Grid> getGameTableGrids() {
        return getGameTable().getGrids();
    }

    /**
     * テーブル設定の可不可に応答する。
     * 
     * @return 設定変更の可不可
     */
    public Boolean isSettingTable() {
        return settingTable;
    }

    /**
     * プレイヤー設定の可不可に応答する。
     * 
     * @return 設定変更の可不可
     */
    public Boolean isSettingPlayer() {
        return settingPlayer;
    }

    /**
     * グリッド設定の可不可に応答する。
     * 
     * @return 設定変更の可不可
     */
    public Boolean isSettingGrid() {
        return settingGrid;
    }

    /**
     * 駒の配置の可不可に応答する。
     * 
     * @return 配置の可不可
     */
    public Boolean isPlacePiece() {
        return placePiece;
    }

    /**
     * ゲームの状態に応答する。
     * 
     * @return 終了の是非
     */
    public Boolean isGameEnd() {
        return gameEnd;
    }
}
