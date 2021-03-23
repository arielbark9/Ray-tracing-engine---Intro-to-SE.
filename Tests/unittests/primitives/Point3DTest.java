package unittests.primitives;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.Assert.*;
/**
 * Unit tests for primitives.Point3D class
 * @author Ariel
 */
public class Point3DTest {
    /**
     * Test method for
     * {@link primitives.Point3D#add(Vector)}
     */
    @Test
    public void testAdd() {
        Point3D p1 = new Point3D(1, 2, 3);
        assertEquals("ERROR: add() does not work correctly",
                Point3D.ZERO, p1.add(new Vector(-1, -2, -3)));
    }
    /**
     * Test method for
     * {@link primitives.Point3D#subtract(Point3D)}
     */
    @Test
    public void testSubtract() {
        Point3D p1 = new Point3D(1, 2, 3);
        assertEquals("ERROR: subtract() does not work correctly",
                new Vector(1, 1, 1), new Point3D(2, 3, 4).subtract(p1));
    }
    /**
     * Test method for
     * {@link primitives.Point3D#distanceSquared(Point3D)}
     */
    @Test
    public void testDistanceSquared() {
        assertEquals("Error: distanceSquared() doesn't work correctly!",
                25d, new Point3D(0,0,0).distanceSquared(new Point3D(5,0,0)), 0d);
    }
    /**
     * Test method for
     * {@link primitives.Point3D#distance(Point3D)}
     */
    @Test
    public void testDistance() {
        assertEquals("Error: distance() doesn't work correctly!",
                5d, new Point3D(0,0,0).distance(new Point3D(5,0,0)), 0d);
    }
}