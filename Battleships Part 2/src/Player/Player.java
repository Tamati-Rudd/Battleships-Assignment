package Player;

import Map.Coordinate;
import Map.Database;
import Ships.Fleet;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class contains attributes and methods that define a single Player in a game of Battleships
 * @author Tamati Rudd 18045626
 */
public abstract class Player {
    protected String playerName;
    protected Fleet playerFleet;
    protected int playerNumber;
    protected Database database;

    public Player(String name, int playerNumber, Database database) {
        this.playerName = name;
        this.playerFleet = new Fleet();
        this.playerNumber = playerNumber;
        this.database = database;
    }
    
    public abstract boolean placeShip(HashSet<Coordinate> location, int ship);
    
    /**
     * This method constructs a HashSet of Coordinates that represent a location of a potential ship in a Fleet
     * @param front - the front Coordinate of the potential Ship
     * @param orientation - the user entered orientation of the potential Ship
     * @param ship - the potential Ship for which the constructed location is for
     * @return newLocation - a HashSet representing a potential location for a ship
     */
    public HashSet<Coordinate> constructLocation(Coordinate front, int orientation, int ship) {
        HashSet<Coordinate> newLocation = new HashSet<>();
        int size = this.getPlayerFleet().getShips().get(ship).getShipSize(); //get the size of the ship to construct
        newLocation.add(front);
        for (int i = 1; i < size; i++) {
            switch (orientation) {
                case 0:
                    newLocation.add(new Coordinate(front.getxCoord(), front.getyCoord() - i));
                    break;
                case 1:
                    newLocation.add(new Coordinate(front.getxCoord(), front.getyCoord() + i));
                    break;
                case 2:
                    char[] shiftedxLeft = Character.toChars(front.getxCoord() - i);
                    newLocation.add(new Coordinate(shiftedxLeft[0], front.getyCoord()));
                    break;
                case 3:
                    char[] shiftedxRight = Character.toChars(front.getxCoord() + i);
                    newLocation.add(new Coordinate(shiftedxRight[0], front.getyCoord()));
                    break;
            }
        }
        return newLocation;
    }

    /**
     * This method checks if the Coordinates between a start and end point are inside the map or blocked (whether a Ship is already present on them)
     * @param locationToCheck - The HashSet of Coordinates to check for blockages
     * @return blocked - Whether the Coordinates the ship would be placed on are blocked by another ship or not
     */
    public Boolean checkLegalPlacement(HashSet<Coordinate> locationToCheck, int orientation) {
        Boolean placementLegal = true;
        Iterator<Coordinate> it = locationToCheck.iterator();
        while (it.hasNext()) {
            Coordinate checking = it.next();
            if (!(checking.getxCoord() >= 'A' && checking.getxCoord() <= 'L' && checking.getyCoord() >= 1 && checking.getyCoord() <= 12)) 
                return false;
            Boolean blocked = database.getShipPresent(this.getPlayerNumber(), checking);
            if (blocked)
                return false;    
        }
        return placementLegal;
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Fleet getPlayerFleet() {
        return playerFleet;
    }

    public void setPlayerFleet(Fleet playerFleet) {
        this.playerFleet = playerFleet;
    }
}
