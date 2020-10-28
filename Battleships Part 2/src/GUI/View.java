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

/**
 * This class creates and defines listeners for the View (GUI)
 * @author Tamati Rudd
 */
public class View extends javax.swing.JFrame implements Observer {

    public JPanel battleshipsPanel = new JPanel();
    public JLabel heading = new JLabel("Battleships: Pre-Game", JLabel.CENTER);
    
    private JLabel p1NameLabel = new JLabel("Player One Name:"); 
    private JLabel p2NameLabel = new JLabel("Player Two Name:"); 
    public JTextField p1Name = new JTextField();
    public JTextField p2Name = new JTextField();
    
    private JLabel xLabels1[] = new JLabel[12]; 
    private JLabel yLabels1[] = new JLabel[12]; 
    public JRadioButton[][] gameMap1 = new JRadioButton[12][12];
    private JLabel map1Label = new JLabel("Placing Ships");
    private JLabel xLabels2[] = new JLabel[12]; 
    private JLabel yLabels2[] = new JLabel[12]; 
    public JRadioButton[][] gameMap2 = new JRadioButton[12][12];
    private JLabel map2Label = new JLabel("Placing Ships");
    
    public JButton newGame = new JButton("New Game");
    public JButton endGame = new JButton ("End Game");
    
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
        
        map1Label.setBounds(140,365,120,20);
        map1Label.setVisible(false);
        battleshipsPanel.add(map1Label);
        map2Label.setBounds(493,365,120,20);
        map2Label.setVisible(false);
        battleshipsPanel.add(map2Label);
        
        newGame.setBounds(303, 45, 100, 35);
        battleshipsPanel.add(newGame);
        endGame.setBounds(303, 365, 100, 35);
        endGame.setVisible(false);
        battleshipsPanel.add(endGame);

        this.setTitle("Battleships");
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
        endGame.addActionListener(listener);
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
    
    public void showMap2() {
        
    }
    
    public void startGame() {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
