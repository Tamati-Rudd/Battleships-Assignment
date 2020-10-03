package Ships;

import Map.Coordinate;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This concrete class of ship defines a Destroyer. A Destroyer is a 4 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Destroyer extends Ship {
    public Destroyer(HashSet<Coordinate> location) {
        this.location = location;
        this.shipSize = 4;
        this.sunk = false;
        Iterator<Coordinate> it = this.getLocation().iterator();
        while (it.hasNext()) 
            it.next().shipPlaced();
    }
}
