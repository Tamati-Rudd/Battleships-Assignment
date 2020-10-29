package Player;

import Map.Coordinate;
import Map.Database;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This concrete class of Player describes a Human Player in a game of Battleships
 * Note: Players are designed this way to support implementation of an AI player in a future version of the program
 * @author Tamati Rudd 18045626
 */
public class HumanPlayer extends Player {  
    public HumanPlayer(String name, int playerNumber, Database database) {
        super(name, playerNumber, database);
    }

    /**
     * This method writes the location of a Ship to the Database and updates the Ships location in the Player's Fleet
     * @param location the location of the Ship to write/update
     * @param ship a numeric representation of which Ship type (e.g. Carrier = 0) to write/update
     */
    @Override
    public boolean placeShip(HashSet<Coordinate> location, int ship) {
        Iterator<Coordinate> it = location.iterator();
        while (it.hasNext()) {
            database.updateShipPresent(this.getPlayerNumber(), it.next(), ship);
        } 
        this.getPlayerFleet().getShips().get(ship).setLocation(location);
        return true;
    }
}
