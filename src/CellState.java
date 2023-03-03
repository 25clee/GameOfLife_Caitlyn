import java.util.Random;
public enum CellState {
    ALIVE,
    DEAD,
    WILL_DIE,
    WILL_REVIVE
}
//with enum, these are the only states, so if you try to enter in a different state, it will be a compiler error! typesafe