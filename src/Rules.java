public abstract class Rules { //abstract class: a class that can't be instantiated - for the purpose of the subclasses to use class's functionality
    public abstract boolean shouldBeBorn(int liveNeighbors); //abstract methods - don't do anything but not semicolon of death - just means that subclass must use the methods and implement independently
    public abstract boolean shouldSurvive(int liveNeighbors);

    public CellState applyRules(CellState cellState, int liveNeighbors) { //an abstract class can have working methods too
        if (cellState == CellState.DEAD && shouldBeBorn(liveNeighbors) == true) {
            return CellState.WILL_REVIVE;
        } else if (cellState == CellState.ALIVE && shouldSurvive(liveNeighbors) == false) {
            return CellState.WILL_DIE;
        } else {
            return cellState;
        }
    }
}