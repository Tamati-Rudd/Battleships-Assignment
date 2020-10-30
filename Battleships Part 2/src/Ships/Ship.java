package Ships;

import Map.Coordinate;
import Map.State.Status;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This abstract class defines common attributes and methods for all ship types that appear in Battleships
 * @author Tamati Rudd 18045626
 */
public abstract class Ship {
    protected HashSet<Coordinate> location;
    protected int shipSize;
    protected Boolean sunk;

    /** 
     * This method checks that a ship still has at least one Coordinate that isn't sunk
     * If all coordinates on a ship are found to be hit, then the ship is sunk
     * @return whether the ship is sunk or not
     */
    public Boolean damageReport() {
        Iterator<Coordinate> it = location.iterator();
        int coordsHit = 0;
         
        while (it.hasNext()) { //Iterate over the location HashSet and record how many Coordinates have been hit
            Coordinate c = it.next();
            if (c.getCoordState() == Status.HIT)
                coordsHit++;    
        }
         
        if (coordsHit == this.getShipSize()) { //if all Coordinates have been hit, the ship is sunk
            setSunk(true);
            return true;
        }
        
        return false;
    } 
    
    public HashSet<Coordinate> getLocation() {
        return location;
    }

    /**
     * Set the location of a ship and update its Coordinates to indicate the presence of a Ship
     * @param location - the location of the Ship
     */
    public void setLocation(HashSet<Coordinate> location) {
        this.location = location;
        Iterator<Coordinate> it = this.location.iterator();
        while (it.hasNext()) 
            it.next().shipPlaced();
    }

    public int getShipSize() {
        return shipSize;
    }

    public void setSunk(Boolean sunk) {
        this.sunk = sunk;
    }
}
