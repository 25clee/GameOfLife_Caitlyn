import processing.core.PApplet;

public class Main extends PApplet {
    private static final int CELL_SIZE = 15;
    public static Main app;
    private Cell[][] cells;
    private boolean doEvolve;

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public Main(){
        super();
        app = this;
    }

    public void settings(){
        size(600, 500);
    }

    public void setup(){
        cells = new Cell[height/CELL_SIZE][width/CELL_SIZE]; // create a 2D array
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        frameRate(15);
        for (int row = 0; row<cells.length; row++){
            for (int col = 0; col<cells[row].length; col++){ // create a nested loop to iterate over your cells by row and column
                int x = col*CELL_SIZE;
                int y = row*CELL_SIZE;
                cells[row][col] = new Cell(x, y, CELL_SIZE, row, col, CellState.DEAD, rules); // create an instance of a Cell, and add to the array
            }
        }

    }

    public void draw(){
        if (doEvolve==true){
            applyRules();
            evolve();
        }
        for (int row = 0; row<cells.length; row++){
            for (int col = 0; col<cells[row].length; col++){
                cells[row][col].display();
            }
        }
    }

    public void mouseClicked(){
        int row = mouseY/CELL_SIZE;
        int col = mouseX/CELL_SIZE;
        cells[row][col].handleClick();
    }

    public void applyRules(){
        for (int row = 1; row<cells.length-1; row++){
            for (int col = 1; col<cells[row].length-1; col++){
                cells[row][col].applyRules(cells);
            }
        }
    }

    public void evolve(){
        for (int row = 1; row<cells.length-1; row++){
            for (int col = 1; col<cells[row].length-1; col++){
                cells[row][col].evolve();
            }
        }
    }

    public void keyPressed(){
        doEvolve = !doEvolve;
    }
}