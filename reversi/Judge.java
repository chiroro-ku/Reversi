package reversi;

import java.util.List;

public class Judge extends Object {
    private Board board;
    private List<Player> players;
    private Boolean playerSet;
    private Integer count;

    public Judge(Board aBoard, List<Player> player) {
        board = aBoard;
        players = player;
        playerSet = false;
        count = 0;
        this.initialize();
        return;
    }

    public void initialize() {
        Integer aMaxColumn = board.getMaxColumn();
        Integer aMaxRow = board.getMaxRow();
        Integer aColumn = aMaxColumn / 2;
        Integer aRow = aMaxRow / 2;

        board.reset();
        players.get(0).setPiece(aColumn, aRow);
        players.get(0).setPiece(aColumn - 1, aRow - 1);
        players.get(1).setPiece(aColumn - 1, aRow);
        players.get(1).setPiece(aColumn, aRow - 1);
        playerSet = true;

        return;
    }

    public void changePlayer() {
        count++;
        if (count == players.size())
            count = 0;
        return;
    }

    public Board getBoard() {
        return board;
    }

    public Boolean getSet() {
        return playerSet;
    }

    public Player getPlayer() {
        return players.get(count);
    }
}
