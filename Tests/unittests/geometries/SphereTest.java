package unittests.geometries;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Ariel
 */
public class SphereTest {
    /**
     * Test method for
     * {@link geometries.Sphere#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // TC01: Test normal to sphere (also tests that normal length is 1)
        var sp = new Sphere(new Point3D(1, 0, 0), 1);
        assertEquals("Error: getNormal() doesn't work correctly",
                new Vector(-1, 0, 0), sp.getNormal(new Point3D(0, 0, 0)));
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point3D(0.2, 0, 0.6);
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0),
                new Vector(-0.5, 0, 1)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", p1, result.get(0));

        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(0, 0, 1),
                new Vector(-0.5, 0, 1)));
        assertNull("Wrong number of points", result);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        p1 = new Point3D(1, 0, -1);
        result = sphere.findIntersections(new Ray(new Point3D(0.2, 0, 0.6),
                new Vector(0.3, 0, -0.6)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", p1, result.get(0));

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(0.2, 0, 0.6),
                new Vector(-0.2, 0, 0.4)));
        assertNull("Wrong number of points", result);

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point3D(0, 0, 0);
        p2 = new Point3D(2, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(-0.5, 0, 0),
                new Vector(1, 0, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        p1 = new Point3D(0, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", p1, result.get(0));

        // TC15: Ray starts inside (1 points)
        p1 = new Point3D(0, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1.5, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", p1, result.get(0));

        // TC16: Ray starts at the center (1 points)
        p1 = new Point3D(0, 0, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0),
                new Vector(-1, 0, 0)));
        assertEquals("Wrong number of points", 1, result.size());
        assertEquals("Ray crosses sphere", p1, result.get(0));

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(1, 0, 0)));
        assertNull("Wrong number of points", result);

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(3, 0, 0),
                new Vector(1, 0, 0)));
        assertNull("Wrong number of points", result);

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, -1),
                new Vector(0, 0, 1)));
        assertNull("Wrong number of points", result);

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(0, 0, 1)));
        assertNull("Wrong number of points", result);

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 1),
                new Vector(0, 0, 1)));
        assertNull("Wrong number of points", result);

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point3D(3, 0, 0),
                new Vector(0, 0, 1)));
        assertNull("Wrong number of points", result);

    }
}

