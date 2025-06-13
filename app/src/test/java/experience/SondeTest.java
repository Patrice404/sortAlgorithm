package experience;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SondeTest {

    private Sonde sonde;

    @BeforeEach
    void setUp() {
        sonde = new Sonde(10, 20, 5, 100);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(10, sonde.getNbComparaisons());
        assertEquals(20, sonde.getNbAccess());
        assertEquals(5, sonde.getNbEchanges());
        assertEquals(100, sonde.getTime());
    }

    @Test
    void testCopy() {
        Sonde copy = Sonde.copy(sonde);
        assertEquals(sonde.getNbComparaisons(), copy.getNbComparaisons());
        assertEquals(sonde.getNbAccess(), copy.getNbAccess());
        assertEquals(sonde.getNbEchanges(), copy.getNbEchanges());
        assertEquals(sonde.getTime(), copy.getTime());
    }

    @Test
    void testAdd() {
        Sonde other = new Sonde(5, 10, 2, 50);
        sonde.add(other);
        assertEquals(15, sonde.getNbComparaisons());
        assertEquals(30, sonde.getNbAccess());
        assertEquals(7, sonde.getNbEchanges());
        assertEquals(150, sonde.getTime());
    }

    @Test
    void testDiviseBy() {
        sonde.diviseBy(2);
        assertEquals(5, sonde.getNbComparaisons());
        assertEquals(10, sonde.getNbAccess());
        assertEquals(2, sonde.getNbEchanges());
        assertEquals(50, sonde.getTime());
    }

    @Test
    void testReset() {
        sonde.reset();
        assertEquals(0, sonde.getNbComparaisons());
        assertEquals(0, sonde.getNbAccess());
        assertEquals(0, sonde.getNbEchanges());
        assertEquals(0, sonde.getTime());
    }

    @Test
    void testIncrementComparaison() {
        sonde.incrementComparaison();
        assertEquals(11, sonde.getNbComparaisons());
    }

    @Test
    void testIncrementAccess() {
        sonde.incrementAccess();
        assertEquals(21, sonde.getNbAccess());
        sonde.incrementAccess(5);
        assertEquals(26, sonde.getNbAccess());
    }

    @Test
    void testIncrementSwaps() {
        sonde.incrementSwaps();
        assertEquals(6, sonde.getNbEchanges());
        assertEquals(22, sonde.getNbAccess());
    }

    @Test
    void testSetTime() {
        sonde.setTime(200);
        assertEquals(200, sonde.getTime());
        sonde.setTime(-50); // Should not change the time
        assertEquals(200, sonde.getTime());
    }

    @Test
    void testEquals() {
        Sonde other = new Sonde(10, 20, 5, 100);
        assertTrue(sonde.equals(other));
        other = new Sonde(10, 20, 6, 100);
        assertFalse(sonde.equals(other));
    }

    @Test
    void testToString() {
        String expected = "Sonde{nbComparaison =10, nbAccess =20 , nbEchanges=5 , temps =100}";
        assertEquals(expected, sonde.toString());
    }
}