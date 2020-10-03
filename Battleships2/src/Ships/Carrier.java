package Ships;

import Map.Coordinate;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This concrete class of ship defines a Carrier. A Carrier is a 6 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Carrier extends Ship {
    public Carrier(HashSet<Coordinate> location) {
        this.location = location;
        this.shipSize = 6;
        this.sunk = false;
        Iterator<Coordinate> it = this.getLocation().iterator();
        while (it.hasNext()) 
            it.next().shipPlaced();
    }
}
