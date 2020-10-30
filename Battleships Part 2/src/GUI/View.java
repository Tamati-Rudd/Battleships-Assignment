package GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This class creates and defines listeners for the View (GUI)
 * @author Tamati Rudd
 */
public class View extends javax.swing.JFrame implements Observer {

    public JPanel battleshipsPanel = new JPanel();
    public JLabel heading = new JLabel("Battleships: Pre-Game", JLabel.CENTER);
    
    private JLabel p1NameLabel = new JLabel("Player 1 Name:"); 
    private JLabel p2NameLabel = new JLabel("Player 2 Name:"); 
    public JTextField p1Name = new JTextField("One");
    public JTextField p2Name = new JTextField("Two");
    
    private JLabel xLabels1[] = new JLabel[12]; 
    private JLabel yLabels1[] = new JLabel[12]; 
    public JRadioButton[][] gameMap1 = new JRadioButton[12][12];
    private JLabel map1Label = new JLabel("Placing Ships", JLabel.CENTER);
    private JTextField shotFeedback1 = new JTextField("Shot Not Fired");
    private ArrayList<Integer> map1RedButtons = new ArrayList<>(); //For tracking which RadioButtons should be red in gameMap1
    private JLabel xLabels2[] = new JLabel[12]; 
    private JLabel yLabels2[] = new JLabel[12]; 
    public JRadioButton[][] gameMap2 = new JRadioButton[12][12];
    private JLabel map2Label = new JLabel("Placing Ships", JLabel.CENTER);
    private JTextField shotFeedback2 = new JTextField("Shot Not Fired");
    private ArrayList<Integer> map2RedButtons = new ArrayList<>(); //For tracking which RadioButtons should be red in gameMap2
    
    public JButton newGame = new JButton("New Game");
    public JButton resignGame = new JButton ("Resign Game");
    public JButton confirmPlacements1 = new JButton("Confirm Placements");
    public JButton confirmPlacements2 = new JButton("Confirm Placements");
    public JButton rules = new JButton("How to Play");
    
    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(704,480);
        this.setLocationRelativeTo(null);
        battleshipsPanel.setLayout(null);
        
        heading.setFont(new java.awt.Font("Helvetica", 1, 18)); //227 width, 22 height
        heading.setBounds(30,10,625,22);
        battleshipsPanel.add(heading);
        
        p1NameLabel.setBounds(117,42,120,14);
        battleshipsPanel.add(p1NameLabel);
        p2NameLabel.setBounds(470,42,120,14);
        battleshipsPanel.add(p2NameLabel);
        p1Name.setBounds(117,60,120,20);
        battleshipsPanel.add(p1Name);
        p2Name.setBounds(470,60,120,20);
        battleshipsPanel.add(p2Name);

        for (char i = 'A'; i < 'A'+12; i++) { //Player 1 X Labels
            xLabels1[i-65] = new JLabel("  "+Character.toString(i));
            xLabels1[i-65].setBounds(51+(i-65)*21,85,21,21);
            xLabels1[i-65].setVisible(false);
            battleshipsPanel.add(xLabels1[i-65]);
        }
        
        for (char i = 'A'; i < 'A'+12; i++) { //Player 2 X Labels
            xLabels2[i-65] = new JLabel("  "+Character.toString(i));
            xLabels2[i-65].setBounds(403+(i-65)*21,85,21,21);
            xLabels2[i-65].setVisible(false);
            battleshipsPanel.add(xLabels2[i-65]);
        }
        
        for (int i = 0; i < 12; i++) { //Player 1 Y Labels & Radio Buttons (Coordinates)
            yLabels1[i] = new JLabel(Integer.toString(i+1));
            yLabels1[i].setBounds(30,106+i*21,21,21);
            yLabels1[i].setVisible(false);
            battleshipsPanel.add(yLabels1[i]);
            for (int j = 0; j < 12; j++) {
                gameMap1[i][j] = new JRadioButton();
                gameMap1[i][j].setBackground(new java.awt.Color(49, 116, 216));
                gameMap1[i][j].setBounds(51+j*21,106+i*21,21,21);
                gameMap1[i][j].setVisible(false);
                battleshipsPanel.add(gameMap1[i][j]);
            }
        }

        for (int i = 0; i < 12; i++) { //Player 2 Y Labels & Radio Buttons (Coordinates)
            yLabels2[i] = new JLabel(Integer.toString(i+1));
            yLabels2[i].setBounds(382,106+i*21,21,21);
            yLabels2[i].setVisible(false);
            battleshipsPanel.add(yLabels2[i]);
            for (int j = 0; j < 12; j++) {
                gameMap2[i][j] = new JRadioButton();
                gameMap2[i][j].setBackground(new java.awt.Color(66, 161, 54));
                gameMap2[i][j].setBounds(403+j*21,106+i*21,21,21);
                gameMap2[i][j].setVisible(false);
                battleshipsPanel.add(gameMap2[i][j]);
            }
        }
        
        map1Label.setBounds(51,365,252,20);
        map1Label.setVisible(false);
        battleshipsPanel.add(map1Label);
        shotFeedback1.setBounds(117, 390, 120, 20);
        shotFeedback1.setEditable(false);
        shotFeedback1.setVisible(false);
        battleshipsPanel.add(shotFeedback1);
        map2Label.setBounds(403,365,252,20);
        map2Label.setVisible(false);
        battleshipsPanel.add(map2Label);
        shotFeedback2.setBounds(470, 390, 120, 20);
        shotFeedback2.setEditable(false);
        shotFeedback2.setVisible(false);
        battleshipsPanel.add(shotFeedback2);
        
        newGame.setBounds(303, 45, 100, 35);
        battleshipsPanel.add(newGame);
        resignGame.setBounds(298, 45, 110, 35);
        resignGame.setVisible(false);
        battleshipsPanel.add(resignGame);
        confirmPlacements1.setBounds(100,390,160,35);
        confirmPlacements1.setVisible(false);
        battleshipsPanel.add(confirmPlacements1);
        confirmPlacements2.setBounds(453,390,160,35);
        confirmPlacements2.setVisible(false);
        battleshipsPanel.add(confirmPlacements2);
        rules.setBounds(298, 390, 110, 35);
        battleshipsPanel.add(rules);

        this.setTitle("Battleships");
        this.setResizable(false);
        this.add(battleshipsPanel);
        this.setVisible(true);
    }
 
    /**
     * Add action listeners to all components that need it - Buttons and RadioButtons
     * @param listener 
     */
    public void addActionListener(ActionListener listener) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
		gameMap1[i][j].addActionListener(listener);
                gameMap2[i][j].addActionListener(listener);
            }
        }
        newGame.addActionListener(listener);
        resignGame.addActionListener(listener);
        confirmPlacements1.addActionListener(listener);
        confirmPlacements2.addActionListener(listener);
        rules.addActionListener(listener);
    }
    
    /**
     * This method deselects a Radio Button in the GUI
     * @param objectToUnselect - the Radio Button to deselect
     */
    public void deselectCoordinate(Object objectToUnselect) {
        for (int i = 0; i < 12; i++) {          
            for (int j = 0; j < 12; j++) {
                if (objectToUnselect.equals(gameMap1[i][j]))
                    gameMap1[i][j].setSelected(false);
                else if (objectToUnselect.equals(gameMap2[i][j]))
                    gameMap1[i][j].setSelected(false);
            }
        }
    }
    
    /**
     * This method shows Player 1's map and its associated labels
     */
    public void showMap1() {
        for (char i = 'A'; i < 'A'+12; i++)
            xLabels1[i-65].setVisible(true);
        for (int i = 0; i < 12; i++) {          
            yLabels1[i].setVisible(true);
            for (int j = 0; j < 12; j++) 
                gameMap1[i][j].setVisible(true);
        }
        map1Label.setVisible(true);
    }
    
     /**
     * This method shows Player 2's map and its associated labels
     */
    public void showMap2() {
        for (char i = 'A'; i < 'A'+12; i++)
            xLabels2[i-65].setVisible(true);
        for (int i = 0; i < 12; i++) {          
            yLabels2[i].setVisible(true);
            for (int j = 0; j < 12; j++) 
                gameMap2[i][j].setVisible(true);
        }
        
        map2Label.setVisible(true);
    }
     
    /**
     * This method updates the RadioButtons to show a ship placement
     * @param data the program data passed to update
     */
    public void showPlacement(Data data) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k : data.placement) {
                    if (i == k/12 && j == k%12) {
                        if (data.gameState == 1) {
                            gameMap1[i][j].setSelected(true);
                            gameMap1[i][j].setEnabled(false);
                        }
                        else if (data.gameState == 2) {
                            gameMap2[i][j].setSelected(true);
                            gameMap2[i][j].setEnabled(false);
                        }      
                    }
                }
            }
        }
    }
    
    /**
     * This method prevents the clicking of more RadioButtons, thus preventing more ship placement inputs
     * @param data the program data passed to update
     */
    public void disablePlacement(Data data) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (data.gameState == 1) {
                    gameMap1[i][j].setEnabled(false);
                    confirmPlacements1.setVisible(true);
                } 
                else if (data.gameState == 2) {
                    gameMap2[i][j].setEnabled(false);
                    confirmPlacements2.setVisible(true);
                }  
            }
        }
    }
    
    /**
     * This method deselects the selected RadioButton when the ship placement is invalid or cancelled
     * @param data 
     */
    public void invalidPlacement(Data data) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k : data.placement) {
                    if (i == k/12 && j == k%12) {
                        if (data.gameState == 1) 
                            gameMap1[i][j].setSelected(false);
                        else if (data.gameState == 2) 
                            gameMap2[i][j].setSelected(false);
                        if (!data.orientationXedFlag)
                            JOptionPane.showMessageDialog(this, "Error: this ship placement is invalid. Possible reasons:"+System.lineSeparator()+"- Part of the ship would be out of bounds"+System.lineSeparator()+"- Part of the ship would overlap with an existing ship"+System.lineSeparator()+"- No orientation option was chosen"+System.lineSeparator()+"Please choose another ship placement", "Ship Placement Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    /**
     * This method resets Player 1's map following ship placement confirmation to prepare it for game play
     */
    public void resetMap1() {
        for (char i = 'A'; i < 'A'+12; i++)
            xLabels1[i-65].setVisible(false);
        for (int i = 0; i < 12; i++) {          
            yLabels1[i].setVisible(false);
            for (int j = 0; j < 12; j++) {
                gameMap1[i][j].setVisible(false);
                gameMap1[i][j].setEnabled(true);
                gameMap1[i][j].setSelected(false);
            }       
        }
        map1Label.setVisible(false);
        confirmPlacements1.setVisible(false);
    }

    /**
     * This method resets Player 2's map following ship placement confirmation to prepare it for game play
     */
    public void resetMap2() {
        for (int i = 0; i < 12; i++) {          
            for (int j = 0; j < 12; j++) {
                gameMap2[i][j].setSelected(false);
                gameMap2[i][j].setEnabled(false);
            }       
        }
        confirmPlacements2.setVisible(false);
    }
    
    /**
     * This method sets up the UI for the start of the game
     */
    public void startGame() {
        heading.setText("In Game: Player 1's Turn");
        map1Label.setText("Player 1's Shots Fired");
        map2Label.setText("Player 2's Shots Fired");
        shotFeedback1.setVisible(true);
        shotFeedback2.setVisible(true);
        resignGame.setVisible(true);
        showMap1();
        disableMap2();
        JOptionPane.showMessageDialog(this, "The Game has Begun! May the best admiral win!"+System.lineSeparator()+System.lineSeparator()+"- Player 1 will select a target to fire at from the blue map during turns"+System.lineSeparator()+"- Player 2 will select a target to fire at from the green map during turns"+System.lineSeparator()+"- Your map will be coloured as above during your turn"+System.lineSeparator()+"- Player 1 always takes the first turn"+System.lineSeparator()+"- The first player to sink all 5 enemy ships wins!"+System.lineSeparator()+System.lineSeparator()+"Note: Once a target coordinate is selected, the target is final!", "Game Started!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This method disables map 1 and enables map 2 RadioButtons that aren't selected (fired at)
     */
    public void disableMap1() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gameMap1[i][j].setEnabled(false);
                if (!(map1RedButtons.contains(i*12+j)))
                    gameMap1[i][j].setBackground(new java.awt.Color(240, 240, 240));
                if (!gameMap2[i][j].isSelected())
                    gameMap2[i][j].setEnabled(true);
                if (!(map2RedButtons.contains(i*12+j)))
                    gameMap2[i][j].setBackground(new java.awt.Color(66, 161, 54)); 
            }
        }
        shotFeedback2.setText("Shot Not Fired");
    }
    
    /**
     * This method disables map 2 and enables map 1 RadioButtons that aren't selected (fired at)
     */
    public void disableMap2() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gameMap2[i][j].setEnabled(false);
                if (!(map2RedButtons.contains(i*12+j)))
                    gameMap2[i][j].setBackground(new java.awt.Color(240, 240, 240));
                if (!gameMap1[i][j].isSelected())
                    gameMap1[i][j].setEnabled(true);
                if (!(map1RedButtons.contains(i*12+j)))
                    gameMap1[i][j].setBackground(new java.awt.Color(49, 116, 216)); 
            }
        }
        shotFeedback1.setText("Shot Not Fired");
    }
    
    /**
     * This method disables all the RadioButtons on both maps. Used when the game ends
     */
    public void disableBothMaps() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                gameMap1[i][j].setEnabled(false);
                gameMap2[i][j].setEnabled(false);
                map1Label.setText("Game Over");
                map2Label.setText("Game Over");
            }
        }
    }
    
    /**
     * This method updates a spot on one of the gameMaps based on the program data
     * @param data the program data passed to update
     */
    public void updateHit(Data data) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == data.hit/12 && j == data.hit%12) {
                    if (data.gameState == 3 || data.winner == 1) {
                        gameMap1[i][j].setBackground(new java.awt.Color(220,79,79));
                        gameMap1[i][j].setEnabled(false);
                        map1RedButtons.add(i*12+j);
                        shotFeedback1.setText("Shot Hit");
                        disableMap1();
                        heading.setText("In Game: Player 2's Turn");
                    }
                            
                    else if (data.gameState == 4 || data.winner == 2) {
                        gameMap2[i][j].setBackground(new java.awt.Color(220,79,79));
                        gameMap2[i][j].setEnabled(false);
                        map2RedButtons.add(i*12+j);
                        shotFeedback2.setText("Shot Hit");
                        disableMap2();
                        heading.setText("In Game: Player 1's Turn");
                    }    
                }
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Data data = (Data) arg;

        if (data.rulesFlag)
            JOptionPane.showMessageDialog(this, "In Battleships, each of the two players have 5 ships, placed on a 12x12 map"+System.lineSeparator()+"The ships are placed in the following order:"+System.lineSeparator()+"- Carrier (length = 6 coordinates long)"+System.lineSeparator()+"- Battleship (length = 5 coordinates long)"+System.lineSeparator()+"- Destroyer (length = 4 coordinates)"+System.lineSeparator()+"- Submarine (length = 3 coordinates long)"+System.lineSeparator()+"- Patrol Boat (length = 2 coordinates long)"+System.lineSeparator()+"Ships can only be placed horizontally or vertically on your map, and must be within the map bounds"+System.lineSeparator()+"During placements and the players turn, player one's map is blue, while player two's map is green"+System.lineSeparator()+System.lineSeparator()+"During a game, players will fire shots at the opponent's ships in alternation by firing at the blue map for player one, or the green map for player two"+System.lineSeparator()+"Coordinates that have been fired at are marked with a dot, and cannot be selected again"+System.lineSeparator()+"If the shot hits an enemy ship, the coordinate will also be coloured red for the rest of the game"+System.lineSeparator()+"A ship is sunk when all of its coordintes have been hit by a shot"+System.lineSeparator()+"Sink all 5 of your opponents ships to win!", "How to Play", JOptionPane.INFORMATION_MESSAGE);
        else {
            if (data.newGameFlag) { //A new game was started in the Model
            heading.setText("Ship Placements: Player 1");
            showMap1();
            newGame.setVisible(false);      
            p1Name.setText(data.p1Name);
            p1Name.setEditable(false);
            p2Name.setText(data.p2Name);
            p2Name.setEditable(false);
            JOptionPane.showMessageDialog(this, data.p1Name+" (player 1) will now place their ships"+System.lineSeparator()+"To place a ship, select a desired end coordinate, then choose an orientation from the popup window (or close the window to cancel)"+System.lineSeparator()+"Placements that go out of bounds or overlap with an existing ship are not allowed"+System.lineSeparator()+"Ships are placed in order of Carrier (length = 6), Battleship (length = 5), Destroyer (length = 4), Submarine (length = 3), Patrol Boat (length = 2)"+System.lineSeparator()+System.lineSeparator()+data.p2Name+" (player 2) should be looking away from the screen!", "Player 1 Ship Placements", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (data.placementSuccessful) { //A ship was successfully placed in the Model
            showPlacement(data);
            if (data.shipToPlace == 5) { 
                disablePlacement(data);
            }
        }
        else if (!data.placementSuccessful && (data.gameState == 1 || data.gameState == 2) && !data.newGameFlag && !data.p2PlacingFlag) //A ship was not successfully placed in the Model, but no flags are true
           invalidPlacement(data);
        
        if (data.p2PlacingFlag) { //Player 1's ship placements have been confirmed
            heading.setText("Ship Placements: Player 2");
            resetMap1();
            showMap2();
            JOptionPane.showMessageDialog(this, data.p2Name+" (player 2) will now place their ships"+System.lineSeparator()+"To place a ship, select a desired end coordinate, then choose an orientation from the popup window (or close the window to cancel)"+System.lineSeparator()+"Placements that go out of bounds or overlap with an existing ship are not allowed"+System.lineSeparator()+"Ships are placed in order of Carrier (length = 6), Battleship (length = 5), Destroyer (length = 4), Submarine (length = 3), Patrol Boat (length = 2)"+System.lineSeparator()+System.lineSeparator()+data.p1Name+" (player 1) should be looking away from the screen!", "Player 2 Ship Placements", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (data.gameStartFlag) { //All ship placements are complete and the game is to begin
            resetMap2();
            startGame();
        }
        
        if (data.hitFlag) { //A shot was fired and hit
            updateHit(data);
            if (data.shipSunkFlag)
                JOptionPane.showMessageDialog(this, "You sunk an enemy ship!"+System.lineSeparator()+"There are "+data.shipsRemaining+" enemy ship(s) left", "Ship Sunk", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            if (data.gameState == 3 && !data.gameStartFlag) {
                shotFeedback1.setText("Shot Missed");
                disableMap1();
                heading.setText("In Game: Player 2's Turn");
            }    
            else if (data.gameState == 4 && !data.gameStartFlag) {
                shotFeedback2.setText("Shot Missed");
                disableMap2();
                heading.setText("In Game: Player 1's Turn");
            }     
        }
        
        if (data.gameState == 5) { //Game has ended
            resignGame.setEnabled(false);
            rules.setEnabled(false);
            confirmPlacements1.setEnabled(false);
            confirmPlacements2.setEnabled(false);
            if (data.victoryFlag || data.resignGameFlag) { //A player won
                disableBothMaps();
                if (data.winner == 1 || data.playerWhoResigned == 2) {
                    heading.setText("Game Over: Player 1 Wins");
                    JOptionPane.showMessageDialog(this, data.p1Name+" (player 1) has won the game!", "Player 1 Victory", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (data.winner == 2 || data.playerWhoResigned == 1) {
                    heading.setText("Game Over: Player 2 Wins");
                    JOptionPane.showMessageDialog(this, data.p2Name+" (player 2) has won the game!", "Player 2 Victory", JOptionPane.INFORMATION_MESSAGE);
                }
            }     
        } 
    }      
    }
}
