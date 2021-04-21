package unittests.geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {

    @Test
    void add() {
    }

    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============

        Ray ray = new Ray(new Point3D(0,0,0),new Vector(1,0,0));

        // TC01:  (0 points)
        Geometries geometries = new Geometries();
        assertEquals("empty composite doesn't working",null,geometries.findIntersections(ray));

        // TC01:  (0 points)
        geometries.add(new Sphere(new Point3D(-2,0,0),1),new Plane(new Point3D(0,1,0),new Point3D(1,1,0),new Point3D(1,1,1)),new Triangle(new Point3D(0,1,0),new Point3D(1,1,0),new Point3D(1,1,1)));
        assertEquals("composite with no intersections doesn't working",null,geometries.findIntersections(ray));

        // TC01:  (0 points)
        geometries.add(new Triangle(new Point3D(1,-1,-1),new Point3D(1,1,-1),new Point3D(1,0,1)));
        int pointCounter = geometries.findIntersections(ray).size();
        assertEquals("FindIntersections of composite with one intersection doesn't working: wrong number of points", 1,pointCounter);

       // TC01:  (0 points)
        geometries.add(new Sphere(new Point3D(2,0,0),1));
        pointCounter = geometries.findIntersections(ray).size();
        assertEquals("FindIntersections of composite with few intersections doesn't working: wrong number of points",3, pointCounter);

        // TC01:  (0 points)
        geometries = new Geometries(new Sphere(new Point3D(2,0,0),1),new Plane(new Point3D(4,1,0),new Point3D(4,-1,0),new Point3D(4,0,1)),new Triangle(new Point3D(1,-1,-1),new Point3D(1,1,-1),new Point3D(1,0,1)));
        pointCounter = geometries.findIntersections(ray).size();
        assertEquals("FindIntersections of composite with all intersections doesn't working: wrong number of points",4, pointCounter);

    }
}