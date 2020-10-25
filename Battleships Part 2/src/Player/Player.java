package Player;

import Map.Coordinate;
import Map.Map;
import Ships.Fleet;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class contains attributes and methods that define a single Player in a game of Battleships
 * @author Tamati Rudd 18045626
 */
public abstract class Player implements Turn {
    protected String playerName;
    protected Map playerMap;
    protected Fleet playerFleet;

    public Player(String name, int mapSize) {
        this.playerName = name;
        this.playerFleet = new Fleet();
        char mapHeight = 'A';
        for (int i = 0; i < mapSize-1; i++) { //Calculate the final column of the map as a character (e.g. for map size 12, this will be 'L')
            mapHeight++;
        }
        this.playerMap = new Map(mapHeight, mapSize, name);
    }
    
    public abstract void placeShip(int shipToPlace);
    public abstract Coordinate enterCoordinate(Boolean firingShot);
    
    /**
     * This method constructs a HashSet of Coordinates that represent a location of a potential ship
     * @param front - the front Coordinate of the potential Ship
     * @param orientation - the user entered orientation of the potential Ship
     * @param length - the size of the potential Ship
     * @return newLocation - a HashSet representing a potential location for a ship
     */
    public HashSet<Coordinate> constructLocation(Coordinate front, String orientation, int length) {
        HashSet<Coordinate> newLocation = new HashSet<>();
        newLocation.add(front);
        for (int i = 1; i < length; i++) {
            switch (orientation.toLowerCase()) {
                case "up":
                    newLocation.add(new Coordinate(front.getxCoord(), front.getyCoord() - i));
                    break;
                case "down":
                    newLocation.add(new Coordinate(front.getxCoord(), front.getyCoord() + i));
                    break;
                case "left":
                    char[] shiftedxLeft = Character.toChars(front.getxCoord() - i);
                    newLocation.add(new Coordinate(shiftedxLeft[0], front.getyCoord()));
                    break;
                case "right":
                    char[] shiftedxRight = Character.toChars(front.getxCoord() + i);
                    newLocation.add(new Coordinate(shiftedxRight[0], front.getyCoord()));
                    break;
            }
        }
        return newLocation;
    }
    
    /**
     * This method checks if the Coordinates between a start and end point are blocked - as in whether a Ship is already present on them
     * @param locationToCheck - The HashSet of Coordinates to check for blockages
     * @return blocked - Whether the Coordinates the ship would be placed on are blocked by another ship or not
     */
    public Boolean checkBlocked(HashSet<Coordinate> locationToCheck) {
        Boolean unblocked = true;
        Iterator<Coordinate> it = locationToCheck.iterator();
        while (it.hasNext()) {
            Boolean blocked = this.getPlayerMap().checkCoordinateForShip(it.next(), false, null);
            if (blocked) {
                unblocked = false;
            }
        }
        return unblocked;
    }
    
    @Override
    public void shotFeedback(Boolean shotHit, Coordinate target, Map enemyMap) {
        System.out.println();
        System.out.println(this.getPlayerName()+"'s Shots Fired");
        enemyMap.writeShotsFiredMap(); 
        enemyMap.readShotsFiredMap();
        System.out.println();
        if (shotHit) 
            System.out.println("Your shot hit an enemy ship!");
        else
            System.out.println("Your shot missed!");  
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Map getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(Map playerMap) {
        this.playerMap = playerMap;
    }

    public Fleet getPlayerFleet() {
        return playerFleet;
    }

    public void setPlayerFleet(Fleet playerFleet) {
        this.playerFleet = playerFleet;
    }
}
