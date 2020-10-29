package GUI;

import Map.Coordinate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * This class connects the View to the Model and controls changes to the View
 * @author Tamati Rudd
 */
public class Controller implements ActionListener {

    public View view;
    public Model model;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source.equals(view.newGame)) //New Game Button pressed
            model.newGameStarted(view.p1Name.getText(), view.p2Name.getText()); 
        
        else if (source.equals(view.endGame))  //End Game Button pressed
            model.endGame(); 
        
        else if (source.equals(view.confirmPlacements1)) //Confirm Ship Placements for Player 1 Button pressed
            model.beginP2Placements();
        
        else if (source.equals(view.confirmPlacements2)) //Confirm Ship Placements for Player 2 Button pressed
            model.beginGame();
        
        else if (source.equals(view.rules)) //Rules Button pressed
            System.out.println("Gimme the rules!");
        
        else { //A map Coordinate (RadioButton) was pressed
            Coordinate selected = null;
            for (int i = 1; i <= 12; i++) { //Convert RadioButton number to Coordinate for Model
                for (char j = 'A'; j < 'A'+12; j++) {
                    if (source.equals(view.gameMap1[i-1][j-65]) || source.equals(view.gameMap2[i-1][j-65])) {
                        selected = new Coordinate(j, i);
                    }
                }
            }
            
            if (model.data.gameState == 1 || model.data.gameState == 2) { //Ship placement
                String[] buttonTitles = { "Up", "Down", "Left", "Right" };
                int orientation = JOptionPane.showOptionDialog(null, "Select ship orientation, or close the window to cancel:", "Ship Placement", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonTitles, buttonTitles[0]);
                model.placeShip(selected, orientation);
            }
            else if (model.data.gameState == 3 || model.data.gameState == 4) //Firing during a Player turn
                model.registerShotFired(selected);
        }
    }
}
