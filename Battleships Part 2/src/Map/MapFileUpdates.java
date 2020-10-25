package Map;

/**
 * This interface defines methods used to read and update (but not initialize) a single Map
 * @author Tamati Rudd 18045626
 */
public interface MapFileUpdates {
    
    /**
     * This method reads a Player's Map from file and displays it on the screen
     * Note that this is for the hit/miss version of the display - it DOES NOT show Ship locations!
     */
    public void readShotsFiredMap(); 
    
    /**
     * This method updates a Player's Map in file
     * Note that this is for hits/misses specifically - it DOES NOT show the location of non-hit Ships
     */
    public void writeShotsFiredMap();
}
