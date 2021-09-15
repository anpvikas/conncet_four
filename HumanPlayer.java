
/**
 * Class defined to create the human player
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public HumanPlayer(Board board, char counter, String playerName) {
        super(board, counter, playerName);
    }

    /**
     * Get the user input
     */
    @Override
    String getMove() {
        String move = "";
        try {
            move = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return move;
    }

}
