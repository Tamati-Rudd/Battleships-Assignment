package Game;

import GUI.Controller;
import GUI.Model;
import GUI.View;
import java.util.Scanner;

/**
 * This is the main class for the Battleships application, where the program begins execution
 * @author Tamati Rudd 18045626
 */
public class BattleshipsApplication {
    static Scanner in = new Scanner(System.in);
    static final int MAP_SIZE = 12; //Declare a constant map size. The ability to change this may be added in future versions of the program
    
    /** 
     * Starting point of the Battleships Application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        View view = new View();
        //Model model = new Model();
        //Controller controller = new Controller();
        //model.addObserver(view);
        
        
    }
    
    /** OLD INTRO
     * Boolean running = true;
        System.out.println("#========================================================================================#");
        System.out.println("|                  Welcome Admirals, to the Battleships Application!                     |");
        System.out.println("#========================================================================================#");
        
        do {  
            System.out.println();
            System.out.println("#========================================================================================#");
            System.out.println("| Main Menu                                                                              |");
            System.out.println("| Type one of the following commands to continue:                                        |");
            System.out.println("| \"new\" - start a new Battleships game                                                     |");
            System.out.println("| \"quit\" - exit the application                                                            |");
            System.out.println("#========================================================================================#");
            System.out.println();
            System.out.print("> ");
            
            String option = in.nextLine();
            switch (option.toLowerCase()) {
                
                case "new": //New game creation
                    Game currentGame = setupGame();
                    currentGame.placeShips();
                    currentGame.play();
                    break;
                    
                case "quit": //Quit the application
                    System.out.println("Exiting the application. Good day, admiral(s).");
                    in.close();
                    running = false;
                    break;
                    
                default: //Unexpected input handling
                    System.out.println("Error: Unrecognized command");
                    break;
            }
        } while (running); 
     */
    
    /**
     * This method collects the setup data for a new Game of Battleships, then creates and returns the Game object
     * @return newGame - a new Battleships Game instance
     */
    public static Game setupGame() {
        String playerOneName = "";
        
        System.out.println("#========================================================================================#");
        System.out.println("|                           Game Setup: Enter Player Names                               |");
        System.out.println("#========================================================================================#");
        
        do { 
            System.out.println();
            System.out.println("Enter player one name: (Note: player one will take the first turn!)");
            System.out.print("> ");
            playerOneName = in.nextLine();
            playerOneName = playerOneName.trim();
        } while(playerOneName.isEmpty());
        

        String playerTwoName = "";
        do { 
            System.out.println();
            System.out.println("Enter player two name: ");
            System.out.print("> ");
            playerTwoName = in.nextLine();
            playerTwoName = playerTwoName.trim();
        } while(playerTwoName.isEmpty());
        
        Game newGame = new Game(playerOneName, playerTwoName, MAP_SIZE);
        return newGame;
    }
}
