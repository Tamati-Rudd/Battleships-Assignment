package GUI;

import Map.Database;
import Game.Game;
import java.util.Observable;


/**
 * This class represents the Model of the Battleships Application
 * @author Tamati Rudd
 */
public class Model extends Observable {
    
    public Database database;
    public Game game;
    public int mapSize = 12;
    public int gameState = 0; //0 = pre-game, 1 = p1 ship placements, 2 = p2 ship placements, 3 = p1 turn, 4 = p2 turn
    
    
    public Model() {
        database = new Database();
        database.setupTables();
        game = new Game("One", "Two", mapSize);
    }
    
}
