package Ships;

/**
 * This concrete class of ship defines a Carrier. A Carrier is a 6 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Carrier extends Ship {
    public Carrier() {
        this.shipSize = 6;
        this.sunk = false;
    }
}
