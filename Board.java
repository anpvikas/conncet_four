/**
 * This class containes all the methods related to board such as: Board Data,
 * Display Board, Board Cell Details, Win COnditions
 */
public class Board {
    private char[][] board;
    private int rows;
    private int columns;
    private int n;

    public Board(int rows, int columns, int n) {
        this.board = new char[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.n = n;
    }

    /**
     * Initializing Game Board
     */
    public void initializeBoard() {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                this.board[row][column] = Constants.EMPTY_CHARACTER;
            }
        }
    }

    /**
     * Displaying Game Board
     */
    public void displayBoard() {
        // displayBoardHeader();
        System.out.println();
        for (int row = 0; row < this.rows; row++) {
            // System.out.print(row);
            System.out.print(" | ");
            for (int column = 0; column < this.columns; column++) {
                System.out.print(this.board[row][column]);
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.print(" ");
        displayColumnIndex();
    }

    /**
     * Display Column Number for selection
     */
    private void displayColumnIndex() {
        for (int column = 0; column < columns; column++) {
            System.out.print("  " + (column + 1) + " ");
        }
        System.out.println();
    }

    /**
     * Row Index for debugging
     */
    private void displayBoardHeader() {
        System.out.print("  ");
        for (int column = 0; column < columns; column++) {
            System.out.print("  " + column + " ");
        }
    }

    /**
     * Get details of Cell
     * 
     * @param row    Row of the Cell
     * @param column Column of the cell
     * @return Returns Cell Object
     */
    public Cell getCell(int row, int column) {
        return new Cell(this.board[row][column], row, column);
    }

    /**
     * Updates Board for current player move
     * 
     * @param player Current Player
     * @param column Column Entered by Player
     */
    public void setCell(Player player, int column) {
        int row = this.getTargetRow(column);
        this.board[row][column] = player.getCounter();
    }

    /**
     * Gets the details of cell to the right of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns right cell of user selected cell
     */
    public Cell getRightCell(int row, int column) {
        if (column + 1 < this.columns) {
            return new Cell(this.board[row][column + 1], row, column + 1);
        }
        return null;
    }

    /**
     * Gets the details of cell to the left of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns left cell of user selected cell
     */
    public Cell getLeftCell(int row, int column) {
        if (column - 1 >= 0) {
            return new Cell(this.board[row][column - 1], row, column - 1);
        }
        return null;
    }

    /**
     * Gets the details of cell to the top of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns top cell of user selected cell
     */
    public Cell getTopCell(int row, int column) {
        if (row - 1 >= 0) {
            return new Cell(this.board[row - 1][column], row - 1, column);
        }
        return null;
    }

    /**
     * Gets the details of cell to the bottom of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns bottom cell of user selected cell
     */
    public Cell getBottomCell(int row, int column) {
        if (row + 1 < this.rows) {
            return new Cell(this.board[row + 1][column], row + 1, column);
        }
        return null;
    }

    /**
     * Gets the details of cell to the right top of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns right top cell of user selected cell
     */
    public Cell getRightTopCell(int row, int column) {
        Cell right = this.getRightCell(row, column);
        return this.getTopCell(right.getRow(), right.getColumn());
    }

    /**
     * Gets the details of cell to the right bottom of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns right bottom cell of user selected cell
     */
    public Cell getRightBottomCell(int row, int column) {
        Cell right = this.getRightCell(row, column);
        return this.getBottomCell(right.getRow(), right.getColumn());
    }

    /**
     * Gets the details of cell to the left top of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns left top cell of user selected cell
     */
    public Cell getLeftTopCell(int row, int column) {
        Cell left = this.getLeftCell(row, column);
        return this.getTopCell(left.getRow(), left.getColumn());
    }

    /**
     * Gets the details of cell to the left bottom of user selected cell
     * 
     * @param row    Row of user selected cell
     * @param column Column of user selected cell
     * @return Returns left bottom cell of user selected cell
     */
    public Cell getLeftBottomCell(int row, int column) {
        Cell left = this.getLeftCell(row, column);
        return this.getBottomCell(left.getRow(), left.getColumn());
    }

    /**
     * Gets the row where the user can put the counter
     * 
     * @param column User input column
     * @return row where the user can put the counter
     */
    public int getTargetRow(int column) {
        for (int row = rows - 1; row >= 0; row--) {
            Cell cell = getCell(row, column);
            char counter = cell.getCounter();
            if (counter == Constants.EMPTY_CHARACTER) {
                return row;
            }
        }
        return -1;
    }

    /**
     * Verifies the win condition in horizontal direction
     * 
     * @param cell input cell where user has put counter
     * @return true when user wins
     */
    public boolean verifyHorizontalWin(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        int counter = cell.getCounter();
        int countCounter = 1;
        for (int i = column + 1; i < this.columns; i++) {
            Cell nextCell = getCell(row, i);
            char nextCounter = nextCell.getCounter();

            if (counter == nextCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        for (int i = column - 1; i >= 0; i--) {

            Cell previousCell = getCell(row, i);
            char previousCounter = previousCell.getCounter();

            if (counter == previousCounter) {
                countCounter++;
                continue;
            }
            break;
        }
        if (countCounter >= this.n) {
            return true;
        }
        return false;
    }

    /**
     * Verifies the win condition in vertical direction
     * 
     * @param cell input cell where user has put counter
     * @return true when user wins
     */
    public boolean verifyVerticalWin(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        int counter = cell.getCounter();
        int countCounter = 1;
        for (int i = row + 1; i < this.rows; i++) {
            Cell nextCell = getCell(i, column);
            char nextCounter = nextCell.getCounter();

            if (counter == nextCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        for (int i = row - 1; i >= 0; i--) {
            Cell previousCell = getCell(i, column);
            char previousCounter = previousCell.getCounter();

            if (counter == previousCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        if (countCounter >= this.n) {
            return true;
        }
        return false;
    }

    /**
     * Verifies the win condition in forward diagonal direction
     * 
     * @param cell input cell where user has put counter
     * @return true when user wins
     */
    public boolean verifyForwardDiagonalWin(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        int counter = cell.getCounter();
        int countCounter = 1;
        for (int i = row + 1, j = column - 1; i < this.rows && j >= 0; i++, j--) {
            Cell nextCell = getCell(i, j);
            char nextCounter = nextCell.getCounter();

            if (counter == nextCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        for (int i = row - 1, j = column + 1; i >= 0 && j < this.columns; i--, j++) {
            Cell previousCell = getCell(i, j);
            char previousCounter = previousCell.getCounter();

            if (counter == previousCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        if (countCounter >= this.n) {
            return true;
        }
        return false;
    }

    /**
     * Verifies the win condition in backward diagonal direction
     * 
     * @param cell input cell where user has put counter
     * @return true when user wins
     */
    public boolean verifyBackwardDiagonalWin(Cell cell) {
        int row = cell.getRow();
        int column = cell.getColumn();
        int counter = cell.getCounter();
        int countCounter = 1;
        for (int i = row + 1, j = column + 1; i < this.rows && j < columns; i++, j++) {
            Cell nextCell = getCell(i, j);
            char nextCounter = nextCell.getCounter();

            if (counter == nextCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            Cell previousCell = getCell(i, j);
            char previousCounter = previousCell.getCounter();

            if (counter == previousCounter) {
                countCounter++;
                continue;
            }
            break;
        }

        if (countCounter >= this.n) {
            return true;
        }
        return false;
    }

    public char[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getN() {
        return n;
    }

}
