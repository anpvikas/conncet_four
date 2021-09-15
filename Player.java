/**
 * Abstract Class which holds the common data for both human and computer player
 */

public abstract class Player {
    protected char counter;
    protected Board board;
    protected String playerName;

    public Player(Board board, char counter, String playerName) {
        this.board = board;
        this.counter = counter;
        this.playerName = playerName;
    }

    public char getCounter() {
        return counter;
    }

    public void setCounter(char counter) {
        this.counter = counter;
    }

    abstract String getMove();

    public String getPlayerName() {
        return playerName;
    }

}
