package reversi;

public class Example extends Object {
    public static void main(String[] args) {
        Integer aMaxColumn = 8;
        Integer aMaxRow = 8;
        Board aBoard = new Board(aMaxColumn, aMaxRow);
        Player aPlayerA = new Player(aBoard,1, "a");
        Player aPlayerB = new Player(aBoard,-1, "b");
        Judge aJudge = new Judge(aBoard, aPlayerA, aPlayerB);
        Model aModel = new Model(aJudge);

        aModel.open("Reversi");
    }
}
