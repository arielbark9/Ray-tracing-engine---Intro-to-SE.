package unittests.geometries;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

public class GeometriesTest {

    @Test
    public void findIntersections() {
        // ============ Equivalence Partitions Tests ==============

        Ray ray = new Ray(new Point3D(0,0,0),new Vector(1,0,0));

        // TC01: No shapes (0 points)
        Geometries geometries = new Geometries();
        assertNull("empty composite doesn't work",geometries.findIntersections(ray));

        // TC02: No intersections with given shapes (0 points)
        geometries.add(new Sphere(new Point3D(-2,0,0),1),new Plane(new Point3D(0,1,0),new Point3D(1,1,0),new Point3D(1,1,1)),new Triangle(new Point3D(0,1,0),new Point3D(1,1,0),new Point3D(1,1,1)));
        assertNull("composite with no intersections doesn't work",geometries.findIntersections(ray));

        // TC03: One intersection (0 points)
        geometries.add(new Triangle(new Point3D(1,-1,-1),new Point3D(1,1,-1),new Point3D(1,0,1)));
        int pointCounter = geometries.findIntersections(ray).size();
        assertEquals("FindIntersections of composite with one intersection doesn't working: wrong number of points", 1,pointCounter);


        // TC04: everything intersects (4 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(1,-1,-1),new Point3D(1,1,-1),new Point3D(1,0,1)));
        pointCounter = geometries.findIntersections(ray).size();
        assertEquals("FindIntersections of composite with all intersections doesn't working: wrong number of points",4, pointCounter);

    }
}