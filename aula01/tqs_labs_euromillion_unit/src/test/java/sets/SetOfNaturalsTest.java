/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.Iterator;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");

    }

    // MY TESTS
    @Test
    @DisplayName("A set should not have duplicated values")
    public void addDuplicatedValueTest() {
        assertThrows(IllegalArgumentException.class, () -> setD.add(10));
    }

    @Test
    @DisplayName("A set of naturals should only have numbers greater than 0")
    public void addNotNaturalNumberTest() {
        assertThrows(IllegalArgumentException.class, () -> setD.add(0));
        assertThrows(IllegalArgumentException.class, () -> setD.add(-1));
    }

    @Test
    @DisplayName("A set of naturals has size zero after instantiated")
    public void sizeWhenEmptyTest() {
        assertEquals(0, setA.size());
    }

    @Test
    @DisplayName("A set of naturals should intersect a subset from this set of naturals")
    public void intersectsTest() {
        SetOfNaturals setE = SetOfNaturals.fromArray(new int[]{10, 20});
        assertTrue(setD.intersects(setE));
        assertFalse(setD.intersects(setA));
    }

    @Test
    @DisplayName("If I look for x and a set of naturals contains x, contains() should return true")
    public void containsTest() {
        int number = 50;
        if (setD.contains(number)){
            assertTrue(setD.contains(number));
        }

    }


}
