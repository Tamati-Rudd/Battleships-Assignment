package Ships;

import Map.Coordinate;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This concrete class of ship defines a Patrol Boat. A Patrol Boat is a 2 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class PatrolBoat extends Ship {
    public PatrolBoat(HashSet<Coordinate> location) {
        this.location = location;
        this.shipSize = 2;
        this.sunk = false;
        Iterator<Coordinate> it = this.getLocation().iterator();
        while (it.hasNext()) 
            it.next().shipPlaced();
    }
}
