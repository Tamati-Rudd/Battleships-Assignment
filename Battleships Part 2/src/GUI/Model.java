package GUI;

import Game.Game;
import Map.Database;
import Map.Coordinate;
import Map.State;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the Model of the Battleships Application
 * @author Tamati Rudd
 */
public class Model extends Observable {
    
    public Database database;
    public Data data;
    public Game game;
    
    public Model() {
        database = new Database();
        database.setupTables();
        game = new Game("One", "Two", database);
        data = new Data("One", "Two");
    }
    
    /**
     * Sets the rules flag to true to display the game rules
     */
    public void showRules() {
        data.rulesFlag = true;
        setChanged();
        notifyObservers(data);
        data.rulesFlag = false;
    }
    
    /**
     * This method does operations needed to allow ship placement to begin
     * @param p1Name Player one name input
     * @param p2Name Player two name input
     */
    public void newGameStarted(String p1Name, String p2Name) {
        p1Name = p1Name.trim();
        p2Name = p2Name.trim();
        if (p1Name.equals(""))
            p1Name = "One";
        if (p2Name.equals(""))
            p2Name = "Two";
        game.getPlayerOne().setPlayerName(p1Name);
        game.getPlayerTwo().setPlayerName(p2Name);
        data.p1Name = p1Name;
        data.p2Name = p2Name;
        data.gameState = 1;
        data.newGameFlag = true;
        setChanged();
        notifyObservers(data);
        data.newGameFlag = false; //Set the newGame flag to false so the relevant update lines aren't ever run a second time
    }
    
    /**
     * This method checks the validity of a desired Ship placement, then places the Ship if valid
     * @param selected the intended front Coordinate of the Ship
     * @param orientation the Ships intended orientation
     */
    public void placeShip(Coordinate selected, int orientation) {
        HashSet<Coordinate> location = new HashSet<>();
        if (orientation == -1) { //user Xed the orientation window
            location.add(selected);
            data.orientationXedFlag = true;
        } 
        else { //user didnt X the orientation window
            Boolean spaceIsFree = false;
            if (data.gameState == 1) {
                location = game.getPlayerOne().constructLocation(selected, orientation, data.shipToPlace);
                spaceIsFree = game.getPlayerOne().checkLegalPlacement(location, orientation);
            }
            else if (data.gameState == 2) {
                location = game.getPlayerTwo().constructLocation(selected, orientation, data.shipToPlace);
                spaceIsFree = game.getPlayerTwo().checkLegalPlacement(location, orientation);
            }

            if (spaceIsFree) {
                if (data.gameState == 1)
                    game.getPlayerOne().placeShip(location, data.shipToPlace);
                else if (data.gameState == 2)
                    game.getPlayerTwo().placeShip(location, data.shipToPlace);
                data.placementSuccessful = true;
                data.shipToPlace++;
            }
            else {
                data.placementSuccessful = false; 
                location.clear();
                location.add(selected);
            } 
        }
        data.placement = convertPlacementData(location);
        setChanged();
        notifyObservers(data);
        data.orientationXedFlag = false;
        data.placementSuccessful = false; //reset flags to false to avoid errornous updates 
    }
    
    /**
     * Converts HashSet<Coordinate> data to numeric data to be used in the update method of View
     * @param locationToConvert the HashSet of Coordinates to convert
     * @return an Integer Array of converted data representing RadioButton numbers to update in View
     */
    public int[] convertPlacementData(HashSet<Coordinate> locationToConvert) {
        int[] converted = new int[locationToConvert.size()];
        Iterator<Coordinate> it = locationToConvert.iterator();
        int index = 0;
        while (it.hasNext()) {
            Coordinate converting = it.next();
            converted[index] = (converting.getxCoord()-65) + ((converting.getyCoord()-1)*12);
            index++;
        }
        return converted;
    }
    
    /**
     * This method changes the necessary data values to allow Player 2's Ship placement to begin
     */
    public void beginP2Placements() {
        data.gameState = 2;
        data.p2PlacingFlag = true;
        data.shipToPlace = 0;
        setChanged();
        notifyObservers(data);
        data.p2PlacingFlag = false; //Set the p2Placing flag to false so the relevant update lines aren't ever run a second time
    }
    
    /**
     * This method changes the necessary data values to allow the game to start
     */
    public void beginGame() {
        data.gameState = 3;
        data.gameStartFlag = true;
        setChanged();
        notifyObservers(data);
        data.gameStartFlag = false; //Set the gameStart flag to false so the relevant update lines aren't ever run a second time
    }
    
    /**
     * This method fires a shot at a Map and registers whether it hit or not
     * @param target the target Coordinate of the shot
     */
    public void registerShotFired(Coordinate target) {
        boolean hit = false;
        boolean shipSunk = false;
        boolean fleetSunk = false;
        if (data.gameState == 3) { //Player One fired 
            hit = database.getShipPresent(2, target);
            if (hit) { //if hit, check to see if the Ship is sunk
                target.setCoordState(State.Status.HIT);
                database.updateShipStatus(1, target);
                game.getPlayerTwo().getPlayerFleet().updateFleetCoordStatus(target);
                data.hit = (target.getxCoord()-65) + ((target.getyCoord()-1)*12);
                data.hitFlag = true;
                try {
                    shipSunk = game.getPlayerTwo().getPlayerFleet().checkIfSunk(target);
                    data.shipsRemaining = game.getPlayerTwo().getPlayerFleet().getCurrentFleetSize();
                } catch (Exception ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else { //Missed
                target.setCoordState(State.Status.MISS);
                database.updateShipStatus(1, target);
            }
            if (shipSunk)
               fleetSunk = game.getPlayerTwo().getPlayerFleet().checkFleetSunk();
        }  
        else if (data.gameState == 4) { //Player Two fired 
            hit = database.getShipPresent(1, target);
            if (hit) {
                target.setCoordState(State.Status.HIT);
                database.updateShipStatus(1, target);
                game.getPlayerOne().getPlayerFleet().updateFleetCoordStatus(target);
                data.hit = (target.getxCoord()-65) + ((target.getyCoord()-1)*12);
                data.hitFlag = true;
                try {
                    shipSunk = game.getPlayerOne().getPlayerFleet().checkIfSunk(target);
                    data.shipsRemaining = game.getPlayerOne().getPlayerFleet().getCurrentFleetSize();
                } catch (Exception ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else { //Missed
                target.setCoordState(State.Status.MISS);
                database.updateShipStatus(1, target);
            }
            if (shipSunk)
               fleetSunk = game.getPlayerOne().getPlayerFleet().checkFleetSunk();
        }
        
        if (shipSunk) //A Ship has been sunk
            data.shipSunkFlag = true;
        if (fleetSunk) //A Fleet has been sunk, so the game is over
           winGame();
        
        setChanged();
        notifyObservers(data);
        
        if (data.gameState == 3) //Switch to Player 2 turn for next register
            data.gameState = 4;
        else if (data.gameState == 4) //Switch to Player 1 turn for next register
            data.gameState = 3;
        data.hitFlag = false;
        data.shipSunkFlag = false;
    }
    
    /**
     * Sets the data values to those relating to the Player who won the game
     */
    public void winGame() {
        if (data.gameState == 3) 
            data.winner = 1;
        else if (data.gameState == 4) 
            data.winner = 2;
        data.victoryFlag = true;
        data.gameState = 5;
    }
    
    /**
     * Sets the data values to those needed to end the game manually
     * @param confirmEnd whether the confirmation button was clicked or not
     */
    public void resignGame(int confirmEnd) {
        if (confirmEnd == 0) {
            if (data.gameState == 3)
                data.playerWhoResigned = 1;
            else if (data.gameState == 4)
                data.playerWhoResigned = 2;
            data.gameState = 5;
            data.resignGameFlag = true;
            setChanged();
            notifyObservers(data);
        }
    }
}
