package unittests.geometries;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

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

    @Test
    public void findGeoIntersectionsWithDistance() {
        // ============ Equivalence Partitions Tests ==============

        Ray ray = new Ray(new Point3D(0,0,0),new Vector(1,0,0));

        // TC01: No shapes (0 points)
        Geometries geometries = new Geometries();
        assertNull("empty composite doesn't work",geometries.findGeoIntersections(ray,Double.POSITIVE_INFINITY));

        // TC02: No intersections with given shapes (0 points)
        geometries.add(new Sphere(new Point3D(-2,0,0),1),new Plane(new Point3D(0,1,0),new Point3D(1,1,0),new Point3D(1,1,1)),new Triangle(new Point3D(0,1,0),new Point3D(1,1,0),new Point3D(1,1,1)));
        assertNull("composite with no intersections doesn't work",geometries.findGeoIntersections(ray,Double.POSITIVE_INFINITY));

        // TC03: One intersection (0 points)
        geometries.add(new Triangle(new Point3D(1,-1,-1),new Point3D(1,1,-1),new Point3D(1,0,1)));
        int pointCounter = geometries.findGeoIntersections(ray,Double.POSITIVE_INFINITY).size();
        assertEquals("FindIntersections of composite with one intersection doesn't working: wrong number of points", 1,pointCounter);


        // TC04: everything intersects (4 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(1,-1,-1),new Point3D(1,1,-1),new Point3D(1,0,1)));
        pointCounter = geometries.findGeoIntersections(ray,Double.POSITIVE_INFINITY).size();
        assertEquals("FindIntersections of composite with all intersections doesn't working: wrong number of points",4, pointCounter);

        // TC05: everything intersects but farther than max distance (0 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(6,-1,-1),new Point3D(6,1,-1),new Point3D(6,0,1)));
        assertNull("FindIntersections of composite with all intersections doesn't working: wrong number of points",geometries.findGeoIntersections(ray,0.5));

        // TC06: only sphere is inside distance (2 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(6,-1,-1),new Point3D(6,1,-1),new Point3D(6,0,1)));
        pointCounter = geometries.findGeoIntersections(ray,3.5).size();
        assertEquals("FindIntersections of composite with all intersections doesn't working: wrong number of points",2, pointCounter);

        // TC07: sphere, plane are inside distance (3 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(6,-1,-1),new Point3D(6,1,-1),new Point3D(6,0,1)));
        pointCounter = geometries.findGeoIntersections(ray,5).size();
        assertEquals("FindIntersections of composite with all intersections doesn't working: wrong number of points",3, pointCounter);

        // TC06: sphere, plane, triangle are inside distance (4 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(6,-1,-1),new Point3D(6,1,-1),new Point3D(6,0,1)));
        pointCounter = geometries.findGeoIntersections(ray,7).size();
        assertEquals("FindIntersections of composite with all intersections doesn't working: wrong number of points",4, pointCounter);

    }
}