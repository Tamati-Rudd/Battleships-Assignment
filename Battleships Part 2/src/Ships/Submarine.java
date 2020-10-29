package Ships;

/**
 * This concrete class of ship defines a Submarine. A Submarine is a 3 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Submarine extends Ship {
    public Submarine() {
        this.shipSize = 3;
        this.sunk = false;
    }
}
