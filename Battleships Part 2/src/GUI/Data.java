package GUI;

/**
 * This class holds data to be passed from the Model to the View via observers to trigger GUI updates
 * @author Tamati Rudd
 */
public class Data {
    public String p1Name; //Player one name
    public String p2Name; //Player two name
    int gameState = 0; //The state of the game. 0 = pre-game, 1 = player one ship placements, 2 = player 2 ship placements, 3 = player 1 turn, 4 = player 2 turn, 5 = game over
    boolean newGameFlag = false; //Flag used to indicate the New Game button has been pressed (and that it is Player one's turn to place ships)
    boolean placementSuccessful = false; //Whether a ship placement succeeded or not
    int shipToPlace = 0; //The current ship that needs to be placed. 0 = carrier, 1 = battleship, 2 = destroyer, 3 = submarine, 4 = patrol boat
    int[] placement; //Ship placement data
    boolean p2PlacingFlag = false; //Flag used to indicate it is now Player two's turn to place ships
    boolean gameStartFlag = false; //Flag used to indicate the game is ready to start
    int shipsRemaining = 0; //Number of ships remaining
    int hit; //Coordinate hit data
    boolean hitFlag = false; //Flag used to indicate a ship was hit
    boolean shipSunkFlag = false; //Flag used to indicate a ship has been sunk (but the game isn't over)
    int winner = 0; //Winner of the game
    boolean victoryFlag = false; //Flag used to indicate if someone has won the game
    boolean endGameFlag = false; //Flag used to indicate if the endGame button was pressed
    boolean rulesFlag = false; //Flag used to indicate if the rules button was pressed
    
    public Data(String p1Name, String p2Name) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
    }
}
