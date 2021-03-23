package unittests.geometries;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Plane class
 * @author Ariel
 */
public class PlaneTest {
    /**
     * Test method for
     * {@link geometries.Plane#Plane(Point3D, Point3D, Point3D)}
     */
    @Test
    public void testConstructor() {
        // TC01: Test plane constructor when two points are the same.
        try {
            new Plane(new Point3D(0,0,0),new Point3D(0,0,0),new Point3D(0,1,0));
            fail("Error: Plane() doesn't throw when two points are the same");
        } catch (IllegalArgumentException ex) { }

        // TC02: Test plane constructor when all points are on the same line.
        try {
            new Plane(new Point3D(1,1,1),new Point3D(2,2,2),new Point3D(3,3,3));
            fail("Error: Plane() doesn't throw when all points are on the same line");
        } catch (IllegalArgumentException ex) { }
    }

    /**
     * Test method for
     * {@link geometries.Plane#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // TC01: Test normal to Plane (also tests that normal length is 1)
        Plane pl = new Plane(new Point3D(0,0,0), new Point3D(1,0,0), new Point3D(0,1,0));
        var normal = pl.getNormal(new Point3D(0,0,0));
        // check for both sides of plane
        boolean option1 = normal.equals(new Vector(0,0,1));
        boolean option2 = normal.equals(new Vector(0,0,-1));
        assertTrue("Error: getNormal() doesn't work correctly",option1 || option2);
    }
}