package Ships;

import Map.Coordinate;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This concrete class of ship defines a Battleship. A Battleship is a 5 coordinate sized ship in a Battleships game.
 * @author Tamati Rudd 18045626
 */
public class Battleship extends Ship {
    public Battleship(HashSet<Coordinate> location) {
        this.location = location;
        this.shipSize = 5;
        this.sunk = false;
        Iterator<Coordinate> it = this.getLocation().iterator();
        while (it.hasNext()) 
            it.next().shipPlaced();
    }
}
