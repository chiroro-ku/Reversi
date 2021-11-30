package reversi;

public class Judge extends Object {
    private Board board;
    private Player playerA;
    private Player playerB;

    public Judge(Board aBoard, Player aPlayerA, Player aPlayerB) {
        board = aBoard;
        playerA = aPlayerA;
        playerB = aPlayerB;
        this.initialize();
        return;
    }

    public void initialize() {
        Integer aMaxColumn = board.getMaxColumn();
        Integer aMaxRow = board.getMaxRow();
        Integer aColumn = aMaxColumn / 2;
        Integer aRow = aMaxRow / 2;

        board.reset();
        playerA.setPiece(aColumn, aRow);
        playerA.setPiece(aColumn - 1, aRow - 1);
        playerB.setPiece(aColumn - 1, aRow);
        playerB.setPiece(aColumn, aRow - 1);

        return;
    }

    public Board getBoard(){
        return board;
    }
}
