package Ships;

import Map.Coordinate;
import Map.State;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Ship class
 * @author Tamati Rudd
 */
public class ShipTest {
    
    public ShipTest() {
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
     * Test of damageReport method, of class Ship.
     */
    @Test
    public void testDamageReport() {
        System.out.println("damageReport");
        Ship instance = new Destroyer();
        HashSet<Coordinate> location = new HashSet<>();
        location.add(new Coordinate('A',1));
        location.add(new Coordinate('A',2));
        location.add(new Coordinate('A',3));
        location.add(new Coordinate('A',4));
        instance.setLocation(location);
        
        Boolean expResult = false;
        Boolean result = instance.damageReport();
        assertEquals(expResult, result);
    }

    /**
     * Test of damageReport method, of class Ship.
     */
    @Test
    public void testDamageReport2() {
        System.out.println("damageReport2");
        Ship instance = new Carrier();
        HashSet<Coordinate> location = new HashSet<>();
        location.add(new Coordinate('D',1));
        location.add(new Coordinate('D',2));
        location.add(new Coordinate('D',3));
        location.add(new Coordinate('D',4));
        location.add(new Coordinate('D',5));
        location.add(new Coordinate('D',6));
        Iterator<Coordinate> it = location.iterator();
        while (it.hasNext()) {
            Coordinate c = it.next();
            c.setCoordState(State.Status.HIT);
        }
        instance.setLocation(location);
        
        Boolean expResult = true;
        Boolean result = instance.damageReport();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of damageReport method, of class Ship.
     * In this test, the Coordinates are all set to Hit except one
     */
    @Test
    public void testDamageReport3() {
        System.out.println("damageReport3");
        Ship instance = new Submarine();
        HashSet<Coordinate> location = new HashSet<>();
        location.add(new Coordinate('F',1));
        location.add(new Coordinate('G',1));
        location.add(new Coordinate('H',1));
        Iterator<Coordinate> it = location.iterator();
        int coordNotHit = 0;
        while (it.hasNext()) {
            Coordinate c = it.next();
            if (coordNotHit != 2)
                c.setCoordState(State.Status.HIT);
            coordNotHit++;
        }
        instance.setLocation(location);
        
        Boolean expResult = false;
        Boolean result = instance.damageReport();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of damageReport method, of class Ship.
     * In this test, the Coordinates are all set to Hit except one, which is set to Miss. Should never happen in a real game, but should pass
     */
    @Test
    public void testDamageReport4() {
        System.out.println("damageReport4");
        Ship instance = new Submarine();
        HashSet<Coordinate> location = new HashSet<>();
        location.add(new Coordinate('F',1));
        location.add(new Coordinate('G',1));
        location.add(new Coordinate('H',1));
        Iterator<Coordinate> it = location.iterator();
        int coordNotHit = 0;
        while (it.hasNext()) {
            Coordinate c = it.next();
            if (coordNotHit != 2)
                c.setCoordState(State.Status.HIT);
            else
                c.setCoordState(State.Status.MISS);
            coordNotHit++;
        }
        instance.setLocation(location);
        
        Boolean expResult = false;
        Boolean result = instance.damageReport();
        assertEquals(expResult, result);
    }
    

    public class ShipImpl extends Ship {
    }  
}
