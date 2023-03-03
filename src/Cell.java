public class Cell {

    private int x;
    private int y;
    private int size;
    private int row;
    private int column;
    private CellState cellState;
    private Rules rules;


    public Cell(int x, int y, int size, int row, int column, CellState cellState, Rules rules){
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    public void evolve(){
        if (cellState == CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        } else if (cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }
    }

    public void display(){
        if(cellState == CellState.ALIVE) {
            Main.app.fill(0);
        } else {
            Main.app.fill(255);
        }
        Main.app.rect(x,y,size, size);
    }

    public void handleClick(){
        if (cellState == CellState.ALIVE){
           cellState = CellState.DEAD;
        } else {
           cellState = CellState.ALIVE;
        }
    }

    private int countLiveNeighbors(Cell[][] cells){ //you could do nested for loop but you would have to subtract the middle cell if it is alive
        int count = 0;
        if (cells[row-1][column-1].cellState == CellState.ALIVE ||cells[row-1][column-1].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row-1][column].cellState == CellState.ALIVE || cells[row-1][column].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row-1][column+1].cellState == CellState.ALIVE || cells[row-1][column+1].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row][column-1].cellState == CellState.ALIVE || cells[row][column-1].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row][column+1].cellState == CellState.ALIVE || cells[row][column+1].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row+1][column-1].cellState == CellState.ALIVE || cells[row+1][column-1].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row+1][column].cellState == CellState.ALIVE || cells[row+1][column].cellState == CellState.WILL_DIE){
            count++;
        }
        if (cells[row+1][column+1].cellState == CellState.ALIVE || cells[row+1][column+1].cellState == CellState.WILL_DIE){
            count++;
        }
        return count;
    }
}
