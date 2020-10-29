package Ships;

/**
 * This concrete class of ship defines a Destroyer. A Destroyer is a 4 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Destroyer extends Ship {
    public Destroyer() {
        this.shipSize = 4;
        this.sunk = false;
    }
}
