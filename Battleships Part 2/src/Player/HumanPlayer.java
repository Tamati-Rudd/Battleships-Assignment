package Player;

import Map.Coordinate;
import Map.Map;
import Ships.Battleship;
import Ships.Carrier;
import Ships.Destroyer;
import Ships.Fleet;
import Ships.PatrolBoat;
import Ships.Ship;
import Ships.Submarine;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This concrete class of Player describes a Human Player in a game of Battleships
 * @author Tamati Rudd 18045626
 */
public class HumanPlayer extends Player {  
    public HumanPlayer(String name, int mapSize) {
        super(name, mapSize);
    }

    /**
     * This method gathers the needed user data for a ship placement, creates the Ship and assigns it to the Player's Fleet
     * @param front
     * @param Orientation
     * @param shipSize
     * @param shipToPlace an integer representation of which ship needs to be placed
     */
    @Override
    public int placeShip(Coordinate front, int Orientation, int shipSize) {

        HashSet<Coordinate> location = new HashSet<>();   
        location = constructLocation(front, Orientation, shipSize);
        Boolean spaceIsFree = checkBlocked(location);
        
        if (spaceIsFree) {
            
        }
        
        /**
         switch (shipSize) { //Perform the Ship placement, and assign it to the Fleet
            case 0:
                Ship carrier = new Carrier(location);
                this.getPlayerFleet().assignShip(carrier);
                break;
            case 1:
                Ship battleship = new Battleship(location);
                this.getPlayerFleet().assignShip(battleship);
                break;
            case 2:
                Ship destroyer = new Destroyer(location);
                this.getPlayerFleet().assignShip(destroyer);
                break;
            case 3:
                Ship submarine = new Submarine(location);
                this.getPlayerFleet().assignShip(submarine);
                break;
            case 4:
                Ship patrolBoat = new PatrolBoat(location);
                this.getPlayerFleet().assignShip(patrolBoat);
                break;
        }
        this.getPlayerMap().updateFleetLocation(this.getPlayerFleet()); //Update the Fleets location in the Player's Map
         */
        return 0;
    }
   
    /** TO BE DELETED
     * This method collects a single coordinate input from the user (e.g. D11)
     * @param firingShot whether the method is being used to target a shot
     * @return a Coordinate object representing the x and y values entered
     */
    @Override
    public Coordinate enterCoordinate(Boolean firingShot) {
        Boolean enteringCoordinate = true;
        char xCoordEntry = ' ';
        int yCoordEntry = 0;
        Scanner in = new Scanner(System.in);
        
        do {
            try { 
                System.out.print("> ");
                String coord = in.nextLine();
                coord = coord.toUpperCase();
                xCoordEntry = coord.charAt(0); //get the x Coordinate
                coord = coord.substring(1);
                yCoordEntry = Integer.parseInt(coord); //get the Y Coordinate
                    
                if (xCoordEntry < 'A' || xCoordEntry > this.getPlayerMap().getMapLength()) {
                    System.out.println("Invalid Coordinate Input: X value provided is outside of the map boundary. Please try again");
                    System.out.println();
                }
                else if (yCoordEntry < 1 || yCoordEntry > this.getPlayerMap().getMapSize()) {
                    System.out.println("Invalid Coordinate Input: Y value provided is outside of the map boundary. Please try again"); 
                    System.out.println();
                } 
                else if (!firingShot && this.getPlayerMap().checkCoordinateForShip(new Coordinate(xCoordEntry, yCoordEntry),false, null)) {
                    System.out.println("Invalid Coordinate Input: You have already placed a ship on this coordinate!");
                    System.out.println();
                }
                else {
                    enteringCoordinate = false;
                }
            }
            catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.err.println("Invalid Coordinate Input: Unrecognized input. Please try again");
                System.out.println();
            }
        } while(enteringCoordinate);
        
        return new Coordinate(xCoordEntry, yCoordEntry);
    }
    
    /**
     * This implementation of fireShot allows a Human Player to fire a shot at the enemy map
     * @param enemyMap the enemy Map to fire at
     * @param enemyFleet the enemy Fleet to fire at
     */
    @Override
    public Coordinate fireShot(Map enemyMap, Fleet enemyFleet) {
        Boolean checkingCoordinate = true;
        Coordinate target;
        Boolean shotHit;
        
        do {
            System.out.println();
            System.out.println("Enter a target coordinate to fire at (e.g. \"D11\"):");
            System.out.println("Note: the letter MUST be typed before the number for the target to be accepted");
            target = enterCoordinate(true);
            if (!enemyMap.checkCoordinateStatus(target))
                checkingCoordinate = false;
            else
                System.out.println("Invalid Target: You cannot fire at this coordinate as you have already fired there during a previous turn");
        } while (checkingCoordinate);
        
        shotHit = enemyMap.checkCoordinateForShip(target, true, enemyFleet);
        this.shotFeedback(shotHit, target, enemyMap); 
        return target;
    }
}
