package GUI;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;



/**
 * View portion of the GUI
 * @author Tamati Rudd
 */
public class View extends javax.swing.JFrame implements Observer {

    private JPanel battleshipsPanel = new JPanel();
    private JLabel heading = new JLabel("Battleships: Pre-Game");
    
    private JLabel p1NameLabel = new JLabel("Player One Name:"); 
    private JLabel p2NameLabel = new JLabel("Player Two Name:"); 
    private JTextField p1Name = new JTextField();
    private JTextField p2Name = new JTextField();
    
    private JLabel xLabels1[] = new JLabel[12]; 
    private JLabel yLabels1[] = new JLabel[12]; 
    private JRadioButton[][] gameMap1 = new JRadioButton[12][12];
    private JLabel map1Label = new JLabel("Placing Ships");
    private JLabel xLabels2[] = new JLabel[12]; 
    private JLabel yLabels2[] = new JLabel[12]; 
    private JRadioButton[][] gameMap2 = new JRadioButton[12][12];
    private JLabel map2Label = new JLabel("Placing Ships");
    
    private JButton newGame = new JButton("New Game");
    private JButton endGame = new JButton ("End Game");
    
    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(704,450);
        this.setLocationRelativeTo(null);
        battleshipsPanel.setLayout(null);
        
        heading.setFont(new java.awt.Font("Helvetica", 1, 18)); //227 width, 22 height
        heading.setBounds(237,10,230,22);
        battleshipsPanel.add(heading);
        
        p1NameLabel.setBounds(117,42,120,14);
        battleshipsPanel.add(p1NameLabel);
        p2NameLabel.setBounds(470,42,120,14);
        battleshipsPanel.add(p2NameLabel);
        p1Name.setBounds(117,60,120,20);
        battleshipsPanel.add(p1Name);
        p2Name.setBounds(470,60,120,20);
        battleshipsPanel.add(p2Name);
        
        newGame.setBounds(303, 45, 100, 35);
        battleshipsPanel.add(newGame);
        
        //MOVE THIS PART TO SHIP PLACEMENT
        //Create the visual representation of the game map
        for (char i = 'A'; i < 'A'+12; i++) { //Player 1 X Labels
            xLabels1[i-65] = new JLabel("  "+Character.toString(i));
            xLabels1[i-65].setBounds(51+(i-65)*21,85,21,21);
            battleshipsPanel.add(xLabels1[i-65]);
        }
        
        for (char i = 'A'; i < 'A'+12; i++) { //Player 2 X Labels
            xLabels2[i-65] = new JLabel("  "+Character.toString(i));
            xLabels2[i-65].setBounds(403+(i-65)*21,85,21,21);
            battleshipsPanel.add(xLabels2[i-65]);
        }
        
        for (int i = 0; i < 12; i++) { //Player 1 Y Labels & Radio Buttons (Coordinates)
            yLabels1[i] = new JLabel(Integer.toString(i+1));
            yLabels1[i].setBounds(30,106+i*21,21,21);
            battleshipsPanel.add(yLabels1[i]);
            for (int j = 0; j < 12; j++) {
                gameMap1[i][j] = new JRadioButton();
                gameMap1[i][j].setBackground(new java.awt.Color(49, 116, 216));
                gameMap1[i][j].setBounds(51+j*21,106+i*21,21,21);
                battleshipsPanel.add(gameMap1[i][j]);
            }
        }
        
        map1Label.setBounds(140,365,120,20);
        battleshipsPanel.add(map1Label);
        
        for (int i = 0; i < 12; i++) { //Player 2 Y Labels & Radio Buttons (Coordinates)
            yLabels2[i] = new JLabel(Integer.toString(i+1));
            yLabels2[i].setBounds(382,106+i*21,21,21);
            battleshipsPanel.add(yLabels2[i]);
            for (int j = 0; j < 12; j++) {
                gameMap2[i][j] = new JRadioButton();
                gameMap2[i][j].setBackground(new java.awt.Color(66, 161, 54));
                gameMap2[i][j].setBounds(403+j*21,106+i*21,21,21);
                battleshipsPanel.add(gameMap2[i][j]);
            }
        }
        
        map2Label.setBounds(493,365,120,20);
        battleshipsPanel.add(map2Label);
        
        endGame.setBounds(303, 365, 100, 35);
        battleshipsPanel.add(endGame);
        //END OF PART TO MOVE TO SHIP PLACEMENT
        

        this.add(battleshipsPanel);
        this.setVisible(true);
    }
    
    
        //p1Name.setEditable(false);
        //p2Name.setVisible(false); //use these to disable components when needed
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
