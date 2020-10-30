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
}
