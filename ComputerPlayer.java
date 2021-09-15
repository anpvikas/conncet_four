
/**
 * Class used to create the computer player
 */
import java.util.Random;

public class ComputerPlayer extends Player {

    Random rand = new Random();

    public ComputerPlayer(Board board, char counter, String playerName) {
        super(board, counter, playerName);
    }

    /**
     * Generate a random number as computer's move
     */
    @Override
    String getMove() {
        int move = rand.nextInt(this.board.getColumns()) + 1;
        System.out.println(move);
        return String.valueOf(move);
    }

}
