package unittests.primitives;

import org.junit.Assert;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;
/**
 * Unit tests for primitives.Vector class
 * @author Ariel
 */
public class VectorTest {
    /**
     * Test method for {@link Vector#Vector(double, double, double)}
     * and {@link Vector#Vector(Point3D)}
     * and {@link Vector#Vector(Coordinate, Coordinate, Coordinate)}
     */
    @Test
    public void testConstructor() {
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
        try { // test zero vector
            new Vector(new Coordinate(0), new Coordinate(0), new Coordinate(0));
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
        try { // test zero vector
            new Vector(new Point3D(0, 0, 0));
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    public void testLengthSquared() {
        Vector v1 = new Vector(10, 12, 5);
        // test lengthSquared..
        assertTrue("ERROR: lengthSquared() wrong value",isZero(v1.lengthSquared() - 269));
    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    public void testLength() {
        Vector v1 = new Vector(0, 3, 4);
        // test length..
        assertTrue("ERROR: length() wrong value",isZero(v1.length() - 5));
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}
     */
    @Test
    public void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // test Dot-Product
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
        assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        try {
            v1.crossProduct(v3);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (IllegalArgumentException e) {}
    }


    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    public void testNormalize() {
        Vector v1 = new Vector(-2, -4, -6);
        assertEquals("ERROR: normalize() does not return the right normalized vector", //
                new Vector(-0.2672612419124244,-0.5345224838248488,-0.8017837257372732),v1.normalize());
        assertEquals("ERROR: normalize() does not change the vector itself", //
                new Vector(-0.2672612419124244,-0.5345224838248488,-0.8017837257372732),v1);
    }

    /**
     * Test method for {@link Vector#normalized()}
     */
    @Test
    public void testNormalized() {
        Vector v1 = new Vector(-2, -4, -6);
        assertEquals("ERROR: normalized() does not return the right normalized vector", //
                new Vector(-0.2672612419124244,-0.5345224838248488,-0.8017837257372732),v1.normalized());
        assertEquals("ERROR: normalized() changes the vector", //
                new Vector(-2,-4,-6),v1);
    }

    /**
     * Test method for {@link Vector#add(Vector)}
     */
    @Test
    public void testAdd() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals("ERROR: add() does not return the right vector", //
                new Vector(-1,-2,-3),v1.add(v2));
    }

    /**
     * Test method for {@link Vector#subtract(Vector)}
     */
    @Test
    public void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals("ERROR: subtract() does not return the right vector", //
                new Vector(3,6,9),v1.subtract(v2));
    }

    /**
     * Test method for {@link Vector#scale(double)}
     */
    @Test
    public void testScale() {
        Vector v1 = new Vector(1, 2, 3);
        assertEquals("ERROR: scale() does not return the right vector", //
                new Vector(-1.5,-3,-4.5),v1.scale(-1.5));
        try {
            v1.scale(0);
            fail("ERROR: scale() allows for zero vector");
        } catch (Exception e){}
    }
}