/**
 * Class to store Board Cell details
 */
public class Cell {
    private char counter;
    private int row;
    private int column;

    public Cell(char counter, int row, int column) {
        this.counter = counter;
        this.row = row;
        this.column = column;
    }

    public char getCounter() {
        return counter;
    }

    public void setCounter(char counter) {
        this.counter = counter;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Cell [row=" + row + ", column=" + column + ", counter=" + counter + "]";
    }

}
