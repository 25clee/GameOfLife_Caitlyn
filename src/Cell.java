/**
 * The Cell class defines and displays Cell objects (each individual cell in the grid) with methods that change a Cell's cellState.
 */
public class Cell {
    private int x;
    private int y;
    private int size;
    private int row;
    private int column;
    private CellState cellState;
    private Rules rules;

    /**
     * The Cell constructor initializes a Cell object with x and y coordinates, a size, location (row and column), state, and rules.
     * @param x the x coordinates of the Cell object
     * @param y the y coordinates of the Cell object
     * @param size the size of the Cell object (which is used in the display method to create rectangles to color the Cells)
     * @param row the row that the Cell object is in (within the 2D array grid)
     * @param column the column that the Cell object is in (within the 2D array grid)
     * @param cellState the state of the Cell object (either ALIVE, DEAD, WILL_DIE, OR WILL_REVIVE)
     * @param rules the rules of the Cell object
     */
    public Cell(int x, int y, int size, int row, int column, CellState cellState, Rules rules) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    /**
     * In the applyRules method, cellState is updated after calling applyRules on rules (and passing the current cellState and liveNeighbors, the number of alive cells surrounding the given cell).
     * @param cells a 2D array of Cell objects (a grid) which gets passed into the countLiveNeighbors method
     */
    public void applyRules(Cell[][] cells) {
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /**
     * The evolve method switches the cellState (from WILL_REVIVE to ALIVE and WILL_DIE to DEAD) in between "generations."
     */
    public void evolve() {
        if (cellState == CellState.WILL_REVIVE) {
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE) {
            cellState = CellState.DEAD;
        }
    }

    /**
     * The display method colors the cells (black if alive and white if dead) with rectangles (with dimensions from the constructor) in the 2D array.
     */
    public void display() {
        if (cellState == CellState.ALIVE) {
            Main.app.fill(0);
        } else {
            Main.app.fill(255);
        }
        Main.app.rect(x, y, size, size);
    }

    /**
     *The handleClick method switches the cellState (from dead to alive or alive to dead). It is called in Main's mouseClicked method, so the cellState is toggled when the user clicks on a cell.
     */
    public void handleClick() {
        if (cellState == CellState.ALIVE) {
            cellState = CellState.DEAD;
        } else {
            cellState = CellState.ALIVE;
        }
    }

    /**
     *The countLiveNeighbors method counts the number of alive cells out of the 8 cells surrounding a given cell, returning the count at the end. The returned int affects the cell's rules.
     * @param cells a 2D array of Cell objects (a grid)
     * @return  the number of alive cells out of the 8 cells surrounding a given cell
     */
    private int countLiveNeighbors(Cell[][] cells) { //you could do nested for loop but you would have to subtract the middle cell if it is alive
        int count = 0;
        if (cells[row - 1][column - 1].cellState == CellState.ALIVE || cells[row - 1][column - 1].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row - 1][column].cellState == CellState.ALIVE || cells[row - 1][column].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row - 1][column + 1].cellState == CellState.ALIVE || cells[row - 1][column + 1].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row][column - 1].cellState == CellState.ALIVE || cells[row][column - 1].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row][column + 1].cellState == CellState.ALIVE || cells[row][column + 1].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row + 1][column - 1].cellState == CellState.ALIVE || cells[row + 1][column - 1].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row + 1][column].cellState == CellState.ALIVE || cells[row + 1][column].cellState == CellState.WILL_DIE) {
            count++;
        }
        if (cells[row + 1][column + 1].cellState == CellState.ALIVE || cells[row + 1][column + 1].cellState == CellState.WILL_DIE) {
            count++;
        }
        return count;
    }
}