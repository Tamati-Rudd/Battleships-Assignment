package Map;

import Map.State.Status;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages the connection between the program and the database and contains methods to query and update the database
 * @author Tamati Rudd
 */
public class Database {
    
    Connection connection = null;
    String url = "jdbc:derby:BattleshipsDB; create=true";
    String username = "battleships";
    String password = "battleships";
      
    public Database() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method sets up the tables in the database and populates them to their initial state
     * Note: shipPresent column values 0 = carrier, 1 = battleship, 2 = destroyer, 3 = submarine, 4 = patrol boat, null if none present
     */
    public void setupTables() {
        try {
            DatabaseMetaData data = connection.getMetaData();
            ResultSet CheckPlayerOneMap = data.getTables(null, null, "PLAYERONEMAP", null);
            if (CheckPlayerOneMap.next()) { //Check to see if PlayerOneMap exists. If it does, drop it, as it will contain data from an obsolete game
                Statement DropPlayerOneMap = connection.createStatement();
                DropPlayerOneMap.executeUpdate("DROP TABLE PlayerOneMap");
                DropPlayerOneMap.close();
            }
            ResultSet CheckPlayerTwoMap = data.getTables(null, null, "PLAYERTWOMAP", null);
            if (CheckPlayerTwoMap.next()) { //Check to see if PlayerTwoMap exists. If it does, drop it, as it will contain data from an obsolete game
                Statement DropPlayerTwoMap = connection.createStatement();
                DropPlayerTwoMap.executeUpdate("DROP TABLE PlayerTwoMap");
                DropPlayerTwoMap.close();
            }
            
            Statement create = connection.createStatement();
            create.executeUpdate("CREATE TABLE PlayerOneMap (location INT, state VARCHAR(7), hasShip INT, shipPresent INT)");
            create.executeUpdate("CREATE TABLE PlayerTwoMap (location INT, state VARCHAR(7), hasShip INT, shipPresent INT)");
            create.close();
            
            Statement populate = connection.createStatement();
            for (int i = 1; i <= 144; i++) {
                populate.executeUpdate("INSERT INTO PlayerOneMap VALUES ("+i+", 'HIDDEN', 0, null)");
                populate.executeUpdate("INSERT INTO PlayerTwoMap VALUES ("+i+", 'HIDDEN', 0, null)");
            }
            populate.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Update the hasShip and shipPresent columns in a database table to reflect a Ship placement
     * @param player which Player's map is to be updated
     * @param location the location on the map to be updated
     * @param ship what Ship is being placed
     */
    public void updateShipPresent(int player, Coordinate location, int ship) {
        try {
            Statement updatePlacement = connection.createStatement();
            int locationNumber = (location.getxCoord()-65) + ((location.getyCoord()-1)*12);
            
            if (player == 1) 
                updatePlacement.executeUpdate("UPDATE PlayerOneMap SET hasShip=1, shipPresent="+ship+" WHERE location="+locationNumber);
            else if (player == 2)
                updatePlacement.executeUpdate("UPDATE PlayerTwoMap SET hasShip=1, shipPresent="+ship+" WHERE location="+locationNumber);
            updatePlacement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     * Query a hasShip column in a database table to determine if a Ship is placed there
     * @param player which Player's map is to be checked
     * @param location the location on the map to be checked
     */
    public boolean getShipPresent(int player, Coordinate location) {
        Boolean shipPresent = false;
        try {
            Statement checkPresent = connection.createStatement();
            int locationNumber = (location.getxCoord()-65) + ((location.getyCoord()-1)*12);
            ResultSet rs = null;
            if (player == 1) 
                rs = checkPresent.executeQuery("SELECT hasShip FROM PlayerOneMap WHERE location = "+locationNumber);
            else
                rs = checkPresent.executeQuery("SELECT hasShip FROM PlayerTwoMap WHERE location = "+locationNumber);
            if (rs.next()) {
                int shipFound = rs.getInt("hasShip");
                if (shipFound == 1)
                    shipPresent = true;
            }
            rs.close();
            checkPresent.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    return shipPresent;
    }
    
    /**
     * Update the state column in a database table to reflect a shot fired
     * @param player which Player's map is to be updated
     * @param location the location on the map to be updated
     */
    public void updateShipStatus(int player, Coordinate location) {
        try {
            Statement updateStatus = connection.createStatement();
            int locationNumber = (location.getxCoord()-65) + ((location.getyCoord()-1)*12);
            String status = "";
            if (location.getCoordState() == Status.HIT)
                status = "HIT";
            else if (location.getCoordState() == Status.MISS)
                status = "MISS";
            if (player == 1) 
                updateStatus.executeUpdate("UPDATE PlayerOneMap SET state='"+status+"' WHERE location="+locationNumber);
            if (player == 2)
                updateStatus.executeUpdate("UPDATE PlayerOneMap SET state='"+status+"' WHERE location="+locationNumber);
            updateStatus.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getShipStatus(int player, Coordinate location) {
        boolean hit = false;
        try {
            Statement checkStatus = connection.createStatement();
            int locationNumber = (location.getxCoord()-65) + ((location.getyCoord()-1)*12);
            ResultSet rs = null;
            if (player == 1)
                rs = checkStatus.executeQuery("SELECT State from PlayerOneMap WHERE location="+locationNumber);
            else
                rs = checkStatus.executeQuery("SELECT State from PlayerTwoMap WHERE location="+locationNumber);
            
            if(rs.next()) {
                String coordinateHit = rs.getString("State");
                if (coordinateHit.equals("HIT"))
                    hit = true;
            }
            rs.close();
            checkStatus.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hit;
    }
}
