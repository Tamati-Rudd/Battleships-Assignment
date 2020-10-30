package Game;

import Map.Coordinate;
import Map.Database;
import Player.HumanPlayer;
import Player.Player;
import java.util.Scanner;

/**
 * This class outlines the actions that occur during a single Game of Battleships
 * @author Tamati Rudd
 */
public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Database database;
    /**
     * Constructor for a PvP Battleships Game
     * @param playerOneName name of the first player
     * @param playerTwoName name of the second player
     * @param database database containing the maps for this game
     */
    public Game(String playerOneName, String playerTwoName, Database database) {
        playerOne = new HumanPlayer(playerOneName, 1, database);
        playerTwo = new HumanPlayer(playerTwoName, 2, database);
        this.database = database;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
