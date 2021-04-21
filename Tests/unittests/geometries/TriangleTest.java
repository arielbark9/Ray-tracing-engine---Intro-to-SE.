package unittests.geometries;

import geometries.Triangle;
import org.junit.Before;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void testGetNormal() {
        Triangle tr = new Triangle(new Point3D(0,0,0), //
                new Point3D(1,0,0), //
                new Point3D(0,1,0));
        assertEquals("Error: getNormal() does not return the right vector!",
                new Vector(0,0,1), tr.getNormal(new Point3D(1,0,0)));
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point3D(2, 0, 0),new Point3D(-2, 0, 0),new Point3D(0, 0, 2));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Point is in triangle (1 point)
        Point3D p1 = new Point3D(0,0,1);
        List<Point3D> res = triangle.findIntersections(new Ray(new Point3D(0,-1,1),
                new Vector(0,1,0)));
        assertEquals("Wrong number of points", 1, res.size());
        assertEquals("Wrong intersection point", p1, res.get(0));

        // TC02: Point is outside against the edge of the triangle (0 points)
        res = triangle.findIntersections(new Ray(new Point3D(2,-1,2),
                new Vector(0,1,0)));
        assertNull("Wrong number of points",res);

        // TC03: Point is outside against the vertex of the triangle (0 points)
        res = triangle.findIntersections(new Ray(new Point3D(0,-1,3),
                new Vector(0,1,0)));
        assertNull("Wrong number of points",res);

        // =============== Boundary Values Tests ==================

        // TC11: Point is on an edge (0 points)
        res = triangle.findIntersections(new Ray(new Point3D(1,-1,1),
                new Vector(0,1,0)));
        assertNull("Wrong number of points",res);

        // TC12: Point is on a vertex (0 points)
        res = triangle.findIntersections(new Ray(new Point3D(2,-1,0),
                new Vector(0,1,0)));
        assertNull("Wrong number of points",res);

        // TC13: Point is on a continuation of an edge (0 points)
        res = triangle.findIntersections(new Ray(new Point3D(1,-1,3),
                new Vector(0,1,0)));
        assertNull("Wrong number of points",res);
    }

}