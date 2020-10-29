package Ships;

/**
 * This concrete class of ship defines a Battleship. A Battleship is a 5 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Battleship extends Ship {
    public Battleship() {
        this.shipSize = 5;
        this.sunk = false;
    }
}
