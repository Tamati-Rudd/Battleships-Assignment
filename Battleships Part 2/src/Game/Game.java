package Game;

import Map.Coordinate;
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
    private Boolean gameStarted;
    private String winnerName;
    static Scanner in = new Scanner(System.in);
    /**
     * Constructor for a PvP Battleships Game
     * @param playerOneName - name of the first player
     * @param playerTwoName - name of the second player
     * @param mapSize - the size of the map to play on
     */
    public Game(String playerOneName, String playerTwoName, int mapSize) {
        playerOne = new HumanPlayer(playerOneName, mapSize);
        playerTwo = new HumanPlayer(playerTwoName, mapSize);
        this.gameStarted = false;
        this.winnerName = "";
    }
    
    
    
    public void placeShips() {
        //Player 1 places their ships
        System.out.println();
        System.out.println("#========================================================================================#");
        System.out.println("|                       Your fleet will be placed in the following order:                |");
        System.out.println("|                       1. Carrier: 6 coordinates long                                   |");
        System.out.println("|                       2. Battleship: 5 coordinates long                                |");
        System.out.println("|                       3. Destroyer: 4 coordinates long                                 |");
        System.out.println("|                       4. Submarine: 3 coordinates long                                 |");
        System.out.println("|                       5. Patrol Boat: 2 coorsinates long                               |");
        System.out.println("#========================================================================================#");
        
        
        System.out.println();
        System.out.println("#========================================================================================#");
        System.out.println("|                            Ship Placements: Player One                                 |");
        System.out.println("#========================================================================================#");
        
        for (int i = 0; i < this.getPlayerOne().getPlayerFleet().getMaxFleetSize(); i++) { 
            this.getPlayerOne().placeShip(i);  
            System.out.println("====================================================================================================================================");
        }
        
        System.out.println();
        System.out.println("#========================================================================================#");
        System.out.println("|                      Viewing Final Ship Placements: Player One                         |");
        System.out.println("#========================================================================================#");
        System.out.println(this.getPlayerOne().getPlayerName()+"'s Fleet:");
        this.getPlayerOne().getPlayerMap().displayFleetLocation();

        System.out.println();
        System.out.println("Type ok to continue to player two's ship placements");
        Boolean okOne = false;
        while (!okOne) { //Allow the Player to view their ship placements before continuing to the next part of the program
            System.out.print("> ");
            String ok = in.nextLine();
            if (ok.toLowerCase().equals("ok")) {
                okOne = true;
                System.out.println();
            }
            else
                System.out.println("Unrecognized Input: Enter ok when ready to continue");          
        }
        
        for (int i = 0; i < 50; i++) //Print a number of lines to seperate Ship Placement text
            System.out.println();
         
        System.out.println();
        System.out.println("#========================================================================================#");
        System.out.println("|                            Ship Placements: Player Two                                 |");
        System.out.println("#========================================================================================#");
        
        for (int i = 0; i < this.getPlayerTwo().getPlayerFleet().getMaxFleetSize(); i++) { 
            this.getPlayerTwo().placeShip(i); 
            System.out.println("====================================================================================================================================");
        }
        
        System.out.println();
        System.out.println("#========================================================================================#");
        System.out.println("|                      Viewing Final Ship Placements: Player Two                         |");
        System.out.println("#========================================================================================#");
        System.out.println(this.getPlayerTwo().getPlayerName()+"'s Fleet:");
        this.getPlayerTwo().getPlayerMap().displayFleetLocation();
        
        System.out.println();
        System.out.println("Type ok to begin the game");
        Boolean okTwo = false;
        while (!okTwo) { //Allow the Player to view their ship placements before continuing to the next part of the program
            System.out.print("> ");
            String ok = in.nextLine();
            if (ok.toLowerCase().equals("ok")) {
                okTwo = true;
                System.out.println();
            }
            else
                System.out.println("Unrecognized Input: Enter ok when ready to continue");          
        }
        
        for (int i = 0; i < 50; i++) //Print a number of lines to seperate Ship Placement text 
            System.out.println();
    }
    
    /** 
     * This method plays through an entire Game of Battleships
     */
    public void play() {  
        Boolean playingGame = true;
        int turnCount = 1;
        Boolean gameForfeit = false;
        String forfeitPlayer = "";
        
        System.out.println();
        System.out.println("#========================================================================================#");
        System.out.println("|                  The Battle has Begun! May the Better Admiral Win!                     |");
        System.out.println("#========================================================================================#");
        
        while (playingGame) {
            System.out.println();
            System.out.println("Round "+turnCount);

            Boolean p1TakingTurn = true;
            Boolean p2TakingTurn = true;

            //Player One's Turn
            while (p1TakingTurn) {
                System.out.println();
                System.out.println("====================================================================================================================================");
                System.out.println(this.getPlayerOne().getPlayerName()+"'s Shots Fired");
                this.getPlayerTwo().getPlayerMap().readShotsFiredMap();
                
                System.out.println();
                System.out.println(this.getPlayerOne().getPlayerName()+"'s Turn");
                Coordinate target = this.getPlayerOne().fireShot(this.getPlayerTwo().getPlayerMap(), this.getPlayerTwo().getPlayerFleet());
                
                try {
                    Boolean shipSunk = this.getPlayerTwo().getPlayerFleet().checkIfSunk(target); 
                    if (shipSunk) {
                        System.out.println("Your shot sunk an enemy ship!"); 
                        System.out.println("The enemy fleet has "+this.getPlayerTwo().getPlayerFleet().getCurrentFleetSize()+" ships remaining.");
                    }
                }
                catch (Exception e) {
                    System.err.println("Error: shot fired hit a ship, but the ship was unable to be located in the fleet");
                }
                System.out.println("Your updated shots fired map is displayed above.");
                
                 
                Boolean enemyFleetSunk = this.getPlayerTwo().getPlayerFleet().checkFleetSunk(); 
                if (enemyFleetSunk) {
                    p1TakingTurn = false;
                    p2TakingTurn = false;
                    this.setWinnerName(this.getPlayerOne().getPlayerName());
                    playingGame = false;
                } 
                
                Boolean endTurn1 = false;
                while (!endTurn1) { //End Player One's Turn, or Forefeit the Game
                    System.out.println();
                    System.out.println("Type \"e\" to end your turn, or \"ff\" to forefit the game (ends the game in victory for the other player)");
                    System.out.print("> ");
                    String end = in.nextLine();
                    
                    if (end.toLowerCase().equals("e")) {
                        endTurn1 = true;
                        p1TakingTurn = false;
                    }   
                    else if (end.toLowerCase().equals("ff")) {
                        p1TakingTurn = false;
                        p2TakingTurn = false;
                        gameForfeit = true;
                        forfeitPlayer = this.getPlayerOne().getPlayerName();
                        this.setWinnerName(this.getPlayerTwo().getPlayerName());
                        playingGame = false;
                        endTurn1 = true;
                    }  
                    else
                        System.out.println("Unrecognised Input: type \"e\" to end your turn, or \"ff\" to forefeit the game");
                }
            }
            
            //Player Two's Turn            
            while (p2TakingTurn) {
                System.out.println();
                System.out.println("====================================================================================================================================");
                System.out.println(this.getPlayerTwo().getPlayerName()+"'s Shots Fired");
                this.getPlayerOne().getPlayerMap().readShotsFiredMap();
                
                System.out.println();
                System.out.println(this.getPlayerTwo().getPlayerName()+"'s Turn");
                Coordinate target = this.getPlayerTwo().fireShot(this.getPlayerOne().getPlayerMap(), this.getPlayerOne().getPlayerFleet());
                
                try {
                    Boolean shipSunk = this.getPlayerOne().getPlayerFleet().checkIfSunk(target); 
                    if (shipSunk) {
                        System.out.println("Your shot sunk an enemy ship!"); 
                        System.out.println("The enemy fleet has "+this.getPlayerOne().getPlayerFleet().getCurrentFleetSize()+" ships remaining.");
                    }  
                }
                catch (Exception e) {
                    System.err.println("Error: shot fired hit a ship, but the ship was unable to be located in the fleet");
                }
                System.out.println("Your updated shots fired map is displayed above.");
                
                Boolean enemyFleetSunk = this.getPlayerOne().getPlayerFleet().checkFleetSunk(); 
                if (enemyFleetSunk) {
                    playingGame = false;  
                    this.setWinnerName(this.getPlayerTwo().getPlayerName());
                }
                
                Boolean endTurn2 = false;
                while (!endTurn2) { //End Player One's Turn, or Forefeit the Game
                    System.out.println();
                    System.out.println("Type \"e\" to end your turn, or \"ff\" to forefit the game (ends the game in victory for the other player)");
                    System.out.print("> ");
                    String end = in.nextLine();
                    
                    if (end.toLowerCase().equals("e")) {
                        endTurn2 = true;
                        p2TakingTurn = false;
                    }   
                    else if (end.toLowerCase().equals("ff")) {
                        p2TakingTurn = false;
                        gameForfeit = true;
                        forfeitPlayer = this.getPlayerTwo().getPlayerName();
                        this.setWinnerName(this.getPlayerOne().getPlayerName());
                        playingGame = false;
                        endTurn2 = true;
                    }  
                    else
                        System.out.println("Unrecognised Input: type \"e\" to end your turn, or \"ff\" to forefeit the game");
                }
                              
                turnCount++;
            }     
        }
        
        if (!gameForfeit)
            winGame();
        else
            forfeitGame(forfeitPlayer);
    }
    
    public void winGame() { 
        System.out.println();
        System.out.println("==========================================================================================");
        System.out.println("The Battle Has Ended!");
        System.out.println(this.getWinnerName()+" Is Victorious, and has Won the Game!");
        System.out.println("==========================================================================================");
    }
    
    public void forfeitGame(String playerWhoForfeit) {
        System.out.println();
        System.out.println("==========================================================================================");
        System.out.println("The Battle Has Ended!");
        System.out.println(playerWhoForfeit+" Has Forfeit the Game!");
        System.out.println("As a Result, "+this.getWinnerName()+" Is Victorious, and has Won the Game!");
        System.out.println("==========================================================================================");
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
