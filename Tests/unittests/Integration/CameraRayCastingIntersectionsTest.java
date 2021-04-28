package unittests.Integration;

import static org.junit.Assert.*;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;

import elements.Camera;
import primitives.*;

import java.util.List;

/**
 * Testing Camera Class integration with ray intersections
 *
 * @author Ariel
 *
 */
public class CameraRayCastingIntersectionsTest {

    /**
     * camera orientation for all tests.
     */
    Camera camera = new Camera(Point3D.ZERO,new Vector(0, 0, -1),new Vector(0, 1, 0))
            .setDistance(1)
            .setViewPlaneSize(3,3);

    /**
     * Test method for Ray through view plane Intersections with Sphere
     */
    @Test
    public void testConstructRayIntersectionSphere() {
        Sphere sphere;
        // TC01: r = 1. (2 Points)
        sphere = new Sphere(new Point3D(0,0,-3),1);
        assertEquals("Wrong # of intersections",2,getNumIntersectionPoints(camera,sphere));

        // TC02: r = 2.5 (18 Points)
        camera.setPosition(new Point3D(0,0,0.5));
        sphere = new Sphere(new Point3D(0,0,-2.5),2.5);
        assertEquals("Wrong # of intersections",18,getNumIntersectionPoints(camera,sphere));

        // TC03: r = 2 (10 Points)
        sphere = new Sphere(new Point3D(0,0,-2),2);
        assertEquals("Wrong # of intersections",10,getNumIntersectionPoints(camera,sphere));

        // TC04: r = 4 (9 Points)
        camera.setPosition(Point3D.ZERO);
        sphere = new Sphere(new Point3D(0,0,-1),4);
        assertEquals("Wrong # of intersections",9,getNumIntersectionPoints(camera,sphere));

        // TC05: r = 0.5 (0 Points)
        sphere = new Sphere(new Point3D(0,0,1),0.5);
        assertEquals("Wrong # of intersections",0,getNumIntersectionPoints(camera,sphere));
    }

    /**
     * Test method for Ray through view plane Intersections with Triangle
     */
    @Test
    public void testConstructRayIntersectionTriangle() {
        Triangle triangle;
        // TC01: (1 Point)
        triangle = new Triangle(new Point3D(0,1,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        assertEquals("Wrong # of intersections",1,getNumIntersectionPoints(camera,triangle));

        // TC02: (2 Points)
        triangle = new Triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        assertEquals("Wrong # of intersections",2,getNumIntersectionPoints(camera,triangle));
    }

    /**
     * Test method for Ray through view plane Intersections with Plane
     */
    @Test
    public void testConstructRayIntersectionPlane() {
        Plane plane;
        // TC01: (9 Points)
        plane = new Plane(new Point3D(0,0,-2),new Vector(0,0,1));
        assertEquals("Wrong # of intersections",9,getNumIntersectionPoints(camera,plane));

        // TC02: tilted (9 Points)
        plane = new Plane(new Point3D(0,0,-2),new Vector(0,-0.5,1));
        assertEquals("Wrong # of intersections",9,getNumIntersectionPoints(camera,plane));

        // TC03: lower pixels don't intersect (6 Points)
        plane = new Plane(new Point3D(0,0,-2),new Vector(0,-1,1));
        assertEquals("Wrong # of intersections",6,getNumIntersectionPoints(camera,plane));
    }

    /**
     * @param camera camera to construct rays from
     * @param shape to intersect with
     * @return number of intersections
     */
    private static int getNumIntersectionPoints(Camera camera, Intersectable shape) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray rayIJ = camera.constructRayThroughPixel(3,3,j,i);
                List<Point3D> intersections = shape.findIntersections(rayIJ);
                res += (intersections != null) ? intersections.size() : 0;
            }
        }
        return res;
    }
}
