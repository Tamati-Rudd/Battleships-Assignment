package Game;

import GUI.Model;
import GUI.View;
import GUI.Controller;

/**
 * This is the main class for the Battleships application, where the program begins execution
 * @author Tamati Rudd 18045626
 */
public class BattleshipsApplication {

    /** 
     * Starting point of the Battleships Application
     * @param args the command line arguments
     * Note: ensure no other instances of the Battleship Application are running when starting, or the program will fail
     */
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        model.addObserver(view);
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
}
