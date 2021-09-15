
/**********************
 * MyConnect4 Game
/**********************/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MyConnect4
 */
public class MyConnect4 {

    public static void main(String[] args) {

        MyConnect4 myConnect4 = new MyConnect4();
        myConnect4.play();
    }

    public void play() {

        System.out.println("Welcome to Connect 4");
        System.out.println("There are 2 players red and yellow");
        System.out.println("Player 1 is Red, Player 2 is Yellow");
        System.out.println("To play the game type in the number of the column you want to drop you counter in");
        System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
        System.out.println("");

        // Creating Board object
        Board board = new Board(6, 7, 4);
        int rows = board.getRows();
        int columns = board.getColumns();

        // List created to hold players
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(board, 'r', "Human Player"));
        players.add(new ComputerPlayer(board, 'y', "Computer Player"));

        board.initializeBoard();
        board.displayBoard();

        int moveCount = 0;

        // Player currentPlayer = getRandomPlayer(players);
        int currentPlayer = 0;
        while (true) {

            Player player = players.get(currentPlayer);
            String playerName = player.getPlayerName();
            System.out.print("\n" + playerName + " Enter column : ");

            String move = player.getMove();
            if (Validations.validateUserInput(move, columns)) {
                int playerMove = Integer.parseInt(move) - 1;
                int row = board.getTargetRow(playerMove);

                // Cheking if column is full
                if (row == -1) {
                    System.out.println("Column already filled. Choose another Column");
                    continue;
                }
                board.setCell(player, playerMove);
                board.displayBoard();
                moveCount++;

                // Checking if player has won
                if (verifyWin(board, playerMove, player, row)) {

                    System.out.println(player.getPlayerName() + " Won !");
                    break;
                }

                // Game draw condition
                if (moveCount >= rows * columns) {
                    System.out.println("Game is a Draw");
                    break;
                }

                // Switch Player
                currentPlayer = (currentPlayer + 1) % players.size();
            } else {
                System.out.println("Enter a Valid Input.");
            }
        }

    }

    // Gets the random player
    public Player getRandomPlayer(List<Player> players) {
        Random rand = new Random();
        return players.get(rand.nextInt(players.size()));
    }

    /**
     * Method to check if the player has won or not
     * 
     * @param board         game board
     * @param move          user move
     * @param currentPlayer current player
     * @param row           row where user has put the counter
     * @return true if the player has won
     */
    public boolean verifyWin(Board board, int move, Player currentPlayer, int row) {
        char counter = currentPlayer.getCounter();

        if (board.verifyHorizontalWin(new Cell(counter, row, move))) {
            return true;
        }

        if (board.verifyVerticalWin(new Cell(counter, row, move))) {
            return true;
        }

        if (board.verifyForwardDiagonalWin(new Cell(counter, row, move))) {
            return true;
        }

        if (board.verifyBackwardDiagonalWin(new Cell(counter, row, move))) {
            return true;
        }
        return false;
    }

}