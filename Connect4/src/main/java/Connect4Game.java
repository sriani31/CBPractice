import java.util.Random;
import java.util.Scanner;

public class Connect4Game {

    private Board board;
    private String colour1;
    private String colour2;
    // True if P1's turn false otherwise
    private boolean is1Playing;
    public Connect4Game(String colour1, String colour2){
        board = new Board();
        this.colour1 = colour1;
        this.colour2 = colour2;
        is1Playing = (new Random()).nextBoolean();
    }

    public boolean checkForWinner(int column){


        String winningColour = is1Playing ? colour1 : colour2;

        return board.checkForWinner(column,winningColour);
    }
    public void startGame(){
        board.printBoard();
        boolean running = true;
        while (running){
            String colour;
            if(is1Playing){
                colour = colour1;
                System.out.println("Player1 turn");
            }
            else {
                colour = colour2;
                System.out.println("Player 2 turn");
            }
            System.out.println("Please Select the column to put piece in");
            System.out.println("choose between 1 and " + board.getCols() + ": ");
            Scanner input = new Scanner(System.in);
            int column = input.nextInt() -1;
            boolean success = board.addPiece(column,colour);
            if(success){
                if(checkForWinner(column)){
                    running = false;
                    if(is1Playing){
                        System.out.println("Player 1 won");
                    }else {
                        System.out.println("Player 2 won");
                    }

                }
                    is1Playing = !is1Playing;
            }
            board.printBoard();

        }
    }
}
