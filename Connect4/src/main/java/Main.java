public class Main {
    public static void main(String[] args) {

//        Board b = new Board();
//        b.printBoard();
//        b.addPiece(0,"R");
//        b.addPiece(0,"G");
//        b.addPiece(1,"R");
//        b.printBoard();

        Connect4Game game = new Connect4Game("R", "Y");
        game.startGame();
    }
}
