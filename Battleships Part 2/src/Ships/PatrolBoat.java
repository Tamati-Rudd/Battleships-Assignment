package Ships;

/**
 * This concrete class of ship defines a Patrol Boat. A Patrol Boat is a 2 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class PatrolBoat extends Ship {
    public PatrolBoat() {
        this.shipSize = 2;
        this.sunk = false;
    }
}
