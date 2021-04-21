package unittests.geometries;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        } catch (IllegalArgumentException ignored) { }

        // TC02: Test plane constructor when all points are on the same line.
        try {
            new Plane(new Point3D(1,1,1),new Point3D(2,2,2),new Point3D(3,3,3));
            fail("Error: Plane() doesn't throw when all points are on the same line");
        } catch (IllegalArgumentException ignored) { }
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

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point3D(1, 0, 0),new Point3D(0, 1, 0),new Point3D(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 points)
        Point3D p1 = new Point3D(0,0,1);
        List<Point3D> res = plane.findIntersections(new Ray(new Point3D(0,0,0),
                new Vector(0,0,1)));
        assertEquals("Wrong number of points", 1, res.size());
        assertEquals("Wrong intersection point", p1, res.get(0));

        // TC02: Ray doesn't intersect the plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(0,0,0),
                new Vector(0,0,-1)));
        assertNull("Wrong number of points", res);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to plane
        // TC11: Ray is included in plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(0,0,1),
                new Vector(-1,0,1)));
        assertNull("Wrong number of points", res);

        // TC12: Ray isn't included in plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(-1,0,0),
                new Vector(-1,0,1)));
        assertNull("Wrong number of points", res);

        // **** Group: Ray is orthogonal to plane
        plane = new Plane(new Point3D(1, 0, 1),new Point3D(0, 1, 1),new Point3D(0, 0, 1));
        // TC13: Ray starts before the plane (1 points)
        p1 = new Point3D(0,0,1);
        res = plane.findIntersections(new Ray(new Point3D(0,0,0.5),
                new Vector(0,0,1)));
        assertEquals("Wrong number of points", 1, res.size());
        assertEquals("Wrong intersection point", p1, res.get(0));

        // TC14: Ray starts at plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(0,0,1),
                new Vector(0,0,1)));
        assertNull("Wrong number of points", res);

        // TC15: Ray starts after plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(0,0,1.5),
                new Vector(0,0,1)));
        assertNull("Wrong number of points", res);

        // **** Group: Special cases
        // TC16: Ray begins at plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(0,1,1),
                new Vector(0,1,1)));
        assertNull("Wrong number of points", res);

        // TC17: Ray begins at reference point of plane (0 points)
        res = plane.findIntersections(new Ray(new Point3D(1,0,1),
                new Vector(1,0,1)));
        assertNull("Wrong number of points", res);
    }
}