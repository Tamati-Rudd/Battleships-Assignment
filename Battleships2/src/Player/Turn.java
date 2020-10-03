package Player;

import Map.Coordinate;
import Map.Map;
import Ships.Fleet;

/**
 * This interface defines methods that outline the actions taken during a single Turn in a game of Battleships
 * @author Tamati Rudd 18045626
 */
public interface Turn {

    /**
     * Implementations of this method will fire a single shot at a target Coordinate
     * @param enemyMap the map of the enemy Player to fire at
     * @param enemyFleet the Fleet of the enemy Player to fire at
     * @return the target Coordinate fired at by the player
     */
    public Coordinate fireShot(Map enemyMap, Fleet enemyFleet);

    /**
     * The implementation of this method will provide user(s) with feedback on their last fired shot
     * @param shotHit whether the last fired shot hit
     * @param target the target Coordinate of the last fired shot
     * @param enemyMap the enemy Players map which was fired at
     */
    public void shotFeedback(Boolean shotHit, Coordinate target, Map enemyMap);
}
