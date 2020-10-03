package Map;

/**
 * This interface defines methods and an enumeration to represent and update the state of a Coordinate
 * @author Tamati Rudd 18045626
 */
public interface State {
    /**
     * This enumeration represents the current status of a coordinate as represented to the players in a game of battleships
     * HIDDEN = the state of the coordinate is unknown to the opposing player
     * MISS = the coordinate has been fired at, and the shot missed
     * HIT = the coordinate has been fired at, and the shot hit a ship
     */
    enum Status { 
        HIDDEN,
        MISS,
        HIT
    }
    
    /**
     * The implementation of this method will be used to check whether a Coordinate object has a Ship on it or not
     * @return a Boolean value indicating whether a ship is present on this Coordinate
     */
    public Boolean checkForShip();
    
    /**
     * The implementation of this method will indicate that a Ship has been placed on a Coordinate object, and update the state accordingly
     */
    public void shipPlaced();
    
    /**
     * The implementation of this method will be used to update the state of a Coordinate object when it is fired at during a turn
     */
    public Boolean registerShot();
}
