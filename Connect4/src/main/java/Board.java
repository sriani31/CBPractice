public class Board {

    private static final int  rows = 6;
    private static final int cols = 7;
    Piece [][] gameBoard = new Piece[rows][cols];

    public static int getCols(){
        return cols;
    }
    public static int getRows(){
        return rows;
    }

    public Board() {
        for(int row  = 0;row<rows ; row++){
            for(int col = 0; col<cols;col++){
                gameBoard[row][col] = null;
            }
        }
    }

    public void printBoard(){
        for(int row  = 0;row<rows ; row++){
            System.out.print("|");
            for(int col = 0; col<cols;col++){
                if(gameBoard[row][col] == null){
                    System.out.print("_");
                }
                else {
                    System.out.print(gameBoard[row][col].getColour());
                }
                System.out.print("|");
            }
            System.out.println();
        }

        System.out.println("-------------------");
    }

    public boolean addPiece(int columnToAdd, String colour){

        if(columnToAdd >= 0 && columnToAdd <cols){
            // we can add
            if(gameBoard[0][columnToAdd] == null){

                boolean addedThePiece = false;
                for(int row = rows -1 ; row >=0 ; row --){

                    if(gameBoard[row][columnToAdd] == null){
                        gameBoard[row][columnToAdd] = new Piece();
                        gameBoard[row][columnToAdd].setColour(colour);
                        addedThePiece = true;
                        break;

                    }
                }
                return addedThePiece;
            }
            else{
                System.err.println("This column is full please choose another");
                return false;
            }

        }
        else {
            System.err.println("Not supported in connect 4");
            return false;
        }


    }

    public boolean checkForWinner(int col , String winningColour){
        boolean someoneWon = false;
        for(int row =0; row< rows; row++){
            if(gameBoard[row][col] != null){

                int winningSteak = 3;// if this reaches 0 the player wins

                //downwards
                for(int winRow = row+1;winRow < rows;winRow++){
                    if(gameBoard[winRow][col].getColour() == winningColour) {
                        winningSteak--;
                        if(winningSteak == 0){
                            someoneWon = true;
                        }
                    }else {
                        winningSteak = 3;
                    }
                }

                // for any other type of check it needs to be 4
                winningSteak = 4;

                //horizontal
                for(int winCol =  col -3;winCol<col+3;winCol++){
                    if(winCol<0)continue;
                    if(winCol >= cols) break;
                    if(gameBoard[row][winCol] != null &&  gameBoard[row][winCol].getColour() == winningColour){
                        winningSteak--;
                        if(winningSteak == 0){
                            someoneWon = true;
                        }
                    } else {
                        winningSteak = 4;
                    }
                }


                if(checkDiagonal(row,col, winningColour,false)) return true;
                if(checkDiagonal(row,col, winningColour,true)) return true;
                break;
            }
        }
        return someoneWon;
    }

    public boolean checkDiagonal(int row, int col, String winningColour, boolean rightDiagonal ){

        int winningStreak = 4;
        int reverser = rightDiagonal ?1:-1;

        for(int winRow = row -3, winCol = col - 3*reverser;winRow<= row+3;winRow++,winCol+= reverser){
            if(!rightDiagonal){
                if(winRow<0  || winCol < 0) continue;
                if(winRow>= rows || winCol >= cols ) break;
            }else {
                if(winRow <0 || winCol >= cols ) continue;
                if(winRow >= rows || winCol <0 )break;
            }
            if(gameBoard[winRow][winCol] != null && gameBoard[winRow][winCol].getColour() == winningColour){
                if(--winningStreak == 0) return true;
            }else winningStreak = 4;

        }
        return false;
    }

}
