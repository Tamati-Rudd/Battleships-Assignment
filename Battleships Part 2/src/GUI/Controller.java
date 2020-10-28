package GUI;

import Map.Coordinate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

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
        if (source.equals(view.newGame)) { //New Game Button pressed
            view.heading.setText("Ship Placements: Player 1");
            view.showMap1();
            view.newGame.setVisible(false);
            view.p1Name.setEditable(false);
            view.p2Name.setEditable(false);
            model.gameState = 1;

            //TO DO: Put in code to do Ship Placements, then start the game
        }
        else if (source.equals(view.endGame)) { //End Game Button pressed
            view.heading.setText("End Game Pressed");
            //TO DO: Put in code to end the game immediately (forfeit). Consider a popup to confirm
        }
        else { //A map Coordinate (RadioButton) was pressed
            
            System.out.println("TEST");
            //Build Coordinate based on selected RadioButton
            Coordinate selected = null;
            for (int i = 0; i < 12; i++) {
                for (char j = 'A'; j < 'A'+12; j++) {
                    if (source.equals(view.gameMap1[i][j-65]) || source.equals(view.gameMap2[i][j-65])) {
                        selected = new Coordinate(j, i);
                    }
                }
            }
            
            if (model.gameState == 1 || model.gameState == 2) { //Ship placement
                String[] buttonTitles = { "Up", "Down", "Left", "Right" };
                int orientation = JOptionPane.showOptionDialog(null, "Select ship orientation, or close the window to cancel:", "Ship Placement", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonTitles, buttonTitles[0]);
                
                if (orientation == -1) //User Xed the option window
                    view.deselectCoordinate(source);
                else {
                    int successfulPlacement = 0;
                    if (model.gameState == 1) {
                        model.game.getPlayerOne().placeShip(selected, orientation, 5);
                    }
                    else if (model.gameState == 2) {
                        //P2 SHIP PLACE CHECK
                    }
                    
                    if (successfulPlacement == 0) //Placement invalid, deselect RadioButton
                        view.deselectCoordinate(source);
                    else {
                        //SELECT NEEDED RADIO BUTTONS METHOD
                    }  
                } 
            }
            else if (model.gameState == 3 || model.gameState == 4) {
                //FIRING
            }   
            //source = view.gameMap1[i][j] or same thing but its gameMap2
            //TO DO: determine which players map was clicked on (only one at a time should be possible as other should be disabled)
            //TO DO: determine if this is a ship placement or firing selection, then call appropriate methods
        }
    }
    
    
}
