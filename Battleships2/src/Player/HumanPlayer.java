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
     * @param shipToPlace an integer representation of which ship needs to be placed
     */
    @Override
    public void placeShip(int shipToPlace) {
        Scanner in = new Scanner(System.in);
        int shipSize = 0;
        char shipLength = ' ';
        HashSet<Coordinate> location = new HashSet<>();
        
        switch(shipToPlace) {
            case 0:
                shipSize = 6;
                shipLength = 6;
                break;
            case 1:
                shipSize = 5;
                shipLength = 5;
                break;
            case 2:
                shipSize = 4;
                shipLength = 4;
                break;
            case 3:
                shipSize = 3;
                shipLength = 3;
                break;
            case 4:
                shipSize = 2;
                shipLength = 2;
                break;
        }
        
        Boolean gettingShipData = true;
        do {
            Coordinate front = null;
            Boolean gettingFront = true;
            do {
                System.out.println(this.getPlayerName()+"'s Current Fleet Positioning");
                this.getPlayerMap().displayFleetLocation();
                switch (shipSize) {
                    case 6:
                        System.out.println();
                        System.out.println("Placing "+this.getPlayerName()+"'s Carrier ("+shipSize+" coordinates long):");
                        System.out.println();
                        break;
                    case 5:
                        System.out.println();
                        System.out.println("Placing "+this.getPlayerName()+"'s Battleship ("+shipSize+" coordinates long):");
                        System.out.println();
                        break;
                    case 4:
                        System.out.println();
                        System.out.println("Placing "+this.getPlayerName()+"'s Destroyer ("+shipSize+" coordinates long):");
                        System.out.println();
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Placing "+this.getPlayerName()+"'s Submarine ("+shipSize+" coordinates long)");
                        System.out.println();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Placing "+this.getPlayerName()+"'s Patrol Boat ("+shipSize+" coordinates long):");
                        System.out.println();
                        break;
                }
                System.out.println("Input the desired coordinate for the bow (front) of the ship: (e.g. \"D11\")");
                System.out.println("Note: the letter MUST be typed before the number for the coordinate to be accepted");
                front = this.enterCoordinate(false);
                gettingFront = false;
            } while(gettingFront);
            
            
            Boolean gettingOrientation = true;
            String orientation = "";
            do {
                System.out.println();
                System.out.println("Enter desired orientation for the ship \"up\", \"down\", \"left\" or \"right\" or type \"change\" to choose a new front coordinate");
                System.out.print("> ");
                orientation = in.nextLine();
                switch (orientation.toLowerCase()) {
                    case "up": //User selects an orientation of up from the front Coordinate
                        if (front.getyCoord()-shipSize+1 >= 1) {
                            location = constructLocation(front, orientation, shipSize);
                            Boolean spaceIsFree = checkBlocked(location);
                            if (spaceIsFree) {
                                gettingOrientation = false;
                                gettingShipData = false;
                            }
                            else
                                System.out.println("Invalid Orientation: This orientation is blocked by an existing ship. Please enter another orientation");
                        }
                        else
                            System.out.println("Invalid Orientation: This orientation would cause an out of bounds ship placement. Please enter another orientation");
                        break;
                    case "down": //User selects an orientation of down from the front Coordinate
                        if (front.getyCoord()+shipSize-1 <= this.getPlayerMap().getMapSize()) {
                            location = constructLocation(front, orientation, shipSize);
                            Boolean spaceIsFree = checkBlocked(location);
                            if (spaceIsFree) {
                                gettingOrientation = false;
                                gettingShipData = false;
                            }
                            else
                                System.out.println("Invalid Orientation: This orientation is blocked by an existing ship. Please enter another orientation");
                        }
                        else
                            System.out.println("Invalid Orientation: This orientation would cause an out of bounds ship placement. Please enter another orientation");
                        break;
                    case "left": //User selects an orientation of left from the front Coordinate
                        if (front.getxCoord()-shipLength+1 >= 'A') {
                            location = constructLocation(front, orientation, shipSize);
                            Boolean spaceIsFree = checkBlocked(location);
                            if (spaceIsFree) {
                                gettingOrientation = false;
                                gettingShipData = false;
                            }
                            else
                                System.out.println("Invalid Orientation: This orientation is blocked by an existing ship. Please enter another orientation");
                        }
                        else
                            System.out.println("Invalid Orientation: This orientation would cause an out of bounds ship placement. Please enter another orientation");
                        break;
                    case "right": //User selects an orientation of right from the front Coordinate
                        if (front.getxCoord()+shipLength <= this.getPlayerMap().getMapLength()) {
                            location = constructLocation(front, orientation, shipSize);
                            Boolean spaceIsFree = checkBlocked(location);
                            if (spaceIsFree) {
                                gettingOrientation = false;
                                gettingShipData = false;
                            }
                            else
                                System.out.println("Invalid Orientation: This orientation is blocked by an existing ship. Please enter another orientation");
                        }
                        else
                            System.out.println("Invalid Orientation: This orientation would cause an out of bounds ship placement. Please enter another orientation");
                        break;
                    case "change":
                        gettingOrientation = false;
                        break;
                    default:
                        System.out.println("Invalid Orientation: Unrecognized direction, please try again, or type change to re-enter the bow (front) coordinate");
                        break;
                }
            } while (gettingOrientation);
        } while(gettingShipData);
        
        switch (shipToPlace) { //Perform the Ship placement, and assign it to the Fleet
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
    }
   
    /**
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
