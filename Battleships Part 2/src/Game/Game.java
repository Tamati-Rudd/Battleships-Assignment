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
    private int p1ShipsToPlace;
    private int p2ShipsToPlace;
    private Boolean gameStarted;
    private String winnerName;
    static Scanner in = new Scanner(System.in);
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
        this.gameStarted = false;
        this.winnerName = "";
    }

    public void placeShips() { //RE USE THIS METHOD BUT REMOVE ALL THE PRINTS
        //Player 1 places their ships
        
        //Player 2 places their ships
    }
    
    /** 
     * This method plays through an entire Game of Battleships
     */
    public void play() {  
        Boolean playingGame = true;
        int turnCount = 1;
        Boolean gameForfeit = false;
        String forfeitPlayer = "";
    }
    
    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Boolean getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(Boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}
