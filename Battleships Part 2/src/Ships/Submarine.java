package Ships;

import Map.Coordinate;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This concrete class of ship defines a Submarine. A Submarine is a 3 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Submarine extends Ship {
    public Submarine(HashSet<Coordinate> location) {
        this.location = location;
        this.shipSize = 3;
        this.sunk = false;
        Iterator<Coordinate> it = this.getLocation().iterator();
        while (it.hasNext()) 
            it.next().shipPlaced();
    }
}
