package Player;

import Map.Coordinate;
import Map.Database;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for HumanPlayer class
 * NOTE: I could not run tests involving querying the database as I couldn't determine how to properly setup the database in the test environment 
 * @author Tamati Rudd
 */
public class HumanPlayerTest {
    
    public HumanPlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of constructLocation method, of class HumanPlayer.
     * Tests the creation of a Carrier oriented down from B5
     */
    @Test
    public void testConstructLocation() {
        System.out.println("constructLocation");
        Coordinate front = new Coordinate('B',5);
        int orientation = 1;
        int ship = 0;
        HumanPlayer instance = new HumanPlayer("TestPlayer", 1, new Database());
        HashSet<Coordinate> expResult = new HashSet<>();
        expResult.add(new Coordinate('B',5));
        expResult.add(new Coordinate('B',6));
        expResult.add(new Coordinate('B',7));
        expResult.add(new Coordinate('B',8));
        expResult.add(new Coordinate('B',9));
        expResult.add(new Coordinate('B',10));
        HashSet<Coordinate> result = instance.constructLocation(front, orientation, ship);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of constructLocation method, of class HumanPlayer.
     * Tests the creation of a Submarine oriented left from I11
     */
    @Test
    public void testConstructLocation2() {
        System.out.println("constructLocation2");
        Coordinate front = new Coordinate('I',11);
        int orientation = 2;
        int ship = 3;
        HumanPlayer instance = new HumanPlayer("TestPlayer", 1, new Database());
        HashSet<Coordinate> expResult = new HashSet<>();
        expResult.add(new Coordinate('I',11));
        expResult.add(new Coordinate('H',11));
        expResult.add(new Coordinate('G',11));
        HashSet<Coordinate> result = instance.constructLocation(front, orientation, ship);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of constructLocation method, of class HumanPlayer.
     * Tests the creation of a Battleship oriented up from A3
     * Note: the creation of this ship is meant to work as this method doesn't test if the placement would be legal
     */
    @Test
    public void testConstructLocation3() {
        System.out.println("constructLocation3");
        Coordinate front = new Coordinate('A',2);
        int orientation = 0;
        int ship = 1;
        HumanPlayer instance = new HumanPlayer("TestPlayer", 2, new Database());
        HashSet<Coordinate> expResult = new HashSet<>();
        expResult.add(new Coordinate('A',2));
        expResult.add(new Coordinate('A',1));
        expResult.add(new Coordinate('A',0));
        expResult.add(new Coordinate('A',-1));
        expResult.add(new Coordinate('A',-2));
        HashSet<Coordinate> result = instance.constructLocation(front, orientation, ship);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of constructLocation method, of class HumanPlayer.
     * Tests the creation of a Patrol Boat oriented right from E2
     */
    @Test
    public void testConstructLocation4() {
        System.out.println("constructLocation4");
        Coordinate front = new Coordinate('E',2);
        int orientation = 3;
        int ship = 4;
        HumanPlayer instance = new HumanPlayer("TestPlayer", 2, new Database());
        HashSet<Coordinate> expResult = new HashSet<>();
        expResult.add(new Coordinate('E',2));
        expResult.add(new Coordinate('F',2));
        HashSet<Coordinate> result = instance.constructLocation(front, orientation, ship);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of constructLocation method, of class HumanPlayer.
     * Tests the creation of a Destroyer oriented right from E2
     */
    @Test
    public void testConstructLocation5() {
        System.out.println("constructLocation4");
        Coordinate front = new Coordinate('E',2);
        int orientation = 3;
        int ship = 4;
        HumanPlayer instance = new HumanPlayer("TestPlayer", 2, new Database());
        HashSet<Coordinate> expResult = new HashSet<>();
        expResult.add(new Coordinate('E',2));
        expResult.add(new Coordinate('F',2));
        HashSet<Coordinate> result = instance.constructLocation(front, orientation, ship);
        assertEquals(expResult, result);
    }
}
