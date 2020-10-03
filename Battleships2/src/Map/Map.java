package Map;

import Map.State.Status;
import Ships.Fleet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class defines a single map in a game of Battleships. 
 * A map is an area of play. This class manages two forms of maps: a ship placement map and a shots fired (hit/miss) map
 * A single map will always be a square grid.
 * Notes:
 * - The shots fired (hit/miss) map IS written to file. 
 * - A players shots fired map is associated with the enemy players Map object (as their fleet is the one being shot). This is reflected in the file names
 * - The ship placement map IS NOT written to file (so a Player cannot get the other's ship placements from a text file)
 * @author Tamati Rudd 18045626
 */
public class Map implements MapFileUpdates {
   private Coordinate[][] map;
   private String mapFileName;
   private char mapLength;
   private int mapSize;

   public Map(char mapLength, int mapSize, String fileName) {
       this.mapLength = mapLength;
       this.mapSize = mapSize;
       this.mapFileName = fileName+" Shots Received.txt";
       this.map = new Coordinate[mapSize][mapSize]; 
       this.initializeMap();
   }
   
   /**
    * Fill the map array with Coordinates and write the initial map text file
    * Only runs when a new Map instance is created
    */
   private void initializeMap() {
       try {
           PrintWriter newMap =  new PrintWriter(new FileOutputStream(this.getMapFileName()));
           for (int i = -1; i < this.getMapSize(); i++) { 
               if (i != -1) //Print the row number, except on the line with the column numbers
                   if (i < 9)
                       newMap.print((i+1)+"  ");
                   else
                       newMap.print((i+1)+" ");
               for (char j = 'A'; j <= this.getMapLength(); j++) {
                   if (i == -1) //If this is the first line, print the column character
                       if (j == 'A')
                           newMap.print("   "+j+" ");
                       else
                           newMap.print(j+" "); 
                   else { //Else print the inital map and add the Coordinate to the array
                       map[i][j-65] = new Coordinate(j,i+1);
                       newMap.print(". ");
                   }  
               }
               newMap.print(System.lineSeparator());
           }
           newMap.close();
       }
       catch (FileNotFoundException e) {
           System.err.println("Error: map file could not be created: "+e);
       }
   }

    /**
     * This method reads a Player's Hit/Miss Map from file and displays it on the screen
     * Note that this is for the hit/miss version of the display - it DOES NOT show Ship locations!
     */
    @Override
    public void readShotsFiredMap() {
       try {
           String lineToDisplay = "";
           BufferedReader readMap = new BufferedReader(new FileReader(this.getMapFileName()));
           
           while ((lineToDisplay=readMap.readLine()) != null) {
               System.out.println(lineToDisplay);   
           }   
           
           readMap.close();
       } 
       catch (IOException e) {
           System.err.println("Error reading file: "+e);
       }
    }

    /**
     * This method updates a Player's Hit/Miss Map in file
     * Note that this is for hits/misses specifically - it DOES NOT show the location of non-hit Ships
     */
    @Override
    public void writeShotsFiredMap() { 
        try {
            PrintWriter updateMap =  new PrintWriter(new FileOutputStream(this.getMapFileName()));
            for (int i = -1; i < this.getMapSize(); i++) { 
                if (i != -1) //Print the row number, except on the line with the column numbers
                    if (i < 9)
                       updateMap.print((i+1)+"  ");
                    else
                       updateMap.print((i+1)+" ");
                for (char j = 'A'; j <= this.getMapLength(); j++) {
                    if (i == -1) //If this is the first line, print the column character
                        if (j == 'A')
                           updateMap.print("   "+j+" ");
                        else
                           updateMap.print(j+" "); 
                    else { //Else print the inital map and add the Coordinate to the array
                        switch (map[i][j-65].getCoordState()) {
                            case HIDDEN:
                               updateMap.print(". ");
                               break;
                            case MISS:
                               updateMap.print("O ");
                               break;
                            case HIT:
                               updateMap.print("X ");
                               break;
                       }
                   }  
               }
               updateMap.print(System.lineSeparator());
           }
           updateMap.close();
       }
       catch (IOException e) {
           System.err.println("Error: could not write to map file: "+e);
       }
    }
    
    /**
     * This method displays the location (ship placements) of a Player's Fleet
     */
    public void displayFleetLocation() {
        for (int i = -1; i < this.getMapSize(); i++) {
            if (i != -1) //Print the row number, except on the line with the column numbers
                   if (i < 9)
                       System.out.print((i+1)+"  ");
                   else
                       System.out.print((i+1)+" ");
            for (char j = 'A'; j <= this.getMapLength(); j++) {
                if (i == -1) //If this is the first line, print the column character
                    if (j == 'A')
                        System.out.print("   "+j+" ");
                    else 
                        System.out.print(j+" ");
                    
                else {
                    if (map[i][j-65].checkForShip())
                        System.out.print("# ");
                    else
                        System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * This method updates the location of a Fleet in the Map Array
     * @param fleetToUpdate the Fleet object containing data to place into the array
     */
    public void updateFleetLocation(Fleet fleetToUpdate) {
        for (int i = 0; i < this.getMapSize(); i++) {
            for (char j = 'A'; j <= this.getMapLength(); j++) {
                if (fleetToUpdate.checkCoordInFleet(map[i][j-65])) { 
                    map[i][j-65].shipPlaced();
                }
            }
        }      
    }
    
    /**
     * Checks a single Coordinate in the Map array to see if there is a ship located there
     * @param coordToCheck the Coordinate to check for a ship
     * @param shotFired whether a shot is being fired at this Coordinate
     * @param fleetToUpdate the Fleet the Coordinate is located in
     * @return shipPresent - whether a Ship is present on the Coordinate or not
     */
    public Boolean checkCoordinateForShip(Coordinate coordToCheck, Boolean shotFired, Fleet fleetToUpdate) {
        Boolean shipPresent = false;
        for (int i = 0; i < this.getMapSize(); i++) {
            for (char j = 'A'; j <= this.getMapLength(); j++) {
                if (map[i][j-65].getxCoord() == coordToCheck.getxCoord() && map[i][j-65].getyCoord() == coordToCheck.getyCoord()) 
                    if (shotFired) {
                        shipPresent = map[i][j-65].registerShot(); //Shot is being fired at this Coordinate, register whether it is a hit or miss
                        if (map[i][j-65].getCoordState() == Status.HIT)
                            fleetToUpdate.updateFleetCoordStatus(map[i][j-65]); //If the shot hit, update the Fleet
                    }   
                    else
                        shipPresent = map[i][j-65].checkForShip(); //Method is being for a purpose outisde of shot registration, simply see if a ship is present at the Coordinate
            }
        }
        return shipPresent;
    }

    /**
     * This method checks a single Coordinate in the Map to see if it is already hit or missed
     * This is used to disallow the firing of shots at an already shot Coordinate
     * @param coordToCheck - the Coordinate to check
     * @return whether the Coordinate parameter has already been hit or missed in the Map
     */
    public Boolean checkCoordinateStatus(Coordinate coordToCheck) {
        Boolean hitOrMiss = false;
         for (int i = 0; i < this.getMapSize(); i++) {
            for (char j = 'A'; j <= this.getMapLength(); j++) {
                if (map[i][j-65].equals(coordToCheck))
                    if (map[i][j-65].getCoordState() == Status.HIT || map[i][j-65].getCoordState() == Status.MISS)
                        hitOrMiss = true;
            }
         }
        
        return hitOrMiss;
    }
    
    public Coordinate[][] getMap() {
        return map;
    }

    public void setMap(Coordinate[][] map) {
        this.map = map;
    }

    public String getMapFileName() {
        return mapFileName;
    }

    public void setMapFileName(String mapFileName) {
        this.mapFileName = mapFileName;
    }

    public char getMapLength() {
        return mapLength;
    }

    public void setMapLength(char mapLength) {
        this.mapLength = mapLength;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }
}
