package Ships;

import Map.Coordinate;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class defines a fleet. A fleet is a collection of a single Players ships in a game of Battleships.
 * @author Tamati Rudd 18045626
 */
public class Fleet {
    private LinkedList<Ship> ships;
    private int currentFleetSize;
    private final int MAX_FLEET_SIZE = 5; //Declare a constant fleet size. The ability to change this may be added in future versions
    private int shipsSunk;

    public Fleet() {
        this.ships = new LinkedList<>();
        this.currentFleetSize = 0;
        this.shipsSunk = 0;
        setupFleet();
    }
    
    private void setupFleet() {
        ships.add(new Carrier());
        ships.add(new Battleship());
        ships.add(new Destroyer());
        ships.add(new Submarine());
        ships.add(new PatrolBoat());
        currentFleetSize = 5;
    }
    
    /**
     * This method determines which Ship in the Fleet is located at a given Coordinate, and returns it
     * This method is only used in cases where the Coordinate is already known to be part of the Fleet (e.g.a shot hit a ship at the Coordinate)
     * @param coord the Coordinate to use to locate a Ship
     * @return the Ship which the Coordinate parameter is part of
     */
    public Ship getShipAtCoord(Coordinate coord) {
        Ship identifiedShip = null;
        System.out.println("Checking"+coord.getxCoord()+""+coord.getyCoord());
        for (Ship s : this.getShips()) {
            Iterator<Coordinate> it = s.getLocation().iterator();
            while (it.hasNext()) {
                Coordinate c = it.next();
                System.out.println("Against"+c.getxCoord()+""+c.getyCoord());
                if (c.equals(coord)) {
                    identifiedShip = s;
                }
            }
        }
        return identifiedShip;
    }
    
    /**
     * This method checks a single Coordinate to determine whether a Ship in the Fleet is placed on it or not
     * @param coordToCheck the Coordinate object to check
     * @return whether a Ship in this Fleet is located on the Coordinate object parameter
     */
    public Boolean checkCoordInFleet(Coordinate coordToCheck) { 
        for (Ship s : this.getShips()) {
            Iterator<Coordinate> it = s.getLocation().iterator();
            while (it.hasNext()) {
                Coordinate c = it.next();
                if (c.equals(coordToCheck)) {
                    Boolean partOfFleet = c.checkForShip();
                    if (partOfFleet)
                        return true;
                } 
            }
        }
        return false;
    }
    
    /**
     * This method updates Coordinates that are part of Ships in the Fleet to be in a hit State upon being shot
     * @param coordToUpdate the Coordinate to update
     */
    public void updateFleetCoordStatus(Coordinate coordToUpdate) {
        Ship shipContainingCoord = this.getShipAtCoord(coordToUpdate);
        Iterator<Coordinate> it = shipContainingCoord.getLocation().iterator(); 
        while (it.hasNext()) {
            Coordinate c = it.next();
            if (c.equals(coordToUpdate)) {
                c.setCoordState(coordToUpdate.getCoordState());
            }
        }
    }
    
    /**
     * This method checks whether a Ship located at the Coordinate parameter is now sunk as a result of a recent shot hit, and if so, updates necessary attributes
     * In the event that the last Ship in the Fleet is sunk in this method, the method returns that the Fleet is now fully sunk
     * @param coordToCheck a Coordinate recently hit by a shot
     * @return whether the Fleet is still operational (not fully sunk)
     * @throws Exception the Coordinate was unable to be mapped to a Ship in the Fleet
     */
    public Boolean checkIfSunk(Coordinate coordToCheck) throws Exception {
        if (!checkCoordInFleet(coordToCheck))
            return false; 
        
        Ship shipToCheck = this.getShipAtCoord(coordToCheck);
        if (shipToCheck == null)
           throw new Exception("Error: the coordinate fired at could not be mapped to a ship in the fleet");
       
        Boolean sunk = shipToCheck.damageReport();
        if (sunk) {
            this.setShipsSunk(this.getShipsSunk()+1);
            this.setCurrentFleetSize(this.getCurrentFleetSize()-1);
            return true;
        }
        else
            return false;     
    }
 
    /**
     * This method checks whether the Fleet is sunk or not
     * @return whether the Fleet is sunk
     */
    public Boolean checkFleetSunk() {
        if (this.getShipsSunk() == this.getMaxFleetSize()) 
            return true;
        else 
            return false;
    }
    
     public LinkedList<Ship> getShips() {
        return ships;
    }

    public void setShips(LinkedList<Ship> ships) {
        this.ships = ships;
    }

     public int getCurrentFleetSize() {
        return currentFleetSize;
    }

    public void setCurrentFleetSize(int currentFleetSize) {
        this.currentFleetSize = currentFleetSize;
    }
    
    public int getShipsSunk() {
        return shipsSunk;
    }

    public void setShipsSunk(int shipsSunk) {
        this.shipsSunk = shipsSunk;
    }
    
     public int getMaxFleetSize() {
        return MAX_FLEET_SIZE;
    }
}
